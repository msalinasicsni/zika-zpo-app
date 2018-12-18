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
import ni.org.ics.zpo.appmovil.parsers.Zpo04ExtendedSectionAtoFXml;
import ni.org.ics.zpo.appmovil.preferences.PreferencesActivity;
import ni.org.ics.zpo.appmovil.utils.Constants;
import ni.org.ics.zpo.appmovil.utils.FileUtils;
import ni.org.ics.zpo.domain.Zpo04ExtendedSectionAtoF;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.util.Date;


public class NewZpo04ExtendedSectionAtoFActivity extends AbstractAsyncActivity {

	protected static final String TAG = NewZpo04ExtendedSectionAtoFActivity.class.getSimpleName();
	
	private ZpoAdapter zipA;
	private static Zpo04ExtendedSectionAtoF mZp04AF = null;
	
	public static final int ADD_ZP04A_ODK = 1;
	public static final int EDIT_ZP04A_ODK = 2;

	Dialog dialogInit;
	private SharedPreferences settings;
	private String username;
	private String mRecordId = "";
	private Integer accion = 0;
	private String event;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (!FileUtils.storageReady()) {
			Toast toast = Toast.makeText(getApplicationContext(),getString(R.string.error)  + "," + getString(R.string.storage_error),Toast.LENGTH_LONG);
			toast.show();
			finish();
		}
		settings =
				PreferenceManager.getDefaultSharedPreferences(this);
		username =
				settings.getString(PreferencesActivity.KEY_USERNAME,
						null);
		String mPass = ((MyZpoApplication) this.getApplication()).getPassApp();
		zipA = new ZpoAdapter(this.getApplicationContext(),mPass,false,false);
        mZp04AF = (Zpo04ExtendedSectionAtoF) getIntent().getExtras().getSerializable(Constants.OBJECTO_ZP04AF);
        mRecordId = getIntent().getExtras().getString(Constants.RECORDID);
        event = getIntent().getExtras().getString(Constants.EVENT);
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
		if (mZp04AF !=null){
			message.setText(getString(R.string.edit)+ " " + getString(R.string.maternal_b_6)+"?");
		}
		else{
			message.setText(getString(R.string.add)+ " " + getString(R.string.maternal_b_6)+"?");
		}

		//add some action to the buttons

		Button yes = (Button) dialogInit.findViewById(R.id.yesnoYes);
		yes.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				dialogInit.dismiss();
				addTrimesterVisit();
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
		if(requestCode == ADD_ZP04A_ODK||requestCode == EDIT_ZP04A_ODK) {
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
					parseTrimesterVisit(idInstancia,instanceFilePath,accion);
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
	private void addTrimesterVisit() {
		try{
			Uri formUri;
			if(mZp04AF ==null){
				//campos de proveedor de collect
				String[] projection = new String[] {
						"_id","jrFormId","displayName"};
				//cursor que busca el formulario
				Cursor c = getContentResolver().query(Constants.CONTENT_URI, projection,
						"jrFormId = 'zpo04_extended_a_f' and displayName = 'Estudio ZPO Cuestionario Extendido_A_F'", null, null);
				c.moveToFirst();
				//captura el id del formulario
				Integer id = Integer.parseInt(c.getString(0));
				//cierra el cursor
				if (c != null) {
					c.close();
				}
				//forma el uri para ODK Collect
				formUri = ContentUris.withAppendedId(Constants.CONTENT_URI,id);
				accion = ADD_ZP04A_ODK;
			}
			else{
				//forma el uri para la instancia en ODK Collect
				Integer id = mZp04AF.getIdInstancia();
				formUri = ContentUris.withAppendedId(Constants.CONTENT_URI_I,id);
				accion = EDIT_ZP04A_ODK;
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
	
	private void parseTrimesterVisit(Integer idInstancia, String instanceFilePath, Integer accion) {
		Serializer serializer = new Persister();
		File source = new File(instanceFilePath);
		try {
			Zpo04ExtendedSectionAtoFXml zp04Xml = new Zpo04ExtendedSectionAtoFXml();
			zp04Xml = serializer.read(Zpo04ExtendedSectionAtoFXml.class, source);
			if (accion==ADD_ZP04A_ODK) mZp04AF = new Zpo04ExtendedSectionAtoF();
			mZp04AF.setRecordId(mRecordId);
			mZp04AF.setEventName(event);
			mZp04AF.setTriDov(zp04Xml.getTriDov());
			mZp04AF.setTriPrimJobInd(zp04Xml.getTriPrimJobInd());
			mZp04AF.setTriPrimJobHours(zp04Xml.getTriPrimJobHours());
			mZp04AF.setTriHouseSitInd(zp04Xml.getTriHouseSitInd());
			mZp04AF.setTriCity(zp04Xml.getTriCity());
			mZp04AF.setTriState(zp04Xml.getTriState());
			mZp04AF.setTriCountry(zp04Xml.getTriCountry());
			mZp04AF.setTriResidRef(zp04Xml.getTriResidRef());
			mZp04AF.setTriCurrResidDur(zp04Xml.getTriCurrResidDur());
			mZp04AF.setTriCurrResidDurRef(zp04Xml.getTriCurrResidDurRef());
			mZp04AF.setTriNbhoodTyp(zp04Xml.getTriNbhoodTyp());
			mZp04AF.setTriResidTyp(zp04Xml.getTriResidTyp());
			mZp04AF.setTriResidTypSpecify(zp04Xml.getTriResidTypSpecify());
			mZp04AF.setTriFloorMat(zp04Xml.getTriFloorMat());
			mZp04AF.setTriFloorMatSpecify(zp04Xml.getTriFloorMatSpecify());
			mZp04AF.setTriWallMat(zp04Xml.getTriWallMat());
			mZp04AF.setTriWallMatSpecify(zp04Xml.getTriWallMatSpecify());
			mZp04AF.setTriRoofMat(zp04Xml.getTriRoofMat());
			mZp04AF.setTriRoofMatSpecify(zp04Xml.getTriRoofMatSpecify());
			mZp04AF.setTriTrashDispos(zp04Xml.getTriTrashDispos());
			mZp04AF.setTriTrashDisposSpecify(zp04Xml.getTriTrashDisposSpecify());
			mZp04AF.setTriNumTotalRooms(zp04Xml.getTriNumTotalRooms());
			mZp04AF.setTriNumSleepRooms(zp04Xml.getTriNumSleepRooms());
			mZp04AF.setTriNumPeople(zp04Xml.getTriNumPeople());
			mZp04AF.setTriScreensInd(zp04Xml.getTriScreensInd());
			mZp04AF.setTriHouseAmenities(zp04Xml.getTriHouseAmenities());
			mZp04AF.setTriTransAccess(zp04Xml.getTriTransAccess());
			mZp04AF.setTriPrimWaterSrc(zp04Xml.getTriPrimWaterSrc());
			mZp04AF.setTriWaterContainInd(zp04Xml.getTriWaterContainInd());
			mZp04AF.setTriWaterContainTyp(zp04Xml.getTriWaterContainTyp());
			mZp04AF.setTriWaterConSpecify(zp04Xml.getTriWaterConSpecify());
			mZp04AF.setTriWaterTreatHome(zp04Xml.getTriWaterTreatHome());
			mZp04AF.setTriWaterTreatFreq(zp04Xml.getTriWaterTreatFreq());
			mZp04AF.setTriToiletTyp(zp04Xml.getTriToiletTyp());
			mZp04AF.setTriOpSewageInd(zp04Xml.getTriOpSewageInd());
			mZp04AF.setTriAnimalsInd(zp04Xml.getTriAnimalsInd());
			mZp04AF.setTriAnimalTyp(zp04Xml.getTriAnimalTyp());//multiple
			mZp04AF.setTriAnimalSpecify(zp04Xml.getTriAnimalSpecify());
			mZp04AF.setTriNumOtherAnimal(zp04Xml.getTriNumOtherAnimal());
			mZp04AF.setTriNumCattle(zp04Xml.getTriNumCattle());
			mZp04AF.setTriNumPig(zp04Xml.getTriNumPig());
			mZp04AF.setTriNumFowl(zp04Xml.getTriNumFowl());
			mZp04AF.setTriNumGoatsSheep(zp04Xml.getTriNumGoatsSheep());
			mZp04AF.setTriSmokeInd(zp04Xml.getTriSmokeInd());
			mZp04AF.setTriDrinkInd(zp04Xml.getTriDrinkInd());
			mZp04AF.setTriDrinkEverInd(zp04Xml.getTriDrinkEverInd());
			mZp04AF.setTriBugNuisInd(zp04Xml.getTriBugNuisInd());
			mZp04AF.setTriPestStorHomeInd(zp04Xml.getTriPestStorHomeInd());
			mZp04AF.setTriPestAppHomeInd(zp04Xml.getTriPestAppHomeInd());
			mZp04AF.setTriPestAppDay(zp04Xml.getTriPestAppDay());
			mZp04AF.setTriPestAppMonth(zp04Xml.getTriPestAppMonth());
			mZp04AF.setTriPestAppYear(zp04Xml.getTriPestAppYear());
			mZp04AF.setTriPestAppName(zp04Xml.getTriPestAppName());
			mZp04AF.setTriHomeTrtdInsctInd(zp04Xml.getTriHomeTrtdInsctInd());
			mZp04AF.setTriHomeTrtdLoc(zp04Xml.getTriHomeTrtdLoc());
			mZp04AF.setTriHomeTrtdEntity(zp04Xml.getTriHomeTrtdEntity());
			mZp04AF.setTriHomeTrtdNames(zp04Xml.getTriHomeTrtdNames());
			mZp04AF.setTriTrtmntAppDay(zp04Xml.getTriTrtmntAppDay());
			mZp04AF.setTriTrtmntAppMonth(zp04Xml.getTriTrtmntAppMonth());
			mZp04AF.setTriTrtmntAppYear(zp04Xml.getTriTrtmntAppYear());
			mZp04AF.setTriLwnTrtmntAppInd(zp04Xml.getTriLwnTrtmntAppInd());
			mZp04AF.setTriLwnTrtmntAppDay(zp04Xml.getTriLwnTrtmntAppDay());
			mZp04AF.setTriLwnTrtmntAppMonth(zp04Xml.getTriLwnTrtmntAppMonth());
			mZp04AF.setTriLwnTrtmntAppYear(zp04Xml.getTriLwnTrtmntAppYear());
			mZp04AF.setTriLwnTrtmntAppName(zp04Xml.getTriLwnTrtmntAppName());
			mZp04AF.setTriMosqRepInd(zp04Xml.getTriMosqRepInd());
			mZp04AF.setTriMosqRepTyp(zp04Xml.getTriMosqRepTyp());
			mZp04AF.setTriMosqRepNameSpray(zp04Xml.getTriMosqRepNameSpray());
			mZp04AF.setTriMosqRepDkSpray(zp04Xml.getTriMosqRepDkSpray());
			mZp04AF.setTriMosqRepNameLotion(zp04Xml.getTriMosqRepNameLotion());
			mZp04AF.setTriMosqRepDkLotion(zp04Xml.getTriMosqRepDkLotion());
			mZp04AF.setTriMosqRepNameSpiral(zp04Xml.getTriMosqRepNameSpiral());
			mZp04AF.setTriMosqRepDkSpiral(zp04Xml.getTriMosqRepDkSpiral());
			mZp04AF.setTriMosqRepNamePlugin(zp04Xml.getTriMosqRepNamePlugin());
			mZp04AF.setTriMosqRepDkPlugin(zp04Xml.getTriMosqRepDkPlugin());
			mZp04AF.setTriMosqRepNameOther(zp04Xml.getTriMosqRepNameOther());
			mZp04AF.setTriMosqRepDkOther(zp04Xml.getTriMosqRepDkOther());
			mZp04AF.setTriCompId(username);
			mZp04AF.setTriCompDat(new Date());
			mZp04AF.setTriRevId(username);
			mZp04AF.setTriRevDat(new Date());
			mZp04AF.setTriEntId(username);
			mZp04AF.setTriEntDat(new Date());
			mZp04AF.setRecordDate(new Date());
			mZp04AF.setRecordUser(username);
			mZp04AF.setIdInstancia(idInstancia);
			mZp04AF.setInstancePath(instanceFilePath);
			mZp04AF.setEstado(Constants.STATUS_NOT_SUBMITTED);
			mZp04AF.setStart(zp04Xml.getStart());
			mZp04AF.setEnd(zp04Xml.getEnd());
			mZp04AF.setDeviceid(zp04Xml.getDeviceid());
			mZp04AF.setSimserial(zp04Xml.getSimserial());
			mZp04AF.setPhonenumber(zp04Xml.getPhonenumber());
			mZp04AF.setToday(zp04Xml.getToday());
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
				if (accionaRealizar == ADD_ZP04A_ODK){
					zipA.crearZpo04ExtendedSectionAtoF( mZp04AF );
				}
				else{
					zipA.editarZpo04ExtendedSectionAtoF( mZp04AF );
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
