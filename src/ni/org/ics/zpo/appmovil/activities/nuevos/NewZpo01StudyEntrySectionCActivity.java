package ni.org.ics.zpo.appmovil.activities.nuevos;

import android.app.Dialog;
import android.content.ContentUris;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import ni.org.ics.zpo.appmovil.AbstractAsyncActivity;
import ni.org.ics.zpo.appmovil.MainActivity;
import ni.org.ics.zpo.appmovil.MyZpoApplication;
import ni.org.ics.zpo.appmovil.R;
import ni.org.ics.zpo.appmovil.database.ZpoAdapter;
import ni.org.ics.zpo.domain.Zpo01StudyEntrySectionC;
import ni.org.ics.zpo.appmovil.parsers.Zpo01StudyEntrySectionCXml;
import ni.org.ics.zpo.appmovil.preferences.PreferencesActivity;
import ni.org.ics.zpo.appmovil.utils.Constants;
import ni.org.ics.zpo.appmovil.utils.FileUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.util.Date;


public class NewZpo01StudyEntrySectionCActivity extends AbstractAsyncActivity {

	protected static final String TAG = NewZpo01StudyEntrySectionCActivity.class.getSimpleName();
	
	private ZpoAdapter zipA;
	private static Zpo01StudyEntrySectionC mZp01C = null;
	
	public static final int ADD_ZP01E_ODK = 1;
	public static final int EDIT_ZP01E_ODK = 2;

	Dialog dialogInit;
	private SharedPreferences settings;
	private String username;
	private String mRecordId = "";
	private Integer accion = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (!FileUtils.storageReady()) {
			Toast toast = Toast.makeText(getApplicationContext(),getString(R.string.error, R.string.storage_error),Toast.LENGTH_LONG);
			toast.show();
			finish();
		}
		settings =
				PreferenceManager.getDefaultSharedPreferences(this);
		username =
				settings.getString(PreferencesActivity.KEY_USERNAME,
						null);
		String mPass = ((MyZpoApplication) this.getApplication()).getPassApp();
		mZp01C = (Zpo01StudyEntrySectionC) getIntent().getExtras().getSerializable(Constants.OBJECTO_ZP01E);
        mRecordId = getIntent().getExtras().getString(Constants.RECORDID);
        zipA = new ZpoAdapter(this.getApplicationContext(),mPass,false,false);
		createInitDialog();
	}

	/**
	 * Presenta dialogo inicial
	 */

	private void createInitDialog() {
		dialogInit = new Dialog(this, R.style.FullHeightDialog); 
		dialogInit.setContentView(R.layout.yesno); 
		dialogInit.setCancelable(false);

		//to set the message
		TextView message =(TextView) dialogInit.findViewById(R.id.yesnotext);
		if (mZp01C !=null){
			message.setText(getString(R.string.edit)+ " " + getString(R.string.maternal_b_2)+"?");
		}
		else{
			message.setText(getString(R.string.add)+ " " + getString(R.string.maternal_b_2)+"?");
		}

		//add some action to the buttons

		Button yes = (Button) dialogInit.findViewById(R.id.yesnoYes);
		yes.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				dialogInit.dismiss();
				addStudyEntryC();
			}
		});

		Button no = (Button) dialogInit.findViewById(R.id.yesnoNo);
		no.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// Cierra
				dialogInit.dismiss();
				finish();
			}
		});
		dialogInit.show();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.general, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId()==R.id.MENU_BACK){
			finish();
			return true;
		}
		else if(item.getItemId()==R.id.MENU_HOME){
			Intent i = new Intent(getApplicationContext(),
					MainActivity.class);
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
			finish();
			return true;
		}
		else{
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		if(requestCode == ADD_ZP01E_ODK||requestCode == EDIT_ZP01E_ODK) {
	        if(resultCode == RESULT_OK) {
	        	Uri instanceUri = intent.getData();
				//Busca la instancia resultado
				String[] projection = new String[] {
						"_id","instanceFilePath", "status","displaySubtext"};
				Cursor c = getContentResolver().query(instanceUri, projection,
						null, null, null);
				c.moveToFirst();
				//Captura la id de la instancia y la ruta del archivo para agregarlo al participante
				Integer idInstancia = c.getInt(c.getColumnIndex("_id"));
				String instanceFilePath = c.getString(c.getColumnIndex("instanceFilePath"));
				String complete = c.getString(c.getColumnIndex("status"));
				//cierra el cursor
				if (c != null) {
					c.close();
				}
				if (complete.matches("complete")){
					//Parsear el resultado obteniendo un tamizaje si esta completo
					parseStudyEntryE(idInstancia,instanceFilePath,accion);
				}
				else{
					Toast.makeText(getApplicationContext(),	getString(R.string.err_not_completed), Toast.LENGTH_LONG).show();
				}
	        }
	        else{
	        	finish();
	        }
	    }
		super.onActivityResult(requestCode, resultCode, intent);
	}

	/**
	 * 
	 */
	private void addStudyEntryC() {
		try{
			Uri formUri;
			if(mZp01C ==null){
				//campos de proveedor de collect
				String[] projection = new String[] {
						"_id","jrFormId","displayName"};
				//cursor que busca el formulario
				Cursor c = getContentResolver().query(Constants.CONTENT_URI, projection,
						"jrFormId = 'zpo01_study_entry_c' and displayName = 'Estudio ZPO Visita inicial en el estudio_C'", null, null);
				c.moveToFirst();
				//captura el id del formulario
				Integer id = Integer.parseInt(c.getString(0));
				//cierra el cursor
				if (c != null) {
					c.close();
				}
				//forma el uri para ODK Collect
				formUri = ContentUris.withAppendedId(Constants.CONTENT_URI,id);
				accion = ADD_ZP01E_ODK;
			}
			else{
				//forma el uri para la instancia en ODK Collect
				Integer id = mZp01C.getIdInstancia();
				formUri = ContentUris.withAppendedId(Constants.CONTENT_URI_I,id);
				accion = EDIT_ZP01E_ODK;
			}
			Intent odkA =  new Intent(Intent.ACTION_EDIT,formUri);
			//Arranca la actividad proveedor de instancias de ODK Collect en busca de resultado
			startActivityForResult(odkA, accion);
		}
		catch(Exception e){
			//No existe el formulario en el equipo
			Log.e(TAG, e.getMessage(), e);
			Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
			finish();
		}
	}
	
	private void parseStudyEntryE(Integer idInstancia, String instanceFilePath, Integer accion) {
		Serializer serializer = new Persister();
		File source = new File(instanceFilePath);
		try {
			Zpo01StudyEntrySectionCXml zp01Xml = new Zpo01StudyEntrySectionCXml();
			zp01Xml = serializer.read(Zpo01StudyEntrySectionCXml.class, source);
			if (accion==ADD_ZP01E_ODK) mZp01C = new Zpo01StudyEntrySectionC();
			mZp01C.setRecordId(mRecordId);
			mZp01C.setEventName(Constants.ENTRY);
            mZp01C.setSeaDiseases(zp01Xml.getSeaDiseases());
            mZp01C.setSeaOtherSpecify(zp01Xml.getSeaOtherSpecify());
            mZp01C.setSeaHepatitis(zp01Xml.getSeaHepatitis());
            mZp01C.setSeaHepatitisDate1(zp01Xml.getSeaHepatitisDate1());
            mZp01C.setSeaHepatitisDate2(zp01Xml.getSeaHepatitisDate2());
            mZp01C.setSeaHepatitisDate3(zp01Xml.getSeaHepatitisDate3());
            mZp01C.setSeaMeasles(zp01Xml.getSeaMeasles());
            mZp01C.setSeaMeaslesDate1(zp01Xml.getSeaMeaslesDate1());
            mZp01C.setSeaMeaslesDate2(zp01Xml.getSeaMeaslesDate2());
            mZp01C.setSeaMeaslesDate3(zp01Xml.getSeaMeaslesDate3());
            mZp01C.setSeaChickenpox(zp01Xml.getSeaChickenpox());
            mZp01C.setSeaChickenpoxDate1(zp01Xml.getSeaChickenpoxDate1());
            mZp01C.setSeaChickenpoxDate2(zp01Xml.getSeaChickenpoxDate2());
            mZp01C.setSeaChickenpoxDate3(zp01Xml.getSeaChickenpoxDate3());
            mZp01C.setSeaInfluenza(zp01Xml.getSeaInfluenza());
            mZp01C.setSeaInfluenzaDate1(zp01Xml.getSeaInfluenzaDate1());
            mZp01C.setSeaInfluenzaDate2(zp01Xml.getSeaInfluenzaDate2());
            mZp01C.setSeaInfluenzaDate3(zp01Xml.getSeaInfluenzaDate3());
            mZp01C.setSeaYellow(zp01Xml.getSeaYellow());
            mZp01C.setSeaYellowDate1(zp01Xml.getSeaYellowDate1());
            mZp01C.setSeaYellowDate2(zp01Xml.getSeaYellowDate2());
            mZp01C.setSeaYellowDate3(zp01Xml.getSeaYellowDate3());
            mZp01C.setSeaDengue(zp01Xml.getSeaDengue());
            mZp01C.setSeaDengueDate1(zp01Xml.getSeaDengueDate1());
            mZp01C.setSeaDengueDate2(zp01Xml.getSeaDengueDate2());
            mZp01C.setSeaDengueDate3(zp01Xml.getSeaDengueDate3());
            mZp01C.setSeaVacOther(zp01Xml.getSeaVacOther());
            mZp01C.setSeaVacotherSpecify(zp01Xml.getSeaVacotherSpecify());
            mZp01C.setSeaOtherDate1(zp01Xml.getSeaOtherDate1());
            mZp01C.setSeaOtherDate2(zp01Xml.getSeaOtherDate2());
            mZp01C.setSeaOtherDate3(zp01Xml.getSeaOtherDate3());
            mZp01C.setSeaRubella(zp01Xml.getSeaRubella());
            mZp01C.setSeaRubellaDate1(zp01Xml.getSeaRubellaDate1());
            mZp01C.setSeaRubellaDate2(zp01Xml.getSeaRubellaDate2());
            mZp01C.setSeaRubellaDate3(zp01Xml.getSeaRubellaDate3());
            mZp01C.setSeaRubellaDoc(zp01Xml.getSeaRubellaDoc());
            mZp01C.setSeaCmv(zp01Xml.getSeaCmv());
            mZp01C.setSeaCmvDate1(zp01Xml.getSeaCmvDate1());
            mZp01C.setSeaCmvDate2(zp01Xml.getSeaCmvDate2());
            mZp01C.setSeaCmvDate3(zp01Xml.getSeaCmvDate3());
            mZp01C.setSeaCmvDoc(zp01Xml.getSeaCmvDoc());
            mZp01C.setSeaHerpes(zp01Xml.getSeaHerpes());
            mZp01C.setSeaHerpesDate1(zp01Xml.getSeaHerpesDate1());
            mZp01C.setSeaHerpesDate2(zp01Xml.getSeaHerpesDate2());
            mZp01C.setSeaHerpesDate3(zp01Xml.getSeaHerpesDate3());
            mZp01C.setSeaHerpesDoc(zp01Xml.getSeaHerpesDoc());
            mZp01C.setSeaParvovirus(zp01Xml.getSeaParvovirus());
            mZp01C.setSeaParvovirusDate1(zp01Xml.getSeaParvovirusDate1());
            mZp01C.setSeaParvovirusDate2(zp01Xml.getSeaParvovirusDate2());
            mZp01C.setSeaParvovirusDate3(zp01Xml.getSeaParvovirusDate3());
            mZp01C.setSeaParvovirusDoc(zp01Xml.getSeaParvovirusDoc());
            mZp01C.setSeaToxoplasmosis(zp01Xml.getSeaToxoplasmosis());
            mZp01C.setSeaToxoplasmosisDate1(zp01Xml.getSeaToxoplasmosisDate1());
            mZp01C.setSeaToxoplasmosisDate2(zp01Xml.getSeaToxoplasmosisDate2());
            mZp01C.setSeaToxoplasmosisDate3(zp01Xml.getSeaToxoplasmosisDate3());
            mZp01C.setSeaToxoplasmosisDoc(zp01Xml.getSeaToxoplasmosisDoc());
            mZp01C.setSeaSyphillis(zp01Xml.getSeaSyphillis());
            mZp01C.setSeaSyphillisDate1(zp01Xml.getSeaSyphillisDate1());
            mZp01C.setSeaSyphillisDate2(zp01Xml.getSeaSyphillisDate2());
            mZp01C.setSeaSyphillisDate3(zp01Xml.getSeaSyphillisDate3());
            mZp01C.setSeaSyphillisDoc(zp01Xml.getSeaSyphillisDoc());
            mZp01C.setSeaHiv(zp01Xml.getSeaHiv());
            mZp01C.setSeaHivDate1(zp01Xml.getSeaHivDate1());
            mZp01C.setSeaHivDate2(zp01Xml.getSeaHivDate2());
            mZp01C.setSeaHivDate3(zp01Xml.getSeaHivDate3());
            mZp01C.setSeaHivDoc(zp01Xml.getSeaHivDoc());
            mZp01C.setSeaZika(zp01Xml.getSeaZika());
            mZp01C.setSeaZikaDate1(zp01Xml.getSeaZikaDate1());
            mZp01C.setSeaZikaDate2(zp01Xml.getSeaZikaDate2());
            mZp01C.setSeaZikaDate3(zp01Xml.getSeaZikaDate3());
            mZp01C.setSeaZikaDoc(zp01Xml.getSeaZikaDoc());
            mZp01C.setSeaChikung(zp01Xml.getSeaChikung());
            mZp01C.setSeaChikungDate1(zp01Xml.getSeaChikungDate1());
            mZp01C.setSeaChikungDate2(zp01Xml.getSeaChikungDate2());
            mZp01C.setSeaChikungDate3(zp01Xml.getSeaChikungDate3());
            mZp01C.setSeaChikungDoc(zp01Xml.getSeaChikungDoc());
            mZp01C.setSeaInfluInfect(zp01Xml.getSeaInfluInfect());
            mZp01C.setSeaInflueInfectDate1(zp01Xml.getSeaInflueInfectDate1());
            mZp01C.setSeaInfluInfectDate2(zp01Xml.getSeaInfluInfectDate2());
            mZp01C.setSeaInfluInfectDate3(zp01Xml.getSeaInfluInfectDate3());
            mZp01C.setSeaInfluInfectDoc(zp01Xml.getSeaInfluInfectDoc());
            mZp01C.setSeaDengueInfect(zp01Xml.getSeaDengueInfect());
            mZp01C.setSeaDengueInfectDate1(zp01Xml.getSeaDengueInfectDate1());
            mZp01C.setSeaDengueInfectDate2(zp01Xml.getSeaDengueInfectDate2());
            mZp01C.setSeaDengueInfectDate3(zp01Xml.getSeaDengueInfectDate3());
            mZp01C.setSeaDengueInfectDoc(zp01Xml.getSeaDengueInfectDoc());
            mZp01C.setSeaFeverSymptom(zp01Xml.getSeaFeverSymptom());
            mZp01C.setSeaRash(zp01Xml.getSeaRash());
            mZp01C.setSeaItch(zp01Xml.getSeaItch());
            mZp01C.setSeaRashFirst(zp01Xml.getSeaRashFirst());
            mZp01C.setSeaRashDay(zp01Xml.getSeaRashDay());
            mZp01C.setSeaRashMonth(zp01Xml.getSeaRashMonth());
            mZp01C.setSeaRashYear(zp01Xml.getSeaRashYear());
            mZp01C.setSeaRashDuration(zp01Xml.getSeaRashDuration());
            mZp01C.setSeaRashSpread(zp01Xml.getSeaRashSpread());
            mZp01C.setSeaSpreadPart(zp01Xml.getSeaSpreadPart());
            mZp01C.setSeaFeverExperience(zp01Xml.getSeaFeverExperience());
            mZp01C.setSeaTempMeasure(zp01Xml.getSeaTempMeasure());
            mZp01C.setSeaHighTemp(zp01Xml.getSeaHighTemp());
            mZp01C.setSeaHightemUnit(zp01Xml.getSeaHightemUnit());
            mZp01C.setSeaTempunknown(zp01Xml.getSeaTempunknown());
            mZp01C.setSeaFeverDay(zp01Xml.getSeaFeverDay());
            mZp01C.setSeaFeverMonth(zp01Xml.getSeaFeverMonth());
            mZp01C.setSeaFeverYear(zp01Xml.getSeaFeverYear());
            mZp01C.setSeaFeverDuration(zp01Xml.getSeaFeverDuration());
            mZp01C.setSeaFDurationunknown(zp01Xml.getSeaFDurationunknown());
            mZp01C.setSeaRedeyes(zp01Xml.getSeaRedeyes());
            mZp01C.setSeaRedeyesDay(zp01Xml.getSeaRedeyesDay());
            mZp01C.setSeaRedeyesMonth(zp01Xml.getSeaRedeyesMonth());
            mZp01C.setSeaRedeyesYear(zp01Xml.getSeaRedeyesYear());
            mZp01C.setSeaRedeyesDuration(zp01Xml.getSeaRedeyesDuration());
            mZp01C.setSeaOccurSame(zp01Xml.getSeaOccurSame());
            mZp01C.setSeaSameSymptom(zp01Xml.getSeaSameSymptom());
            mZp01C.setSeaJoint(zp01Xml.getSeaJoint());
            mZp01C.setSeaJointDay(zp01Xml.getSeaJointDay());
            mZp01C.setSeaJointMonth(zp01Xml.getSeaJointMonth());
            mZp01C.setSeaJointYear(zp01Xml.getSeaJointYear());
            mZp01C.setSeaJointDuration(zp01Xml.getSeaJointDuration());
            mZp01C.setSeaHeadache(zp01Xml.getSeaHeadache());
            mZp01C.setSeaHeadacheDay(zp01Xml.getSeaHeadacheDay());
            mZp01C.setSeaHeadacheMonth(zp01Xml.getSeaHeadacheMonth());
            mZp01C.setSeaHeadacheYear(zp01Xml.getSeaHeadacheYear());
            mZp01C.setSeaHeadacheDuration(zp01Xml.getSeaHeadacheDuration());
            mZp01C.setSeaSymptomOther(zp01Xml.getSeaSymptomOther());
            mZp01C.setSeaSpecifySymptom(zp01Xml.getSeaSpecifySymptom());
            mZp01C.setSeaOtherSymptom(zp01Xml.getSeaOtherSymptom());
            mZp01C.setSeaMedicare(zp01Xml.getSeaMedicare());
            mZp01C.setSeaCareDay(zp01Xml.getSeaCareDay());
            mZp01C.setSeaCareMonth(zp01Xml.getSeaCareMonth());
            mZp01C.setSeaCareYear(zp01Xml.getSeaCareYear());
            mZp01C.setSeaCareFacility(zp01Xml.getSeaCareFacility());
            mZp01C.setSeaHospitalized(zp01Xml.getSeaHospitalized());
            mZp01C.setSeaHospital(zp01Xml.getSeaHospital());
            mZp01C.setSeaDiagRubella(zp01Xml.getSeaDiagRubella());
            mZp01C.setSeaDiagDengue(zp01Xml.getSeaDiagDengue());
            mZp01C.setSeaDiagChikung(zp01Xml.getSeaDiagChikung());
            mZp01C.setSeaDiagZika(zp01Xml.getSeaDiagZika());
            mZp01C.setSeaDiagCytome(zp01Xml.getSeaDiagCytome());
            mZp01C.setSeaMedicine(zp01Xml.getSeaMedicine());
            mZp01C.setSeaMedcineName(zp01Xml.getSeaMedcineName());
            mZp01C.setSeaGuillainbarre(zp01Xml.getSeaGuillainbarre());
            mZp01C.setSeaTingling(zp01Xml.getSeaTingling());
            mZp01C.setSeaTinglingArm(zp01Xml.getSeaTinglingArm());
            mZp01C.setSeaTinglingLeg(zp01Xml.getSeaTinglingLeg());
            mZp01C.setSeaTinglingHand(zp01Xml.getSeaTinglingHand());
            mZp01C.setSeaTinglingFoot(zp01Xml.getSeaTinglingFoot());
            mZp01C.setSeaTinglingFace(zp01Xml.getSeaTinglingFace());
            mZp01C.setSeaTinglingOther(zp01Xml.getSeaTinglingOther());
            mZp01C.setSeaSensationMin(zp01Xml.getSeaSensationMin());
            mZp01C.setSeaSensationHr(zp01Xml.getSeaSensationHr());
            mZp01C.setSeaSenstaionDay(zp01Xml.getSeaSenstaionDay());
            mZp01C.setSeaInjury(zp01Xml.getSeaInjury());
            mZp01C.setSeaTinglingDay(zp01Xml.getSeaTinglingDay());
            mZp01C.setSeaTinglingMonth(zp01Xml.getSeaTinglingMonth());
            mZp01C.setSeaTinglingYear(zp01Xml.getSeaTinglingYear());
            mZp01C.setSeaTinglingDuration(zp01Xml.getSeaTinglingDuration());
            mZp01C.setSeaNumbness(zp01Xml.getSeaNumbness());
            mZp01C.setSeaNumbArm(zp01Xml.getSeaNumbArm());
            mZp01C.setSeaNumbLeg(zp01Xml.getSeaNumbLeg());
            mZp01C.setSeaNumbHand(zp01Xml.getSeaNumbHand());
            mZp01C.setSeaNumbFoot(zp01Xml.getSeaNumbFoot());
            mZp01C.setSeaNumbFace(zp01Xml.getSeaNumbFace());
            mZp01C.setSeaNumbOther(zp01Xml.getSeaNumbOther());
            mZp01C.setSeaNumbDay(zp01Xml.getSeaNumbDay());
            mZp01C.setSeaNumbMonth(zp01Xml.getSeaNumbMonth());
            mZp01C.setSeaNumbYear(zp01Xml.getSeaNumbYear());
            mZp01C.setSeaNumbDuration(zp01Xml.getSeaNumbDuration());
            mZp01C.setSeaParalysis(zp01Xml.getSeaParalysis());
            mZp01C.setSeaParaArm(zp01Xml.getSeaParaArm());
            mZp01C.setSeaParaLeg(zp01Xml.getSeaParaLeg());
            mZp01C.setSeaParaHand(zp01Xml.getSeaParaHand());
            mZp01C.setSeaParaFoot(zp01Xml.getSeaParaFoot());
            mZp01C.setSeaParaFace(zp01Xml.getSeaParaFace());
            mZp01C.setSeaParaOther(zp01Xml.getSeaParaOther());
            mZp01C.setSeaParaDay(zp01Xml.getSeaParaDay());
            mZp01C.setSeaParaMonth(zp01Xml.getSeaParaMonth());
            mZp01C.setSeaParaYear(zp01Xml.getSeaParaYear());
            mZp01C.setSeaParaDuration(zp01Xml.getSeaParaDuration());
			mZp01C.setRecordDate(new Date());
			mZp01C.setRecordUser(username);
			mZp01C.setIdInstancia(idInstancia);
			mZp01C.setInstancePath(instanceFilePath);
			mZp01C.setEstado(Constants.STATUS_NOT_SUBMITTED);
			mZp01C.setStart(zp01Xml.getStart());
			mZp01C.setEnd(zp01Xml.getEnd());
			mZp01C.setDeviceid(zp01Xml.getDeviceid());
			mZp01C.setSimserial(zp01Xml.getSimserial());
			mZp01C.setPhonenumber(zp01Xml.getPhonenumber());
			mZp01C.setToday(zp01Xml.getToday());
			new SaveDataTask().execute(accion);
			
		} catch (Exception e) {
			// Presenta el error al parsear el xml
			Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
			e.printStackTrace();
			finish();
		}		
	}
	
	// ***************************************
	// Private classes
	// ***************************************
	private class SaveDataTask extends AsyncTask<Integer, Void, String> {
		private Integer accionaRealizar = null;
		@Override
		protected void onPreExecute() {
			// before the request begins, show a progress indicator
			showLoadingProgressDialog();
		}

		@Override
		protected String doInBackground(Integer... values) {
			accionaRealizar = values[0];
			try {
				zipA.open();
				if (accionaRealizar == ADD_ZP01E_ODK){
					zipA.crearZpo01StudyEntrySectionC(mZp01C);
				}
				else{
					zipA.editarZpo01StudyEntrySectionC(mZp01C);
				}
				zipA.close();
			} catch (Exception e) {
				Log.e(TAG, e.getLocalizedMessage(), e);
				return "error";
			}
			return "exito";
		}

		protected void onPostExecute(String resultado) {
			// after the network request completes, hide the progress indicator
			dismissProgressDialog();
			showResult(resultado);
		}

	}

	// ***************************************
	// Private methods
	// ***************************************
	private void showResult(String resultado) {
		Toast.makeText(getApplicationContext(),	resultado, Toast.LENGTH_LONG).show();
		finish();
	}	


}
