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
import ni.org.ics.zpo.appmovil.parsers.Zpo01StudyEntrySectionDtoFXml;
import ni.org.ics.zpo.appmovil.preferences.PreferencesActivity;
import ni.org.ics.zpo.appmovil.utils.Constants;
import ni.org.ics.zpo.appmovil.utils.FileUtils;
import ni.org.ics.zpo.domain.Zpo01StudyEntrySectionDtoF;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.util.Date;


public class NewZpo01StudyEntrySectionDActivity extends AbstractAsyncActivity {

	protected static final String TAG = NewZpo01StudyEntrySectionDActivity.class.getSimpleName();
	
	private ZpoAdapter zpoA;
	private static Zpo01StudyEntrySectionDtoF mZp01D = null;

	public static final int ADD_ZP01F_ODK = 1;

	Dialog dialogInit;
	private SharedPreferences settings;
	private String username;
	private String mRecordId = "";
	private Integer accion = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (!FileUtils.storageReady()) {
			Toast toast = Toast.makeText(getApplicationContext(),getString(R.string.error) + getString(R.string.storage_error),Toast.LENGTH_LONG);
			toast.show();
			finish();
		}
		settings =
				PreferenceManager.getDefaultSharedPreferences(this);
		username =
				settings.getString(PreferencesActivity.KEY_USERNAME,
						null);
		String mPass = ((MyZpoApplication) this.getApplication()).getPassApp();
		mZp01D = (Zpo01StudyEntrySectionDtoF) getIntent().getExtras().getSerializable(Constants.OBJECTO_ZP01F);
        mRecordId = getIntent().getExtras().getString(Constants.RECORDID);
        zpoA = new ZpoAdapter(this.getApplicationContext(),mPass,false,false);
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

		message.setText(getString(R.string.edit)+ " " + getString(R.string.maternal_b_3)+"?");


		//add some action to the buttons

		Button yes = (Button) dialogInit.findViewById(R.id.yesnoYes);
		yes.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				dialogInit.dismiss();
				addZp01StudyEntryDtoF();
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
		if(requestCode == ADD_ZP01F_ODK) {
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
					parseZp01StudyEntryD(idInstancia, instanceFilePath,accion);
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
	private void addZp01StudyEntryDtoF() {
		try{
			Uri formUri;
				//campos de proveedor de collect
				String[] projection = new String[] {
						"_id","jrFormId","displayName"};
				//cursor que busca el formulario
				Cursor c = getContentResolver().query(Constants.CONTENT_URI, projection,
						"jrFormId = 'zpo01_study_entry_d' and displayName = 'Estudio ZPO Visita inicial en el estudio_D'", null, null);
				c.moveToFirst();
				//captura el id del formulario
				Integer id = Integer.parseInt(c.getString(0));
				//cierra el cursor
				if (c != null) {
					c.close();
				}
				//forma el uri para ODK Collect
				formUri = ContentUris.withAppendedId(Constants.CONTENT_URI,id);
				accion = ADD_ZP01F_ODK;


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
	
	private void parseZp01StudyEntryD(Integer idInstancia, String instanceFilePath, Integer accion) {
		Serializer serializer = new Persister();
		File source = new File(instanceFilePath);
		try {
			Zpo01StudyEntrySectionDtoFXml zp01Xml = new Zpo01StudyEntrySectionDtoFXml();
			zp01Xml = serializer.read(Zpo01StudyEntrySectionDtoFXml.class, source);
			mZp01D.setSeaFirstPreg("0");
            mZp01D.setSeaAnemia(zp01Xml.getSeaAnemia());
            mZp01D.setSeaVaginal(zp01Xml.getSeaVaginal());
            mZp01D.setSeaUtiPrior(zp01Xml.getSeaUtiPrior());
            mZp01D.setSeaSexually(zp01Xml.getSeaSexually());
            mZp01D.setSeaPreterm(zp01Xml.getSeaPreterm());
            mZp01D.setSeaPreeclampsia(zp01Xml.getSeaPreeclampsia());
            mZp01D.setSeaEclampsia(zp01Xml.getSeaEclampsia());
            mZp01D.setSeaHeart(zp01Xml.getSeaHeart());
            mZp01D.setSeaNeuropathy(zp01Xml.getSeaNeuropathy());
            mZp01D.setSeaGestational(zp01Xml.getSeaGestational());
            mZp01D.setSeaTotalPreg(zp01Xml.getSeaTotalPreg());
            mZp01D.setSeaDeliveryDate1(zp01Xml.getSeaDeliveryDate1());
            mZp01D.setSeaGage1(zp01Xml.getSeaGage1());
            mZp01D.setSeaOutcome1(zp01Xml.getSeaOutcome1());
            mZp01D.setSeaBdefects1(zp01Xml.getSeaBdefects1());
            mZp01D.setSeaDeliveryDate2(zp01Xml.getSeaDeliveryDate2());
            mZp01D.setSeaGage2(zp01Xml.getSeaGage2());
            mZp01D.setSeaOutcome2(zp01Xml.getSeaOutcome2());
            mZp01D.setSeaBdefects2(zp01Xml.getSeaBdefects2());
            mZp01D.setSeaDeliveryDate3(zp01Xml.getSeaDeliveryDate3());
            mZp01D.setSeaGage3(zp01Xml.getSeaGage3());
            mZp01D.setSeaOutcome3(zp01Xml.getSeaOutcome3());
            mZp01D.setSeaBdefects3(zp01Xml.getSeaBdefects3());
            mZp01D.setSeaDeliveryDate4(zp01Xml.getSeaDeliveryDate4());
            mZp01D.setSeaGage4(zp01Xml.getSeaGage4());
            mZp01D.setSeaOutcome4(zp01Xml.getSeaOutcome4());
            mZp01D.setSeaBdefects4(zp01Xml.getSeaBdefects4());
            mZp01D.setSeaDeliveryDate5(zp01Xml.getSeaDeliveryDate5());
            mZp01D.setSeaGage5(zp01Xml.getSeaGage5());
            mZp01D.setSeaOutcome5(zp01Xml.getSeaOutcome5());
            mZp01D.setSeaBdefects5(zp01Xml.getSeaBdefects5());
            mZp01D.setSeaDeliveryDate6(zp01Xml.getSeaDeliveryDate6());
            mZp01D.setSeaGage6(zp01Xml.getSeaGage6());
            mZp01D.setSeaOutcome6(zp01Xml.getSeaOutcome6());
            mZp01D.setSeaBdefects6(zp01Xml.getSeaBdefects6());

            mZp01D.setSeaIdCompleting(username);
            mZp01D.setSeaDateCompleted(new Date());
            mZp01D.setSeaIdReviewer(username);
            mZp01D.setSeaDateReviewed(new Date());
            mZp01D.setSeaIdDataEntry(username);
            mZp01D.setSeaDateEntered(new Date());

			mZp01D.setRecordDate(new Date());
			mZp01D.setRecordUser(username);
			mZp01D.setEstado(Constants.STATUS_NOT_SUBMITTED);
			mZp01D.setStart(zp01Xml.getStart());
			mZp01D.setEnd(zp01Xml.getEnd());
			mZp01D.setDeviceid(zp01Xml.getDeviceid());
			mZp01D.setSimserial(zp01Xml.getSimserial());
			mZp01D.setPhonenumber(zp01Xml.getPhonenumber());
			mZp01D.setToday(zp01Xml.getToday());

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
				zpoA.open();
				zpoA.editarZpo01StudyEntrySectionDtoF(mZp01D);
				zpoA.close();
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
