package ni.org.ics.zpo.appmovil.activities.paginas.eventosmadre;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import ni.org.ics.zpo.appmovil.AbstractAsyncActivity;
import ni.org.ics.zpo.appmovil.MainActivity;
import ni.org.ics.zpo.appmovil.MyZpoApplication;
import ni.org.ics.zpo.appmovil.R;
import ni.org.ics.zpo.appmovil.activities.nuevos.*;
import ni.org.ics.zpo.appmovil.adapters.eventosmadre.IngresoAdapter;
import ni.org.ics.zpo.appmovil.database.ZpoAdapter;
import ni.org.ics.zpo.domain.*;
import ni.org.ics.zpo.appmovil.utils.Constants;
import ni.org.ics.zpo.appmovil.utils.MainDBConstants;

import java.text.SimpleDateFormat;

public class IngresoActivity extends AbstractAsyncActivity {
	private ZpoAdapter zpoA;
	private static Zpo00Screening zp00 = new Zpo00Screening();
	private static ZpoEstadoEmbarazada zpEstado = new ZpoEstadoEmbarazada();
	private static Zpo01StudyEntrySectionAtoB zp01a = null;
	private static Zpo01StudyEntrySectionC zp01c = null;
	private static Zpo01StudyEntrySectionDtoF zp01f = null;
	private static Zpo02BiospecimenCollection zp02 = null;
	private static Zpo04ExtendedSectionAtoD zp04a = null;
	private static Zpo04ExtendedSectionE zp04e = null;
    private static Zpo04ExtendedSectionF zp04f = null;
    private static Zpo05Delivery zp05 = null;

	private SimpleDateFormat mDateFormat = new SimpleDateFormat("MMM dd, yyyy");
	private static String evento;
	private GridView gridView;
	private TextView textView;
	private AlertDialog alertDialog;
	private static final int EXIT = 1;
	private boolean mExitShowing;
	private boolean pendiente = false;
	private static final String EXIT_SHOWING = "exitshowing";
	String[] menu_maternal_info;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_maternal);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			ActionBar actionBar = getActionBar();
			actionBar.setDisplayHomeAsUpEnabled(true);
		}
		if (savedInstanceState != null) {
			if (savedInstanceState.containsKey(EXIT_SHOWING)) {
				mExitShowing = savedInstanceState.getBoolean(EXIT_SHOWING, false);
			}
		}
		String mPass = ((MyZpoApplication) this.getApplication()).getPassApp();
		zpoA = new ZpoAdapter(this.getApplicationContext(),mPass,false,false);
		/*Aca se recupera evento, tamizaje y estado*/
		evento = getIntent().getStringExtra(Constants.EVENT);
		zp00 = (Zpo00Screening) getIntent().getExtras().getSerializable(Constants.OBJECTO_ZP00);
		zpEstado = (ZpoEstadoEmbarazada) getIntent().getExtras().getSerializable(Constants.OBJECTO_ZPEST);
		//Aca se recupera los datos de los formularios para ver si estan realizados o no...
		new FetchDataIngresoTask().execute(evento);
		textView = (TextView) findViewById(R.id.label);
		textView.setText(getString(R.string.forms)+"\n"+
				getString(R.string.mat_id)+": "+zp00.getRecordId()+"\n"+
						getString(R.string.mat_fec)+": "+ mDateFormat.format(zp00.getScrVisitDate()));
		menu_maternal_info = getResources().getStringArray(R.array.menu_maternal_ingreso);
		gridView = (GridView) findViewById(R.id.gridView1);
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Bundle arguments = new Bundle();
				Intent i;
				arguments.putString(Constants.EVENT, Constants.ENTRY);
                arguments.putString(Constants.RECORDID, zp00.getRecordId());
				switch(position){ 
                    case 0: // DEMOGRAFIA
                        i = new Intent(getApplicationContext(),
                                NewZpo01StudyEntrySectionAtoBActivity.class);
                        //Se pone el evento y el objeto en caso de que no sea nulo...
                        if (zp01a!=null) arguments.putSerializable(Constants.OBJECTO_ZP01A, zp01a);
                        i.putExtras(arguments);
                        startActivity(i);
                        break;
                    case 1: // ESTADO SALUD
                        i = new Intent(getApplicationContext(),
                                NewZpo01StudyEntrySectionCActivity.class);
                        if (zp01c !=null) arguments.putSerializable(Constants.OBJECTO_ZP01E , zp01c);
                        i.putExtras(arguments);
                        startActivity(i);
                        break;
                    case 2: // HISTORIA EMBARAZO
                        i = new Intent(getApplicationContext(),
                                NewZpo01StudyEntrySectionDtoFActivity.class);
                        if (zp01f!=null) arguments.putSerializable(Constants.OBJECTO_ZP01F , zp01f);
                        i.putExtras(arguments);
                        startActivity(i);
                        break;
                    case 3: //MUESTRAS
                        i = new Intent(getApplicationContext(),
                                NewZpo02BiospecimenCollectionActivity.class);
                        if (zp02!=null) arguments.putSerializable(Constants.OBJECTO_ZP02 , zp02);
                        i.putExtras(arguments);
                        startActivity(i);
                        break;
                    case 4: //PROFESION
                        i = new Intent(getApplicationContext(),
                                NewZpo04ExtendedSectionAtoDActivity.class);
                        if (zp04a!=null) arguments.putSerializable(Constants.OBJECTO_ZP04A , zp04a);
                        i.putExtras(arguments);
                        startActivity(i);
                        break;
                    case 5: //EXPOSICION
                        i = new Intent(getApplicationContext(),
                                NewZpo04ExtendedSectionEActivity.class);
                        if (zp04e!=null) arguments.putSerializable(Constants.OBJECTO_ZP04E , zp04e);
                        i.putExtras(arguments);
                        startActivity(i);
                        break;
                    case 6: //PESTICIDAS
                        i = new Intent(getApplicationContext(),
                                NewZpo04ExtendedSectionFActivity.class);
                        if (zp04f!=null) arguments.putSerializable(Constants.OBJECTO_ZP04F , zp04f);
                        i.putExtras(arguments);
                        startActivity(i);
                        break;
                    case 7: //PARTO
                        i = new Intent(getApplicationContext(),
                                NewZpo05DeliveryActivity.class);
                        if (zp05 !=null) arguments.putSerializable(Constants.OBJECTO_ZP06 , zp05);
                        i.putExtras(arguments);
                        startActivity(i);
                        break;
                    default:
                        break;
				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.general, menu);
		return true;
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putBoolean(EXIT_SHOWING, mExitShowing);
	}

	@Override
	protected void onResume() {
		//getParticipanteData();
		if (mExitShowing) {
			createDialog(EXIT);
		}
		new FetchDataIngresoTask().execute(evento);
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (alertDialog != null && alertDialog.isShowing()) {
			alertDialog.dismiss();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent i;
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		case R.id.MENU_BACK:
			if (pendiente){
				createDialog(EXIT);
			}
			else{
				finish();
			}
			return true;
		case R.id.MENU_HOME:
			if (pendiente){
				createDialog(EXIT);
			}
			else{
				i = new Intent(getApplicationContext(),
						MainActivity.class);
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
				finish();
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onBackPressed (){
		if (pendiente){
			createDialog(EXIT);
		}
		else{
			finish();
		}
	}

	private void createDialog(int dialog) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		switch(dialog){
		case EXIT:
			builder.setTitle(this.getString(R.string.confirm));
			builder.setMessage(this.getString(R.string.ok));
			builder.setPositiveButton(this.getString(R.string.yes), new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					// Finish app
					dialog.dismiss();
					
					mExitShowing=false;
				}

			});
			builder.setNegativeButton(this.getString(R.string.no), new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// Do nothing
					dialog.dismiss();
					mExitShowing=false;
				}
			});
			mExitShowing=true;
			break;
		default:
			break;
		}
		alertDialog = builder.create();
		alertDialog.show();
	}
	
	// ***************************************
		// Private classes
		// ***************************************
		private class FetchDataIngresoTask extends AsyncTask<String, Void, String> {
			private String eventoaFiltrar = null;
			private String filtro = null;
			@Override
			protected void onPreExecute() {
				// before the request begins, show a progress indicator
				showLoadingProgressDialog();
			}

			@Override
			protected String doInBackground(String... values) {
				eventoaFiltrar = values[0];
				try {
					zpoA.open();
					filtro = MainDBConstants.recordId + "='" + zp00.getRecordId() + "'";
					zp01a = zpoA.getZpo01StudyEntrySectionAtoB(filtro, MainDBConstants.recordId);
					zp01c = zpoA.getZpo01StudyEntrySectionC(filtro, null);
					zp01f = zpoA.getZpo01StudyEntrySectionDtoF(filtro, null);
					filtro = MainDBConstants.recordId + "='" + zp00.getRecordId() + "' and " + MainDBConstants.eventName + "='" + eventoaFiltrar +"'";
					zp02 = zpoA.getZpo02BiospecimenCollection(filtro, MainDBConstants.recordId);
					zp04a = zpoA.getZpo04ExtendedSectionAtoD(filtro, null);
					zp04e = zpoA.getZpo04ExtendedSectionE(filtro, null);
					zp04f = zpoA.getZpo04ExtendedSectionF(filtro, null);
                    zp05 = zpoA.getZpo05Delivery(filtro, null);
					if (zp01a!=null && zp01c !=null && zp01f!=null && zp02!=null &&
							zp04a!=null && zp04e!=null && zp04f!=null){
						zpEstado.setIngreso('1');
						zpoA.editarZpoEstadoMadre(zpEstado);
					}
					zpoA.close();
				} catch (Exception e) {
					Log.e(TAG, e.getLocalizedMessage(), e);
					return "Error";
				}
				return "Exito";
			}

			protected void onPostExecute(String resultado) {
				// after the network request completes, hide the progress indicator
				gridView.setAdapter(new IngresoAdapter(getApplicationContext(), R.layout.menu_item_2, menu_maternal_info, zp01a, zp01c, zp01f,
						zp02, zp04a, zp04e ,zp04f, zp05
						));
				dismissProgressDialog();
			}

		}

}
	
