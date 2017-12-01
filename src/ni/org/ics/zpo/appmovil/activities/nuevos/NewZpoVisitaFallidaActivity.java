package ni.org.ics.zpo.appmovil.activities.nuevos;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.util.Log;
import android.view.*;
import android.widget.*;
import ni.org.ics.zpo.appmovil.AbstractAsyncActivity;
import ni.org.ics.zpo.appmovil.MainActivity;
import ni.org.ics.zpo.appmovil.MyZpoApplication;
import ni.org.ics.zpo.appmovil.R;
import ni.org.ics.zpo.appmovil.database.ZpoAdapter;
import ni.org.ics.zpo.appmovil.preferences.PreferencesActivity;
import ni.org.ics.zpo.appmovil.utils.Constants;
import ni.org.ics.zpo.appmovil.utils.DeviceInfo;
import ni.org.ics.zpo.appmovil.utils.MainDBConstants;
import ni.org.ics.zpo.domain.ZpoVisitaFallida;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

public class NewZpoVisitaFallidaActivity extends AbstractAsyncActivity {

	private TextView mHeader;
    private TextView mLabelOtra;
	private Spinner mRazonView;
	private EditText mPersonaView;
	private EditText mOtraRazonView;
	private Button mSaveButton;
	private Button mFinishButton;
	//private String mCodigo;
	private Date todayWithZeroTime = null;
	private String mCodigosDeHoy;
    private DeviceInfo infoMovil;
	
	private static final int EXIT = 1;
	private static final int HOME = 0;
	private static final int REVIEW = 3;
	private static final int BARCODE_CAPTURE = 2;
	
	private AlertDialog alertDialog;

	private ZpoAdapter zipA;
	private ZpoVisitaFallida mVisitaFallida = null;
	private String username;
	private SharedPreferences settings;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.visitas_fallidas);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			ActionBar actionBar = getActionBar();
			actionBar.setDisplayHomeAsUpEnabled(true);
		}
		
		settings =
				PreferenceManager.getDefaultSharedPreferences(this);
		username =
				settings.getString(PreferencesActivity.KEY_USERNAME,
						null);
        infoMovil = new DeviceInfo(NewZpoVisitaFallidaActivity.this);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date today = new Date();
		try {
			todayWithZeroTime =formatter.parse(formatter.format(today));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String mPass = ((MyZpoApplication) this.getApplication()).getPassApp();
		zipA = new ZpoAdapter(this.getApplicationContext(),mPass,false,false);
		zipA.open();
		mHeader = (TextView) findViewById(R.id.label_header);
		mRazonView = (Spinner) findViewById(R.id.razon);
		mPersonaView = (EditText) findViewById(R.id.persona);
        mLabelOtra = (TextView) findViewById(R.id.label_otrarazon);
		mOtraRazonView = (EditText) findViewById(R.id.otrarazon);
		mOtraRazonView.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
        mOtraRazonView.setVisibility(View.GONE);

		mHeader.setText(R.string.visitaf_2);

        String[] list_razones = getResources().getStringArray(R.array.list_razon_visita_fall);
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list_razones);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		mRazonView.setAdapter(dataAdapter);

        mRazonView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position!=6){
                    mOtraRazonView.setVisibility(View.GONE);
                    mLabelOtra.setVisibility(View.GONE);
                }
                else{
                    mOtraRazonView.setVisibility(View.VISIBLE);
                    mLabelOtra.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });

		mSaveButton = (Button) findViewById(R.id.saveButton);
		mSaveButton.setText(R.string.save);

		mSaveButton.setOnClickListener(new View.OnClickListener()  {
			@Override
			public void onClick(View v) {
				if(validarEntrada()){
					new SaveDataTask().execute(mPersonaView.getText().toString(),
                            mRazonView.getSelectedItem().toString(),
                            mOtraRazonView.getText().toString());
				}
				
			}
		});
		
		mFinishButton = (Button) findViewById(R.id.cancelButton);
		mFinishButton.setText(R.string.finish);
		mFinishButton.setOnClickListener(new View.OnClickListener()  {
			@Override
			public void onClick(View v) {
				createDialog(EXIT);
			}
		});

	}
	
	private boolean validarEntrada() {
		//Valida la entrada 
		if (mRazonView.getSelectedItemPosition()==0){
			showToast(this.getString( R.string.error_razon));
			return false;
		}
        if (mRazonView.getSelectedItemPosition()==6 && mOtraRazonView.getText().toString().isEmpty()){
            showToast(this.getString( R.string.error_otra_razon));
            return false;
        }
		if (mPersonaView.getText().toString().isEmpty()){
			showToast(this.getString( R.string.error_persona_vf));
			return false;
		}
		else{
			return true;
		}
	}
	
	private void showToast(String mensaje){
		LayoutInflater inflater = getLayoutInflater();

		View layout = inflater.inflate(R.layout.toast,
				(ViewGroup) findViewById(R.id.toast_layout_root));

		TextView text = (TextView) layout.findViewById(R.id.text);
		text.setText(mensaje);

		Toast toast = new Toast(getApplicationContext());
		toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setView(layout);
		toast.show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.control, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId()==R.id.MENU_BACK){
			createDialog(EXIT);
			return true;
		}
		else if(item.getItemId()==R.id.MENU_HOME){
			createDialog(HOME);
			return true;
		}
		else if(item.getItemId()==R.id.MENU_VIEW){
			new GetDataTask().execute();
			return true;
		}
		else{
			return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	public void onBackPressed (){
		createDialog(EXIT);
	}
	
	private void createDialog(int dialog) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		switch(dialog){
		case EXIT:
			builder.setTitle(this.getString(R.string.confirm));
			builder.setMessage(this.getString(R.string.exiting));
			builder.setPositiveButton(this.getString(R.string.yes), new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					// Finish app
					dialog.dismiss();
					zipA.close();
					finish();
				}
			});
			builder.setNegativeButton(this.getString(R.string.no), new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// Do nothing
					dialog.dismiss();
				}
			});
			break;
		case HOME:
			builder.setTitle(this.getString(R.string.confirm));
			builder.setMessage(this.getString(R.string.exiting));
			builder.setPositiveButton(this.getString(R.string.yes), new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					// Finish app
					dialog.dismiss();
					zipA.close();
					Intent i = new Intent(getApplicationContext(),
							MainActivity.class);
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
					finish();
				}
			});
			builder.setNegativeButton(this.getString(R.string.no), new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// Do nothing
					dialog.dismiss();
				}
			});
			break;
		case REVIEW:
			builder.setTitle(this.getString(R.string.review));
			builder.setMessage(mCodigosDeHoy);
			builder.setPositiveButton(this.getString(R.string.ok), new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			break;				
		default:
			break;
		}
		alertDialog = builder.create();
		alertDialog.show();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);

	}
	
	// ***************************************
	// Private classes
	// ***************************************
	private class SaveDataTask extends AsyncTask<String, Void, String> {
		private String id = null;
		private String persona = null;
		private String razon = null;
        private String otraRazon = null;
		@Override
		protected void onPreExecute() {
			// before the request begins, show a progress indicator
			showLoadingProgressDialog();
		}

		@Override
		protected String doInBackground(String... values) {
			persona = values[0];
			razon = values[1];
            otraRazon = values[2];
			try {
				Date fecha = new Date();
				mVisitaFallida = new ZpoVisitaFallida();
				mVisitaFallida.setId(infoMovil.getId());
				mVisitaFallida.setPersona(persona);
				mVisitaFallida.setRazon(razon);
                mVisitaFallida.setOtraRazon(otraRazon);
				mVisitaFallida.setFechaVisita(new Date());
				mVisitaFallida.setRecordDate(fecha);
				mVisitaFallida.setRecordUser(username);
				mVisitaFallida.setDeviceid(infoMovil.getDeviceId());
				mVisitaFallida.setEstado(Constants.STATUS_NOT_SUBMITTED);
				mVisitaFallida.setToday(fecha);
                zipA.crearZpoVisitaFallida(mVisitaFallida);
			} catch (Exception e) {
				Log.e(TAG, e.getLocalizedMessage(), e);
				return e.getLocalizedMessage();
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
		if(resultado.matches("exito")){
			Toast.makeText(getApplicationContext(),	R.string.success, Toast.LENGTH_LONG).show();
			mOtraRazonView.setText(null);
            mPersonaView.setText(null);
            zipA.close();
            finish();
		}
		else{
			showToast(resultado);
		}
	}	

	// ***************************************
	// Private classes
	// ***************************************
	private class GetDataTask extends AsyncTask<String, Void, List<ZpoVisitaFallida>> {
		private List<ZpoVisitaFallida> zpListaDeHoy = null;
		@Override
		protected void onPreExecute() {
			// before the request begins, show a progress indicator
			showLoadingProgressDialog();
		}

		@Override
		protected List<ZpoVisitaFallida> doInBackground(String... values) {
			try {
				zpListaDeHoy = zipA.getZpoVisitaFallidas(MainDBConstants.fechaVisita + ">=" + todayWithZeroTime.getTime(), MainDBConstants.id);
				return zpListaDeHoy;
			} catch (Exception e) {
				Log.e(TAG, e.getLocalizedMessage(), e);
				return null;
			}
		}

		protected void onPostExecute(List<ZpoVisitaFallida> resultado) {
			// after the network request completes, hide the progress indicator
			dismissProgressDialog();
			showResultados(resultado);
		}

	}
	
	// ***************************************
	// Private methods
	// ***************************************
	private void showResultados(List<ZpoVisitaFallida> resultado) {
		if(resultado!=null){
			mCodigosDeHoy = this.getString(R.string.today_items) + ": " + String.valueOf(resultado.size()) + "\n";
			ListIterator<ZpoVisitaFallida> iter = resultado.listIterator();
            while (iter.hasNext()){
            	mCodigosDeHoy = mCodigosDeHoy + iter.next().getPersona()+ "\n";
            }
		}
		else{
			mCodigosDeHoy = this.getString(R.string.no_items);
		}
		createDialog(REVIEW);
	}
	
}
