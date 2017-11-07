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
import ni.org.ics.zpo.domain.Zpo01StudyEntrySectionAtoB;
import ni.org.ics.zpo.appmovil.parsers.Zpo01StudyEntrySectionAtoBXml;
import ni.org.ics.zpo.appmovil.preferences.PreferencesActivity;
import ni.org.ics.zpo.appmovil.utils.Constants;
import ni.org.ics.zpo.appmovil.utils.FileUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.util.Date;


public class NewZpo01StudyEntrySectionAtoBActivity extends AbstractAsyncActivity {

	protected static final String TAG = NewZpo01StudyEntrySectionAtoBActivity.class.getSimpleName();
	
	private ZpoAdapter zipA;
	private static Zpo01StudyEntrySectionAtoB mZp01A = null;
	
	public static final int ADD_ZP01A_ODK = 1;
	public static final int EDIT_ZP01A_ODK = 2;

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
		mZp01A = (Zpo01StudyEntrySectionAtoB) getIntent().getExtras().getSerializable(Constants.OBJECTO_ZP01A);
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
		if (mZp01A!=null){
			message.setText(getString(R.string.edit)+ " " + getString(R.string.maternal_b_1)+"?");
		}
		else{
			message.setText(getString(R.string.add)+ " " + getString(R.string.maternal_b_1)+"?");
		}

		//add some action to the buttons

		Button yes = (Button) dialogInit.findViewById(R.id.yesnoYes);
		yes.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				dialogInit.dismiss();
				addZp01a();
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
		if(requestCode == ADD_ZP01A_ODK||requestCode == EDIT_ZP01A_ODK) {
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
					parseZp01(idInstancia,instanceFilePath,accion);
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
	private void addZp01a() {
		try{
			Uri formUri;
			if(mZp01A==null){
				//campos de proveedor de collect
				String[] projection = new String[] {
						"_id","jrFormId","displayName"};
				//cursor que busca el formulario
				Cursor c = getContentResolver().query(Constants.CONTENT_URI, projection,
						"jrFormId = 'zpo01_study_entry_a_b' and displayName = 'Estudio ZPO Visita inicial en el estudio_A_B'", null, null);
				c.moveToFirst();
				//captura el id del formulario
				Integer id = Integer.parseInt(c.getString(0));
				//cierra el cursor
				if (c != null) {
					c.close();
				}
				//forma el uri para ODK Collect
				formUri = ContentUris.withAppendedId(Constants.CONTENT_URI,id);
				//Arranca la actividad ODK Collect en busca de resultado
	        	accion = ADD_ZP01A_ODK;
			}
			else{
				//forma el uri para la instancia en ODK Collect
				Integer id = mZp01A.getIdInstancia();
				formUri = ContentUris.withAppendedId(Constants.CONTENT_URI_I,id);
				accion = EDIT_ZP01A_ODK;
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
	
	private void parseZp01(Integer idInstancia, String instanceFilePath, Integer accion) {
		Serializer serializer = new Persister();
		File source = new File(instanceFilePath);
		try {
			Zpo01StudyEntrySectionAtoBXml zp01Xml = new Zpo01StudyEntrySectionAtoBXml();
			zp01Xml = serializer.read(Zpo01StudyEntrySectionAtoBXml.class, source);
			if (accion==ADD_ZP01A_ODK) mZp01A = new Zpo01StudyEntrySectionAtoB();
            mZp01A.setRecordId(mRecordId);
            mZp01A.setEventName(Constants.ENTRY);
            mZp01A.setSeaVdate(zp01Xml.getSeaVdate());
            mZp01A.setSeaBirthdate(zp01Xml.getSeaBirthdate());
            mZp01A.setSeaPregnantBefore(zp01Xml.getSeaPregnantBefore());
            mZp01A.setSeaPregStillDeliAfter(zp01Xml.getSeaPregStillDeliAfter());
            mZp01A.setSeaCity(zp01Xml.getSeaCity());
            mZp01A.setSeaState(zp01Xml.getSeaState());
            mZp01A.setSeaCountry(zp01Xml.getSeaCountry());
            mZp01A.setSeaLive(zp01Xml.getSeaLive());
            mZp01A.setSeaAgeLeave(zp01Xml.getSeaAgeLeave());
            mZp01A.setSeaLeavena(zp01Xml.getSeaLeavena());
            mZp01A.setSeaMstatus(zp01Xml.getSeaMstatus());
            mZp01A.setSeaRace(zp01Xml.getSeaRace());
            mZp01A.setSeaEthnicityOther(zp01Xml.getSeaEthnicityOther());
            mZp01A.setSeaDegreeYou(zp01Xml.getSeaDegreeYou());
            mZp01A.setSeaYdegreeYears(zp01Xml.getSeaYdegreeYears());
            mZp01A.setSeaDegreeSpouse(zp01Xml.getSeaDegreeSpouse());
            mZp01A.setSeaSdegreeYears(zp01Xml.getSeaSdegreeYears());

			mZp01A.setRecordDate(new Date());
			mZp01A.setRecordUser(username);
			mZp01A.setIdInstancia(idInstancia);
			mZp01A.setInstancePath(instanceFilePath);
			mZp01A.setEstado(Constants.STATUS_NOT_SUBMITTED);
			mZp01A.setStart(zp01Xml.getStart());
			mZp01A.setEnd(zp01Xml.getEnd());
			mZp01A.setDeviceid(zp01Xml.getDeviceid());
			mZp01A.setSimserial(zp01Xml.getSimserial());
			mZp01A.setPhonenumber(zp01Xml.getPhonenumber());
			mZp01A.setToday(zp01Xml.getToday());
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
				if (accionaRealizar == ADD_ZP01A_ODK){
					zipA.crearZpo01StudyEntrySectionAtoB(mZp01A);
				}
				else{
					zipA.editarZpo01StudyEntrySectionAtoB(mZp01A);
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
