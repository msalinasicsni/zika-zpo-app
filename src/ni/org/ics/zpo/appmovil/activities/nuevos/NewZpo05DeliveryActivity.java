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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import ni.org.ics.zpo.appmovil.AbstractAsyncActivity;
import ni.org.ics.zpo.appmovil.MainActivity;
import ni.org.ics.zpo.appmovil.MyZpoApplication;
import ni.org.ics.zpo.appmovil.R;
import ni.org.ics.zpo.appmovil.database.ZpoAdapter;
import ni.org.ics.zpo.domain.Zpo05Delivery;
import ni.org.ics.zpo.domain.ZpoEstadoInfante;
import ni.org.ics.zpo.domain.ZpoInfantData;
import ni.org.ics.zpo.appmovil.parsers.Zpo05DeliveryXml;
import ni.org.ics.zpo.appmovil.preferences.PreferencesActivity;
import ni.org.ics.zpo.appmovil.utils.Constants;
import ni.org.ics.zpo.appmovil.utils.FileUtils;
import ni.org.ics.zpo.appmovil.utils.MainDBConstants;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.util.Date;

/**
 * Created by FIRSTICT on 10/31/2016.
 * V1.0
 */
public class NewZpo05DeliveryActivity extends AbstractAsyncActivity {

    protected static final String TAG = NewZpo05DeliveryActivity.class.getSimpleName();

    private ZpoAdapter zipA;
    private static Zpo05Delivery mDelivery = null;
    private static ZpoInfantData mZpoInfantData1 = null;
    private static ZpoInfantData mZpoInfantData2 = null;
    private static ZpoInfantData mZpoInfantData3 = null;
    private static ZpoEstadoInfante mZpoEstadoInfante1 = null;
    private static ZpoEstadoInfante mZpoEstadoInfante2 = null;
    private static ZpoEstadoInfante mZpoEstadoInfante3 = null;

    public static final int ADD_ZP06_ODK = 1;
	public static final int EDIT_ZP06_ODK = 2;

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
        zipA = new ZpoAdapter(this.getApplicationContext(),mPass,false,false);
        mRecordId = getIntent().getExtras().getString(Constants.RECORDID);
        mDelivery = (Zpo05Delivery) getIntent().getExtras().getSerializable(Constants.OBJECTO_ZP06);
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
        if (mDelivery!=null){
            message.setText(getString(R.string.edit)+ " " + getString(R.string.maternal_b_10)+"?");
        }
        else{
            message.setText(getString(R.string.add)+ " " + getString(R.string.maternal_b_10)+"?");
        }

        //add some action to the buttons

        Button yes = (Button) dialogInit.findViewById(R.id.yesnoYes);
        yes.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                dialogInit.dismiss();
                addZpo05Delivery();
            }
        });

        Button no = (Button) dialogInit.findViewById(R.id.yesnoNo);
        no.setOnClickListener(new View.OnClickListener() {

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
    	if(requestCode == ADD_ZP06_ODK||requestCode == EDIT_ZP06_ODK) {
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
                    parseZpo05Delivery(idInstancia, instanceFilePath, accion);
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

    private void addZpo05Delivery() {
        try{
        	Uri formUri;
			if(mDelivery==null){
	            //campos de proveedor de collect
	            String[] projection = new String[] {
	                    "_id","jrFormId","displayName"};
	            //cursor que busca el formulario
	            Cursor c = getContentResolver().query(Constants.CONTENT_URI, projection,
	                    "jrFormId = 'zpo05_delivery' and displayName = 'Estudio ZPO Parto'", null, null);
	            c.moveToFirst();
	            //captura el id del formulario
	            Integer id = Integer.parseInt(c.getString(0));
	            //cierra el cursor
	            if (c != null) {
	                c.close();
	            }
	            //forma el uri para ODK Collect
	            formUri = ContentUris.withAppendedId(Constants.CONTENT_URI, id);
	            accion = ADD_ZP06_ODK;
			}
			else{
				//forma el uri para la instancia en ODK Collect
				Integer id = mDelivery.getIdInstancia();
				formUri = ContentUris.withAppendedId(Constants.CONTENT_URI_I,id);
				accion = EDIT_ZP06_ODK;
			}
            //Arranca la actividad ODK Collect en busca de resultado
            Intent odkA =  new Intent(Intent.ACTION_EDIT,formUri);
            startActivityForResult(odkA, accion);
        }
        catch(Exception e){
            //No existe el formulario en el equipo
            Log.e(TAG, e.getMessage(), e);
            Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void parseZpo05Delivery(Integer idInstancia, String instanceFilePath, Integer accion) {
        Serializer serializer = new Persister();
        File source = new File(instanceFilePath);
        try {
            Zpo05DeliveryXml zp06Xml = serializer.read(Zpo05DeliveryXml.class, source);
            if (accion==ADD_ZP06_ODK) mDelivery = new Zpo05Delivery();
            mDelivery.setRecordId(mRecordId);
            mDelivery.setEventName(event);

            mDelivery.setDeliVisitDate(zp06Xml.getDeliVisitDate());
            mDelivery.setDeliOriginInfo(zp06Xml.getDeliOriginInfo());
            mDelivery.setDeliMotherWt(zp06Xml.getDeliMotherWt());
            mDelivery.setDeliWtUnit(zp06Xml.getDeliWtUnit());
            mDelivery.setDeliSystolic(zp06Xml.getDeliSystolic());
            mDelivery.setDeliDiastolic(zp06Xml.getDeliDiastolic());
            mDelivery.setDeliTemperature(zp06Xml.getDeliTemperature());
            mDelivery.setDeliTempUnit(zp06Xml.getDeliTempUnit());
            mDelivery.setDeliDeliveryDate(zp06Xml.getDeliDeliveryDate());
            mDelivery.setDeliMode(zp06Xml.getDeliMode());
            mDelivery.setDeliDeliveryWho(zp06Xml.getDeliDeliveryWho());
            mDelivery.setDeliDeliveryOccur(zp06Xml.getDeliDeliveryOccur());
            mDelivery.setDeliHospitalId(zp06Xml.getDeliHospitalId());
            mDelivery.setDeliClinicId(zp06Xml.getDeliClinicId());
            mDelivery.setDeliDeliveryOther(zp06Xml.getDeliDeliveryOther());
            mDelivery.setDeliNumBirth(zp06Xml.getDeliNumBirth());
            mDelivery.setDeliFetalOutcome1(zp06Xml.getDeliFetalOutcome1());
            mDelivery.setDeliCauseDeath1(zp06Xml.getDeliCauseDeath1());
            mDelivery.setDeliSexBaby1(zp06Xml.getDeliSexBaby1());
            mDelivery.setDeliFetalOutcome2(zp06Xml.getDeliFetalOutcome2());
            mDelivery.setDeliCauseDeath2(zp06Xml.getDeliCauseDeath2());
            mDelivery.setDeliSexBaby2(zp06Xml.getDeliSexBaby2());
            mDelivery.setDeliFetalOutcome3(zp06Xml.getDeliFetalOutcome3());
            mDelivery.setDeliCauseDeath3(zp06Xml.getDeliCauseDeath3());
            mDelivery.setDeliSexBaby3(zp06Xml.getDeliSexBaby3());
            mDelivery.setDeliConsentInfant(zp06Xml.getDeliConsentInfant());
            mDelivery.setDeliReasonNoconsent(zp06Xml.getDeliReasonNoconsent());
            mDelivery.setDeliNoconsentOther(zp06Xml.getDeliNoconsentOther());
            mDelivery.setDeliIdCompleting(username);
            mDelivery.setDeliDateCompleted(new Date());
            mDelivery.setDeliIdReviewer(username);
            mDelivery.setDeliDateReviewed(new Date());
            mDelivery.setDeliIdDataEntry(username);
            mDelivery.setDeliDateEntered(new Date());
            
            mDelivery.setDeliHyperDisease(zp06Xml.getDeliHyperDisease());
            mDelivery.setDeliPreterm1(zp06Xml.getDeliPreterm1());
            mDelivery.setDeliPreterm2(zp06Xml.getDeliPreterm2());
            mDelivery.setDeliPreterm3(zp06Xml.getDeliPreterm3());
            mDelivery.setDeliDeliverEarly(zp06Xml.getDeliDeliverEarly());

            mDelivery.setRecordDate(new Date());
            mDelivery.setRecordUser(username);
            mDelivery.setIdInstancia(idInstancia);
            mDelivery.setInstancePath(instanceFilePath);
            mDelivery.setEstado(Constants.STATUS_NOT_SUBMITTED);
            mDelivery.setStart(zp06Xml.getStart());
            mDelivery.setEnd(zp06Xml.getEnd());
            mDelivery.setDeviceid(zp06Xml.getDeviceid());
            mDelivery.setSimserial(zp06Xml.getSimserial());
            mDelivery.setPhonenumber(zp06Xml.getPhonenumber());
            mDelivery.setToday(zp06Xml.getToday());
            
            if(event.matches(Constants.ENTRY) && !zp06Xml.getDeliMode().equals("4")){
                if(zp06Xml.getDeliNumBirth().matches("1")){
                	mZpoInfantData1 = completarDatosInfante(1);
                	mZpoEstadoInfante1 = new ZpoEstadoInfante(mZpoInfantData1.getRecordId(), '0', '0', '0', new Date(),
                			username,'0',idInstancia,instanceFilePath,Constants.STATUS_NOT_SUBMITTED,zp06Xml.getStart(),
                			zp06Xml.getEnd(),zp06Xml.getDeviceid(),zp06Xml.getSimserial(),zp06Xml.getPhonenumber(),zp06Xml.getToday());
                }
                else if(zp06Xml.getDeliNumBirth().matches("2")){
                	mZpoInfantData1 = completarDatosInfante(1);
                	mZpoInfantData2 = completarDatosInfante(2);
                	mZpoEstadoInfante1 = new ZpoEstadoInfante(mZpoInfantData1.getRecordId(), '0', '0', '0', new Date(),
                			username,'0',idInstancia,instanceFilePath,Constants.STATUS_NOT_SUBMITTED,zp06Xml.getStart(),
                			zp06Xml.getEnd(),zp06Xml.getDeviceid(),zp06Xml.getSimserial(),zp06Xml.getPhonenumber(),zp06Xml.getToday());
                	mZpoEstadoInfante2 = new ZpoEstadoInfante(mZpoInfantData2.getRecordId(), '0', '0', '0', new Date(),
                			username,'0',idInstancia,instanceFilePath,Constants.STATUS_NOT_SUBMITTED,zp06Xml.getStart(),
                			zp06Xml.getEnd(),zp06Xml.getDeviceid(),zp06Xml.getSimserial(),zp06Xml.getPhonenumber(),zp06Xml.getToday());
                }else{
                	mZpoInfantData1 = completarDatosInfante(1);
                	mZpoInfantData2 = completarDatosInfante(2);
                	mZpoInfantData3 = completarDatosInfante(3);
                	mZpoEstadoInfante1 = new ZpoEstadoInfante(mZpoInfantData1.getRecordId(), '0', '0', '0', new Date(),
                			username,'0',idInstancia,instanceFilePath,Constants.STATUS_NOT_SUBMITTED,zp06Xml.getStart(),
                			zp06Xml.getEnd(),zp06Xml.getDeviceid(),zp06Xml.getSimserial(),zp06Xml.getPhonenumber(),zp06Xml.getToday());
                	mZpoEstadoInfante2 = new ZpoEstadoInfante(mZpoInfantData2.getRecordId(), '0', '0', '0', new Date(),
                			username,'0',idInstancia,instanceFilePath,Constants.STATUS_NOT_SUBMITTED,zp06Xml.getStart(),
                			zp06Xml.getEnd(),zp06Xml.getDeviceid(),zp06Xml.getSimserial(),zp06Xml.getPhonenumber(),zp06Xml.getToday());
                	mZpoEstadoInfante3 = new ZpoEstadoInfante(mZpoInfantData3.getRecordId(), '0', '0', '0', new Date(),
                			username,'0',idInstancia,instanceFilePath,Constants.STATUS_NOT_SUBMITTED,zp06Xml.getStart(),
                			zp06Xml.getEnd(),zp06Xml.getDeviceid(),zp06Xml.getSimserial(),zp06Xml.getPhonenumber(),zp06Xml.getToday());
                }
            }

            new SaveDataTask().execute(accion);

        } catch (Exception e) {
            // Presenta el error al parsear el xml
            Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
            finish();
        }
    }

    private ZpoInfantData completarDatosInfante(int numInfante) {
        String madreId = mDelivery.getRecordId();
        String bebeId = null;
        String fetalOutcome = null;
        String causeDeath = null;
        String sexBaby = null;
        if (numInfante == 1) {
        	bebeId = madreId.substring(0, madreId.length() - 1) + "1";
        	fetalOutcome = mDelivery.getDeliFetalOutcome1();
        	causeDeath = mDelivery.getDeliCauseDeath1();
        	sexBaby = mDelivery.getDeliSexBaby1();
        }
        else if (numInfante == 2) {
        	bebeId = madreId.substring(0, madreId.length() - 1) + "2";
        	fetalOutcome = mDelivery.getDeliFetalOutcome2();
        	causeDeath = mDelivery.getDeliCauseDeath2();
        	sexBaby = mDelivery.getDeliSexBaby2();
        }
        else if (numInfante == 3) {
        	bebeId = madreId.substring(0, madreId.length() - 1) + "3";
        	fetalOutcome = mDelivery.getDeliFetalOutcome3();
        	causeDeath = mDelivery.getDeliCauseDeath3();
        	sexBaby = mDelivery.getDeliSexBaby3();
        }
        ZpoInfantData data = new ZpoInfantData();
        data.setRecordId(bebeId);
        data.setPregnantId(madreId);
        data.setInfantBirthDate(mDelivery.getDeliDeliveryDate());
        data.setInfantMode(mDelivery.getDeliMode());
        data.setInfantDeliveryWho(mDelivery.getDeliDeliveryWho());
        data.setInfantDeliveryOccur(mDelivery.getDeliDeliveryOccur());
        data.setInfantHospitalId(mDelivery.getDeliHospitalId());
        data.setInfantClinicId(mDelivery.getDeliClinicId());
        data.setInfantDeliveryOther(mDelivery.getDeliDeliveryOther());
        data.setInfantNumBirth(mDelivery.getDeliNumBirth());
        data.setInfantFetalOutcome(fetalOutcome);
        data.setInfantCauseDeath(causeDeath);
        data.setInfantSexBaby(sexBaby);
        data.setEstado(Constants.STATUS_NOT_SUBMITTED);
        data.setInfantConsentInfant(mDelivery.getDeliConsentInfant());
        data.setInfantReasonNoconsent(mDelivery.getDeliReasonNoconsent());
        data.setInfantNoconsentOther(mDelivery.getDeliNoconsentOther());
        data.setStart(mDelivery.getStart());
        data.setEnd(mDelivery.getEnd());
        data.setDeviceid(mDelivery.getDeviceid());
        data.setSimserial(mDelivery.getSimserial());
        data.setPhonenumber(mDelivery.getPhonenumber());
        data.setToday(mDelivery.getToday());
        data.setRecordDate(new Date());
        data.setRecordUser(username);
        return data;
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
            try {
            	accionaRealizar = values[0];
    			try {
    				zipA.open();
    				if (accionaRealizar == ADD_ZP06_ODK){
    					zipA.crearZpo05Delivery(mDelivery);
                        if (mZpoInfantData1!=null)
                            zipA.crearZpoInfantData(mZpoInfantData1);
                        if (mZpoInfantData2!=null)
                            zipA.crearZpoInfantData(mZpoInfantData2);
                        if (mZpoInfantData3!=null)
                            zipA.crearZpoInfantData(mZpoInfantData3);
                        if (mZpoEstadoInfante1!=null)
                            zipA.crearZpoEstadoInfante(mZpoEstadoInfante1);
                        if (mZpoEstadoInfante2!=null)
                        	zipA.crearZpoEstadoInfante(mZpoEstadoInfante2);
                        if (mZpoEstadoInfante3!=null)
                        	zipA.crearZpoEstadoInfante(mZpoEstadoInfante3);
    				}
    				else{
    					zipA.editarZpo05Delivery(mDelivery);
                        if (mZpoInfantData1!=null){
                            if (zipA.getZpoInfantData(MainDBConstants.recordId + "='" + mZpoInfantData1.getRecordId() + "'",null)!=null)
                                zipA.editarZpoInfantData(mZpoInfantData1);
                            else zipA.crearZpoInfantData(mZpoInfantData1);
                        }
                        if (mZpoInfantData2!=null){
                            if (zipA.getZpoInfantData(MainDBConstants.recordId + "='" + mZpoInfantData2.getRecordId() + "'",null)!=null)
                                zipA.editarZpoInfantData(mZpoInfantData2);
                            else zipA.crearZpoInfantData(mZpoInfantData2);
                        }
                        if (mZpoInfantData3!=null){
                            if (zipA.getZpoInfantData(MainDBConstants.recordId + "='" + mZpoInfantData3.getRecordId() + "'",null)!=null)
                                zipA.editarZpoInfantData(mZpoInfantData3);
                            else zipA.crearZpoInfantData(mZpoInfantData3);
                        }
    				}
    				zipA.close();
    			} catch (Exception e) {
    				Log.e(TAG, e.getLocalizedMessage(), e);
    				return "error";
    			}
    			return "exito";
            } catch (Exception e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return "error";
            }
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
