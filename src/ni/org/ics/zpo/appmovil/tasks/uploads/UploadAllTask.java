package ni.org.ics.zpo.appmovil.tasks.uploads;

import android.content.Context;
import android.util.Log;
import ni.org.ics.zpo.appmovil.database.ZpoAdapter;
import ni.org.ics.zpo.appmovil.listeners.UploadListener;
import ni.org.ics.zpo.appmovil.tasks.UploadTask;
import ni.org.ics.zpo.appmovil.utils.Constants;
import ni.org.ics.zpo.appmovil.utils.MainDBConstants;
import ni.org.ics.zpo.domain.*;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class UploadAllTask extends UploadTask {
	
	private final Context mContext;

	public UploadAllTask(Context context) {
		mContext = context;
	}

	protected static final String TAG = UploadAllTask.class.getSimpleName();
    private static final String TOTAL_TASK = "21";
	private ZpoAdapter zpoA = null;

    private List<Zpo00Screening> mTamizajes = new ArrayList<Zpo00Screening>();
    private List<Zpo01StudyEntrySectionAtoB> mIngresosAD = new ArrayList<Zpo01StudyEntrySectionAtoB>();
    private List<Zpo01StudyEntrySectionC> mIngresosE = new ArrayList<Zpo01StudyEntrySectionC>();
    private List<Zpo01StudyEntrySectionDtoF> mIngresosFK = new ArrayList<Zpo01StudyEntrySectionDtoF>();
    private List<Zpo02BiospecimenCollection> mCollections = new ArrayList<Zpo02BiospecimenCollection>();
    private List<Zpo04ExtendedSectionAtoD> mTrimesterVisitAD = new ArrayList<Zpo04ExtendedSectionAtoD>();
    private List<Zpo04ExtendedSectionE> mTrimesterVisitE = new ArrayList<Zpo04ExtendedSectionE>();
    private List<Zpo04ExtendedSectionF> mTrimesterVisitFH = new ArrayList<Zpo04ExtendedSectionF>();
    private List<Zpo05Delivery> mDeliverys = new ArrayList<Zpo05Delivery>();
    private List<Zpo08StudyExit> mExits = new ArrayList<Zpo08StudyExit>();
    private List<ZpoEstadoEmbarazada> mStatus = new ArrayList<ZpoEstadoEmbarazada>();
    private List<ZpoControlConsentimientosSalida> mSalidasCons = new ArrayList<ZpoControlConsentimientosSalida>();
    private List<ZpoControlConsentimientosRecepcion> mRecepcionesCons = new ArrayList<ZpoControlConsentimientosRecepcion>();
    private List<ZpoVisitaFallida> mVisitasFallidas = new ArrayList<ZpoVisitaFallida>();
    private List<ZpoInfantData> mInfantData = new ArrayList<ZpoInfantData>();
    private List<ZpoEstadoInfante> mEstadoInfante = new ArrayList<ZpoEstadoInfante>();
    private List<Zpo07InfantAssessmentVisit> mInfantAssessment = new ArrayList<Zpo07InfantAssessmentVisit>();
    private List<Zpo07aInfantOphtResults> mAInfantOphtResults = new ArrayList<Zpo07aInfantOphtResults>();
    private List<Zpo07bInfantAudioResults> mbInfantAudioResults = new ArrayList<Zpo07bInfantAudioResults>();
    private List<Zpo07cInfantImageStudies> mcInfantImageStudies = new ArrayList<Zpo07cInfantImageStudies>();
    private List<Zpo07dInfantBayleyScales> mdInfantBayleyScales = new ArrayList<Zpo07dInfantBayleyScales>();

	private String url = null;
	private String username = null;
	private String password = null;
	private String error = null;
	protected UploadListener mStateListener;

    public static final int TAMIZAJE = 1;
    public static final int ESTADO = 2;
    public static final int DAT_INFANTE = 3;
    public static final int ESTADO_INFANTE = 4;
    public static final int INGRESO1 = 5;
    public static final int INGRESO2 = 6;
    public static final int INGRESO3 = 7;
    public static final int EXTENDED1 = 8;
    public static final int EXTENDED2 = 9;
    public static final int EXTENDED3 = 10;
    public static final int PARTO = 11;
    public static final int EVAL_INFANTE = 12;
    public static final int OPHTH_RESULTS = 13;
    public static final int AUDIO_RESULTS = 14;
    public static final int IMAGE_STUDIES = 15;
    public static final int BAYLEY_SCALES = 16;
    public static final int MUESTRAS = 17;
    public static final int CONSSAL = 18;
    public static final int CONSREC = 19;
    public static final int SALIDA = 20;
    public static final int VISITA_FALL = 21;

    @Override
	protected String doInBackground(String... values) {
		url = values[0];
		username = values[1];
		password = values[2];

		try {
			publishProgress("Obteniendo registros de la base de datos", "1", "2");
			zpoA = new ZpoAdapter(mContext, password, false,false);
			zpoA.open();

            String filtro = MainDBConstants.STATUS + "='" + Constants.STATUS_NOT_SUBMITTED + "'";            
            mTamizajes = zpoA.getZpo00Screenings(filtro, MainDBConstants.recordId);
            mIngresosAD = zpoA.getZpo01StudyEntrySectionAtoBs(filtro, MainDBConstants.recordId);
            mIngresosE = zpoA.getZpo01StudyEntrySectionCs(filtro, MainDBConstants.recordId);
            mIngresosFK = zpoA.getZpo01StudyEntrySectionDtoFs(filtro, MainDBConstants.recordId);
            mCollections = zpoA.getZpo02BiospecimenCollections(filtro, MainDBConstants.recordId);
            mTrimesterVisitAD = zpoA.getZpo04ExtendedSectionAtoDs(filtro, MainDBConstants.recordId);
            mTrimesterVisitE = zpoA.getZpo04ExtendedSectionEs(filtro, MainDBConstants.recordId);
            mTrimesterVisitFH = zpoA.getZpo04ExtendedSectionFs(filtro, MainDBConstants.recordId);
            mDeliverys = zpoA.getZpo05Deliverys(filtro, MainDBConstants.recordId);
            mExits = zpoA.getZpo08StudyExits(filtro, MainDBConstants.recordId);
            mStatus = zpoA.getZpoEstadoMadres(filtro, MainDBConstants.recordId);
            mSalidasCons = zpoA.getZpoControlConsentimientosSalidas(filtro, null);
            mRecepcionesCons = zpoA.getZpoControlConsentimientosRecepciones(filtro, null);
            mInfantData = zpoA.getZpoInfantDatas(filtro,null);            
            mInfantAssessment = zpoA.getZpo07InfantAssessmentVisits(filtro, MainDBConstants.recordId);
            mAInfantOphtResults = zpoA.getZpo07aInfantOphtResults(filtro, MainDBConstants.recordId);
            mbInfantAudioResults = zpoA.getZpo07bInfantAudioResults(filtro, MainDBConstants.recordId);
            mcInfantImageStudies = zpoA.getZpo07cInfantImageStudies(filtro, MainDBConstants.recordId);
            mdInfantBayleyScales = zpoA.getZpo07dInfantBayleyScales(filtro, MainDBConstants.recordId);
            mEstadoInfante = zpoA.getZpoEstadoInfantes(filtro, MainDBConstants.recordId);
            mVisitasFallidas = zpoA.getZpoVisitaFallidas(filtro, null);
            
			publishProgress("Datos completos!", "2", "2");
            actualizarBaseDatos(Constants.STATUS_SUBMITTED, TAMIZAJE);
            error = cargarTamizajes(url, username, password);
            if (!error.matches("Datos recibidos!")){
                actualizarBaseDatos(Constants.STATUS_NOT_SUBMITTED, TAMIZAJE);
                return error;
            }
            actualizarBaseDatos(Constants.STATUS_SUBMITTED, ESTADO);
            error = uploadStatusPreg(url, username, password);
            if (!error.matches("Datos recibidos!")){
                actualizarBaseDatos(Constants.STATUS_NOT_SUBMITTED, ESTADO);
                return error;
            }
            actualizarBaseDatos(Constants.STATUS_SUBMITTED, DAT_INFANTE);
            error = uploadInfantData(url, username, password);
            if (!error.matches("Datos recibidos!")){
                actualizarBaseDatos(Constants.STATUS_NOT_SUBMITTED, DAT_INFANTE);
                return error;
            }
            actualizarBaseDatos(Constants.STATUS_SUBMITTED, ESTADO_INFANTE);
            error = uploadInfantStatus(url, username, password);
            if (!error.matches("Datos recibidos!")){
                actualizarBaseDatos(Constants.STATUS_NOT_SUBMITTED, ESTADO_INFANTE);
                return error;
            }
            actualizarBaseDatos(Constants.STATUS_SUBMITTED, INGRESO1);
            error = uploadEntrysAB(url, username, password);
            if (!error.matches("Datos recibidos!")){
                actualizarBaseDatos(Constants.STATUS_NOT_SUBMITTED, INGRESO1);
                return error;
            }
            actualizarBaseDatos(Constants.STATUS_SUBMITTED, INGRESO2);
            error = uploadEntrysZp01C(url, username, password);
            if (!error.matches("Datos recibidos!")){
                actualizarBaseDatos(Constants.STATUS_NOT_SUBMITTED, INGRESO2);
                return error;
            }
            actualizarBaseDatos(Constants.STATUS_SUBMITTED, INGRESO3);
            error = uploadEntrysF(url, username, password);
            if (!error.matches("Datos recibidos!")){
                actualizarBaseDatos(Constants.STATUS_NOT_SUBMITTED, INGRESO3);
                return error;
            }
            actualizarBaseDatos(Constants.STATUS_SUBMITTED, EXTENDED1);
            error = uploadExtendedAD(url, username, password);
            if (!error.matches("Datos recibidos!")){
                actualizarBaseDatos(Constants.STATUS_NOT_SUBMITTED, EXTENDED1);
                return error;
            }
            actualizarBaseDatos(Constants.STATUS_SUBMITTED, EXTENDED2);
            error = uploadExtendedE(url, username, password);
            if (!error.matches("Datos recibidos!")){
                actualizarBaseDatos(Constants.STATUS_NOT_SUBMITTED, EXTENDED2);
                return error;
            }
            actualizarBaseDatos(Constants.STATUS_SUBMITTED, EXTENDED3);
            error = uploadExtendedFH(url, username, password);
            if (!error.matches("Datos recibidos!")){
                actualizarBaseDatos(Constants.STATUS_NOT_SUBMITTED, EXTENDED3);
                return error;
            }
            actualizarBaseDatos(Constants.STATUS_SUBMITTED, PARTO);
            error = uploadDeliverys(url, username, password);
            if (!error.matches("Datos recibidos!")){
                actualizarBaseDatos(Constants.STATUS_NOT_SUBMITTED, PARTO);
                return error;
            }
            actualizarBaseDatos(Constants.STATUS_SUBMITTED, EVAL_INFANTE);
            error = uploadInfantAssessment(url, username, password);
            if (!error.matches("Datos recibidos!")){
                actualizarBaseDatos(Constants.STATUS_NOT_SUBMITTED, EVAL_INFANTE);
                return error;
            }
            actualizarBaseDatos(Constants.STATUS_SUBMITTED, OPHTH_RESULTS);
            error = uploadInfantOphtResults(url, username, password);
            if (!error.matches("Datos recibidos!")){
                actualizarBaseDatos(Constants.STATUS_NOT_SUBMITTED, OPHTH_RESULTS);
                return error;
            }

            actualizarBaseDatos(Constants.STATUS_SUBMITTED, AUDIO_RESULTS);
            error = uploadInfantAudioResults(url, username, password);
            if (!error.matches("Datos recibidos!")){
                actualizarBaseDatos(Constants.STATUS_NOT_SUBMITTED, AUDIO_RESULTS);
                return error;
            }

            actualizarBaseDatos(Constants.STATUS_SUBMITTED, IMAGE_STUDIES);
            error = uploadInfantImageStudies(url, username, password);
            if (!error.matches("Datos recibidos!")){
                actualizarBaseDatos(Constants.STATUS_NOT_SUBMITTED, IMAGE_STUDIES);
                return error;
            }

            actualizarBaseDatos(Constants.STATUS_SUBMITTED, BAYLEY_SCALES);
            error = uploadInfantBayleyScales(url, username, password);
            if (!error.matches("Datos recibidos!")){
                actualizarBaseDatos(Constants.STATUS_NOT_SUBMITTED, BAYLEY_SCALES);
                return error;
            }
            actualizarBaseDatos(Constants.STATUS_SUBMITTED, MUESTRAS);
            error = upLoadBioCollections(url, username, password);
            if (!error.matches("Datos recibidos!")){
                actualizarBaseDatos(Constants.STATUS_NOT_SUBMITTED, MUESTRAS);
                return error;
            }
            actualizarBaseDatos(Constants.STATUS_SUBMITTED, CONSSAL);
            error = uploadControlConsentimientosSalida(url, username, password);
            if (!error.matches("Datos recibidos!")){
                actualizarBaseDatos(Constants.STATUS_NOT_SUBMITTED, CONSSAL);
                return error;
            }
            actualizarBaseDatos(Constants.STATUS_SUBMITTED, CONSREC);
            error = uploadControlConsentimientosRecepcion(url, username, password);
            if (!error.matches("Datos recibidos!")){
                actualizarBaseDatos(Constants.STATUS_NOT_SUBMITTED, CONSREC);
                return error;
            }
            actualizarBaseDatos(Constants.STATUS_SUBMITTED, SALIDA);
            error = uploadExits(url, username, password);
            if (!error.matches("Datos recibidos!")){
                actualizarBaseDatos(Constants.STATUS_NOT_SUBMITTED, SALIDA);
                return error;
            }
            actualizarBaseDatos(Constants.STATUS_SUBMITTED, VISITA_FALL);
            error = uploadFailedVisits(url, username, password);
            if (!error.matches("Datos recibidos!")){
                actualizarBaseDatos(Constants.STATUS_NOT_SUBMITTED, VISITA_FALL);
                return error;
            }

            zpoA.close();
		} catch (Exception e1) {
			zpoA.close();
			e1.printStackTrace();
			return e1.getLocalizedMessage();
		}
		return error;
	}

    private void actualizarBaseDatos(String estado, int opcion) {
        int c;
       if(opcion==TAMIZAJE){
            c = mTamizajes.size();
            if(c>0){
                for (Zpo00Screening tamizaje : mTamizajes) {
                    tamizaje.setEstado(estado);
                    zpoA.editarZpo00Screening(tamizaje);
                    publishProgress("Actualizando tamizajes base de datos local", Integer.valueOf(mTamizajes.indexOf(tamizaje)).toString(), Integer
                            .valueOf(c).toString());
                }
            }
        }
        else if(opcion==INGRESO1){
            c = mIngresosAD.size();
            if(c>0){
                for (Zpo01StudyEntrySectionAtoB ingreso : mIngresosAD) {
                    ingreso.setEstado(estado);
                    zpoA.editarZpo01StudyEntrySectionAtoB(ingreso);
                    publishProgress("Actualizando datos de ingreso (A-D) base de datos local", Integer.valueOf(mIngresosAD.indexOf(ingreso)).toString(), Integer
                            .valueOf(c).toString());
                }
            }
        }
        else if(opcion==INGRESO2){
            c = mIngresosE.size();
            if(c>0){
                for (Zpo01StudyEntrySectionC ingreso : mIngresosE) {
                    ingreso.setEstado(estado);
                    zpoA.editarZpo01StudyEntrySectionC(ingreso);
                    publishProgress("Actualizando datos de ingreso (E) base de datos local", Integer.valueOf(mIngresosE.indexOf(ingreso)).toString(), Integer
                            .valueOf(c).toString());
                }
            }
        }
        else if(opcion==INGRESO3){
            c = mIngresosFK.size();
            if(c>0){
                for (Zpo01StudyEntrySectionDtoF ingreso : mIngresosFK) {
                    ingreso.setEstado(estado);
                    zpoA.editarZpo01StudyEntrySectionDtoF(ingreso);
                    publishProgress("Actualizando datos de ingreso (F-K) base de datos local", Integer.valueOf(mIngresosFK.indexOf(ingreso)).toString(), Integer
                            .valueOf(c).toString());
                }
            }
        }
        else if(opcion==MUESTRAS){
            c = mCollections.size();
            if(c>0){
                for (Zpo02BiospecimenCollection collection : mCollections) {
                    collection.setEstado(estado);
                    zpoA.editarZpo02BiospecimenCollection(collection);
                    publishProgress("Actualizando recoleccion de muestras base de datos local", Integer.valueOf(mCollections.indexOf(collection)).toString(), Integer
                            .valueOf(c).toString());
                }
            }
        }        
        else if(opcion== EXTENDED1){
            c = mTrimesterVisitAD.size();
            if(c>0){
                for (Zpo04ExtendedSectionAtoD extendedSectionAtoD : mTrimesterVisitAD) {
                    extendedSectionAtoD.setEstado(estado);
                    zpoA.editarZpo04ExtendedSectionAtoD(extendedSectionAtoD);
                    publishProgress("Actualizando formulario extendido (A-D) base de datos local", Integer.valueOf(mTrimesterVisitAD.indexOf(extendedSectionAtoD)).toString(), Integer
                            .valueOf(c).toString());
                }
            }
        }
        else if(opcion== EXTENDED2){
            c = mTrimesterVisitE.size();
            if(c>0){
                for (Zpo04ExtendedSectionE extendedSectionE : mTrimesterVisitE) {
                    extendedSectionE.setEstado(estado);
                    zpoA.editarZpo04ExtendedSectionE(extendedSectionE);
                    publishProgress("Actualizando formulario extendido (E) base de datos local", Integer.valueOf(mTrimesterVisitE.indexOf(extendedSectionE)).toString(), Integer
                            .valueOf(c).toString());
                }
            }
        }
        else if(opcion== EXTENDED3){
            c = mTrimesterVisitFH.size();
            if(c>0){
                for (Zpo04ExtendedSectionF extendedSectionF : mTrimesterVisitFH) {
                    extendedSectionF.setEstado(estado);
                    zpoA.editarZpo04ExtendedSectionF(extendedSectionF);
                    publishProgress("Actualizando formulario extendido (F) base de datos local", Integer.valueOf(mTrimesterVisitFH.indexOf(extendedSectionF)).toString(), Integer
                            .valueOf(c).toString());
                }
            }
        }        
        else if(opcion==PARTO){
            c = mDeliverys.size();
            if(c>0){
                for (Zpo05Delivery deliveryAnd6weekVisit : mDeliverys) {
                    deliveryAnd6weekVisit.setEstado(estado);
                    zpoA.editarZpo05Delivery(deliveryAnd6weekVisit);
                    publishProgress("Actualizando partos base de datos local", Integer.valueOf(mDeliverys.indexOf(deliveryAnd6weekVisit)).toString(), Integer
                            .valueOf(c).toString());
                }
            }
        }
        else if(opcion==SALIDA){
            c = mExits.size();
            if(c>0){
                for (Zpo08StudyExit studyExit : mExits) {
                    studyExit.setEstado(estado);
                    zpoA.editarZpo08StudyExit(studyExit);
                    publishProgress("Actualizando salidas del estudio base de datos local", Integer.valueOf(mExits.indexOf(studyExit)).toString(), Integer
                            .valueOf(c).toString());
                }
            }
        }
        else if(opcion==ESTADO){
            c = mStatus.size();
            if(c>0){
                for (ZpoEstadoEmbarazada estadoEmbarazada : mStatus) {
                    estadoEmbarazada.setEstado(estado);
                    zpoA.editarZpoEstadoMadre(estadoEmbarazada);
                    publishProgress("Actualizando estado de embarazadas base de datos local", Integer.valueOf(mStatus.indexOf(estadoEmbarazada)).toString(), Integer
                            .valueOf(c).toString());
                }
            }
        }
        else if(opcion==CONSSAL){
            c = mSalidasCons.size();
            if(c>0){
                for (ZpoControlConsentimientosSalida salidaCons : mSalidasCons) {
                    salidaCons.setEstado(estado);
                    zpoA.editarZpoControlConsentimientosSalida(salidaCons);
                    publishProgress("Actualizando salidas de consentimientos base de datos local", Integer.valueOf(mSalidasCons.indexOf(salidaCons)).toString(), Integer
                            .valueOf(c).toString());
                }
            }
        }
        else if(opcion==CONSREC){
            c = mRecepcionesCons.size();
            if(c>0){
                for (ZpoControlConsentimientosRecepcion recepcionCons : mRecepcionesCons) {
                    recepcionCons.setEstado(estado);
                    zpoA.editarZpoControlConsentimientosRecepcion(recepcionCons);
                    publishProgress("Actualizando recepciones de consentimientos base de datos local", Integer.valueOf(mRecepcionesCons.indexOf(recepcionCons)).toString(), Integer
                            .valueOf(c).toString());
                }
            }
        }
       else if(opcion==VISITA_FALL){
           c = mVisitasFallidas.size();
           if(c>0){
               for (ZpoVisitaFallida visitaFallida : mVisitasFallidas) {
                   visitaFallida.setEstado(estado);
                   zpoA.editarZpoVisitaFallida(visitaFallida);
                   publishProgress("Actualizando visitas fallidas base de datos local", Integer.valueOf(mVisitasFallidas.indexOf(visitaFallida)).toString(), Integer
                           .valueOf(c).toString());
               }
           }
       }
        /***************INFANTES***********/        
        else if(opcion==EVAL_INFANTE){
            c = mInfantAssessment.size();
            if(c>0){
                for (Zpo07InfantAssessmentVisit infantAssessmentVisit : mInfantAssessment) {
                    infantAssessmentVisit.setEstado(estado);
                    zpoA.editarZpo07InfantAssessmentVisit(infantAssessmentVisit);
                    publishProgress("Actualizando evaluaciones de infantes base de datos local", Integer.valueOf(mInfantAssessment.indexOf(infantAssessmentVisit)).toString(), Integer
                            .valueOf(c).toString());
                }
            }
        }
        else if(opcion==DAT_INFANTE){
            c = mInfantData.size();
            if(c>0){
                for (ZpoInfantData infantData : mInfantData) {
                    infantData.setEstado(estado);
                    zpoA.editarZpoInfantData(infantData);
                    publishProgress("Actualizando datos de infantes base de datos local", Integer.valueOf(mInfantData.indexOf(infantData)).toString(), Integer
                            .valueOf(c).toString());
                }
            }
        }
        else if(opcion==ESTADO_INFANTE){
            c = mEstadoInfante.size();
            if(c>0){
                for (ZpoEstadoInfante infantEstado : mEstadoInfante) {
                    infantEstado.setEstado(estado);
                    zpoA.editarZpoEstadoInfante(infantEstado);
                    publishProgress("Actualizando estado de infantes base de datos local", Integer.valueOf(mEstadoInfante.indexOf(infantEstado)).toString(), Integer
                            .valueOf(c).toString());
                }
            }
        }

        else if(opcion==OPHTH_RESULTS){
            c = mAInfantOphtResults.size();
            if(c>0){
                for (Zpo07aInfantOphtResults aInfantOphtResult : mAInfantOphtResults) {
                    aInfantOphtResult.setEstado(estado);
                    zpoA.editarZpo07aInfantOphtResults(aInfantOphtResult);
                    publishProgress("Actualizando resultados oftalmologicos de infantes de base de datos local", Integer.valueOf(mAInfantOphtResults.indexOf(aInfantOphtResult)).toString(), Integer
                            .valueOf(c).toString());
                }
            }
        }

        else if(opcion==AUDIO_RESULTS){
            c = mbInfantAudioResults.size();
            if(c>0){
                for (Zpo07bInfantAudioResults bInfantAudioResult : mbInfantAudioResults) {
                    bInfantAudioResult.setEstado(estado);
                    zpoA.editarZpo07bInfantAudioResults(bInfantAudioResult);
                    publishProgress("Actualizando resultados audiologicos de infantes de base de datos local", Integer.valueOf(mbInfantAudioResults.indexOf(bInfantAudioResult)).toString(), Integer
                            .valueOf(c).toString());
                }
            }
        }

        else if(opcion==IMAGE_STUDIES){
            c = mcInfantImageStudies.size();
            if(c>0){
                for (Zpo07cInfantImageStudies cInfantImageSt : mcInfantImageStudies) {
                    cInfantImageSt.setEstado(estado);
                    zpoA.editarZpo07cInfantImageStudies(cInfantImageSt);
                    publishProgress("Actualizando estudios de imagenes de infantes de base de datos local", Integer.valueOf(mcInfantImageStudies.indexOf(cInfantImageSt)).toString(), Integer
                            .valueOf(c).toString());
                }
            }
        }

        else if(opcion==BAYLEY_SCALES){
            c = mdInfantBayleyScales.size();
            if(c>0){
                for (Zpo07dInfantBayleyScales dInfantBayleySc : mdInfantBayleyScales) {
                    dInfantBayleySc.setEstado(estado);
                    zpoA.editarZpo07dInfantBayleyScales(dInfantBayleySc);
                    publishProgress("Actualizando escala de Bayley de base de datos local", Integer.valueOf(mdInfantBayleyScales.indexOf(dInfantBayleySc)).toString(), Integer
                            .valueOf(c).toString());
                }
            }
        }
    }


    /***************************************************/
    /********************* Zp00Tamizajes ************************/
    /***************************************************/
    // url, username, password
    protected String cargarTamizajes(String url, String username,
                                     String password) throws Exception {
        try {
            if(mTamizajes.size()>0){
                publishProgress("Enviando tamizajes!", String.valueOf(TAMIZAJE), TOTAL_TASK);
                // La URL de la solicitud POST
                final String urlRequest = url + "/movil/zpo00Screenings";
                Zpo00Screening[] envio = mTamizajes.toArray(new Zpo00Screening[mTamizajes.size()]);
                HttpHeaders requestHeaders = new HttpHeaders();
                HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<Zpo00Screening[]> requestEntity =
                        new HttpEntity<Zpo00Screening[]>(envio, requestHeaders);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
                // Hace la solicitud a la red, pone la vivienda y espera un mensaje de respuesta del servidor
                ResponseEntity<String> response = restTemplate.exchange(urlRequest, HttpMethod.POST, requestEntity,
                        String.class);
                return response.getBody();
            }
            else{
                return "Datos recibidos!";
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return e.getMessage();
        }
    }

    /***************************************************/
    /*************** Zp01 AtoB ************************/
    /***************************************************/
    // url, username, password
    protected String uploadEntrysAB(String url, String username,
                                    String password) throws Exception {
        try {
            if(mIngresosAD.size()>0){
                publishProgress("Enviando ingresos (1)!",String.valueOf(INGRESO1), TOTAL_TASK);
                // La URL de la solicitud POST
                final String urlRequest = url + "/movil/zpo01StudyEntrySectionAtoBs";
                Zpo01StudyEntrySectionAtoB[] envio = mIngresosAD.toArray(new Zpo01StudyEntrySectionAtoB[mIngresosAD.size()]);
                HttpHeaders requestHeaders = new HttpHeaders();
                HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<Zpo01StudyEntrySectionAtoB[]> requestEntity =
                        new HttpEntity<Zpo01StudyEntrySectionAtoB[]>(envio, requestHeaders);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
                // Hace la solicitud a la red, pone la vivienda y espera un mensaje de respuesta del servidor
                ResponseEntity<String> response = restTemplate.exchange(urlRequest, HttpMethod.POST, requestEntity,
                        String.class);
                return response.getBody();
            }
            else{
                return "Datos recibidos!";
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return e.getMessage();
        }
    }

    /***************************************************/
    /*************** Zp01 C ************************/
    /***************************************************/
    // url, username, password
    protected String uploadEntrysZp01C(String url, String username,
                                       String password) throws Exception {
        try {
            if(mIngresosE.size()>0){
                publishProgress("Enviando ingresos (2)!", String.valueOf(INGRESO2), TOTAL_TASK);
                // La URL de la solicitud POST
                final String urlRequest = url + "/movil/zpo01StudyEntrySectionCs";
                Zpo01StudyEntrySectionC[] envio = mIngresosE.toArray(new Zpo01StudyEntrySectionC[mIngresosE.size()]);
                HttpHeaders requestHeaders = new HttpHeaders();
                HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<Zpo01StudyEntrySectionC[]> requestEntity =
                        new HttpEntity<Zpo01StudyEntrySectionC[]>(envio, requestHeaders);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
                // Hace la solicitud a la red, pone la vivienda y espera un mensaje de respuesta del servidor
                ResponseEntity<String> response = restTemplate.exchange(urlRequest, HttpMethod.POST, requestEntity,
                        String.class);
                return response.getBody();
            }
            else{
                return "Datos recibidos!";
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return e.getMessage();
        }
    }

    /***************************************************/
    /*************** Zp01 F ************************/
    /***************************************************/
    // url, username, password
    protected String uploadEntrysF(String url, String username,
                                   String password) throws Exception {
        try {
            if(mIngresosFK.size()>0){
                publishProgress("Enviando ingresos (3)!", String.valueOf(INGRESO3), TOTAL_TASK);
                // La URL de la solicitud POST
                final String urlRequest = url + "/movil/zpo01StudyEntrySectionDtoFs";
                Zpo01StudyEntrySectionDtoF[] envio = mIngresosFK.toArray(new Zpo01StudyEntrySectionDtoF[mIngresosFK.size()]);
                HttpHeaders requestHeaders = new HttpHeaders();
                HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<Zpo01StudyEntrySectionDtoF[]> requestEntity =
                        new HttpEntity<Zpo01StudyEntrySectionDtoF[]>(envio, requestHeaders);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
                // Hace la solicitud a la red, pone la vivienda y espera un mensaje de respuesta del servidor
                ResponseEntity<String> response = restTemplate.exchange(urlRequest, HttpMethod.POST, requestEntity,
                        String.class);
                return response.getBody();
            }
            else{
                return "Datos recibidos!";
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return e.getMessage();
        }
    }

    /***************************************************/
    /********************* Zp02 ************************/
    /***************************************************/
    // url, username, password
    protected String upLoadBioCollections(String url, String username,
                                          String password) throws Exception {
        try {
            if(mCollections.size()>0){
                publishProgress("Enviando muestras!", String.valueOf(MUESTRAS), TOTAL_TASK);
                // La URL de la solicitud POST
                final String urlRequest = url + "/movil/zpo02BiospecimenCollections";
                Zpo02BiospecimenCollection[] envio = mCollections.toArray(new Zpo02BiospecimenCollection[mCollections.size()]);
                HttpHeaders requestHeaders = new HttpHeaders();
                HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<Zpo02BiospecimenCollection[]> requestEntity =
                        new HttpEntity<Zpo02BiospecimenCollection[]>(envio, requestHeaders);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
                // Hace la solicitud a la red, pone la vivienda y espera un mensaje de respuesta del servidor
                ResponseEntity<String> response = restTemplate.exchange(urlRequest, HttpMethod.POST, requestEntity,
                        String.class);
                return response.getBody();
            }
            else{
                return "Datos recibidos!";
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return e.getMessage();
        }
    }

    /***************************************************/
    /********************* Zp04 AtoD ************************/
    /***************************************************/
    // url, username, password
    protected String uploadExtendedAD(String url, String username,
                                      String password) throws Exception {
        try {
            if(mTrimesterVisitAD.size()>0){
                publishProgress("Enviando formulario extendido (1)!", String.valueOf(EXTENDED1), TOTAL_TASK);
                // La URL de la solicitud POST
                final String urlRequest = url + "/movil/zpo04ExtendedSectionAtoDs";
                Zpo04ExtendedSectionAtoD[] envio = mTrimesterVisitAD.toArray(new Zpo04ExtendedSectionAtoD[mTrimesterVisitAD.size()]);
                HttpHeaders requestHeaders = new HttpHeaders();
                HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<Zpo04ExtendedSectionAtoD[]> requestEntity =
                        new HttpEntity<Zpo04ExtendedSectionAtoD[]>(envio, requestHeaders);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
                // Hace la solicitud a la red, pone la vivienda y espera un mensaje de respuesta del servidor
                ResponseEntity<String> response = restTemplate.exchange(urlRequest, HttpMethod.POST, requestEntity,
                        String.class);
                return response.getBody();
            }
            else{
                return "Datos recibidos!";
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return e.getMessage();
        }
    }

    /***************************************************/
    /********************* Zp04 E ************************/
    /***************************************************/
    // url, username, password
    protected String uploadExtendedE(String url, String username,
                                     String password) throws Exception {
        try {
            if(mTrimesterVisitE.size()>0){
                publishProgress("Enviando formulario extendido (2)!", String.valueOf(EXTENDED2), TOTAL_TASK);
                // La URL de la solicitud POST
                final String urlRequest = url + "/movil/zpo04ExtendedSectionEs";
                Zpo04ExtendedSectionE[] envio = mTrimesterVisitE.toArray(new Zpo04ExtendedSectionE[mTrimesterVisitE.size()]);
                HttpHeaders requestHeaders = new HttpHeaders();
                HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<Zpo04ExtendedSectionE[]> requestEntity =
                        new HttpEntity<Zpo04ExtendedSectionE[]>(envio, requestHeaders);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
                // Hace la solicitud a la red, pone la vivienda y espera un mensaje de respuesta del servidor
                ResponseEntity<String> response = restTemplate.exchange(urlRequest, HttpMethod.POST, requestEntity,
                        String.class);
                return response.getBody();
            }
            else{
                return "Datos recibidos!";
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return e.getMessage();
        }
    }

    /***************************************************/
    /********************* Zp04 F ************************/
    /***************************************************/
    // url, username, password
    protected String uploadExtendedFH(String url, String username,
                                      String password) throws Exception {
        try {
            if(mTrimesterVisitFH.size()>0){
                publishProgress("Enviando formulario extendido (3)!", String.valueOf(EXTENDED3), TOTAL_TASK);
                // La URL de la solicitud POST
                final String urlRequest = url + "/movil/zpo04ExtendedSectionFs";
                Zpo04ExtendedSectionF[] envio = mTrimesterVisitFH.toArray(new Zpo04ExtendedSectionF[mTrimesterVisitFH.size()]);
                HttpHeaders requestHeaders = new HttpHeaders();
                HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<Zpo04ExtendedSectionF[]> requestEntity =
                        new HttpEntity<Zpo04ExtendedSectionF[]>(envio, requestHeaders);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
                // Hace la solicitud a la red, pone la vivienda y espera un mensaje de respuesta del servidor
                ResponseEntity<String> response = restTemplate.exchange(urlRequest, HttpMethod.POST, requestEntity,
                        String.class);
                return response.getBody();
            }
            else{
                return "Datos recibidos!";
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return e.getMessage();
        }
    }

    /***************************************************/
    /********************* Zp05 ************************/
    /***************************************************/
    // url, username, password
    protected String uploadDeliverys(String url, String username,
                                     String password) throws Exception {
        try {
            if(mDeliverys.size()>0){
                publishProgress("Enviando formulario de partos!", String.valueOf(PARTO), TOTAL_TASK);
                // La URL de la solicitud POST
                final String urlRequest = url + "/movil/zpo05Deliverys";
                Zpo05Delivery[] envio = mDeliverys.toArray(new Zpo05Delivery[mDeliverys.size()]);
                HttpHeaders requestHeaders = new HttpHeaders();
                HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<Zpo05Delivery[]> requestEntity =
                        new HttpEntity<Zpo05Delivery[]>(envio, requestHeaders);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
                // Hace la solicitud a la red, pone la vivienda y espera un mensaje de respuesta del servidor
                ResponseEntity<String> response = restTemplate.exchange(urlRequest, HttpMethod.POST, requestEntity,
                        String.class);
                return response.getBody();
            }
            else{
                return "Datos recibidos!";
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return e.getMessage();
        }
    }

    /***************************************************/
    /********************* Zp08 ************************/
    /***************************************************/
    // url, username, password
    protected String uploadExits(String url, String username,
                                 String password) throws Exception {
        try {
            if(mExits.size()>0){
                publishProgress("Enviando salidas!", String.valueOf(SALIDA), TOTAL_TASK);
                // La URL de la solicitud POST
                final String urlRequest = url + "/movil/zpo08StudyExits";
                Zpo08StudyExit[] envio = mExits.toArray(new Zpo08StudyExit[mExits.size()]);
                HttpHeaders requestHeaders = new HttpHeaders();
                HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<Zpo08StudyExit[]> requestEntity =
                        new HttpEntity<Zpo08StudyExit[]>(envio, requestHeaders);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
                // Hace la solicitud a la red, pone la vivienda y espera un mensaje de respuesta del servidor
                ResponseEntity<String> response = restTemplate.exchange(urlRequest, HttpMethod.POST, requestEntity,
                        String.class);
                return response.getBody();
            }
            else{
                return "Datos recibidos!";
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return e.getMessage();
        }
    }

    /***************************************************/
    /********************* ZpEstadosEmbarazadas ************************/
    /***************************************************/
    // url, username, password
    protected String uploadStatusPreg(String url, String username,
                                      String password) throws Exception {
        try {
            if(mStatus.size()>0){
                publishProgress("Enviando estados madres!", String.valueOf(ESTADO), TOTAL_TASK);
                // La URL de la solicitud POST
                final String urlRequest = url + "/movil/zpoEstadoEmb";
                ZpoEstadoEmbarazada[] envio = mStatus.toArray(new ZpoEstadoEmbarazada[mStatus.size()]);
                HttpHeaders requestHeaders = new HttpHeaders();
                HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<ZpoEstadoEmbarazada[]> requestEntity =
                        new HttpEntity<ZpoEstadoEmbarazada[]>(envio, requestHeaders);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
                // Hace la solicitud a la red, pone la vivienda y espera un mensaje de respuesta del servidor
                ResponseEntity<String> response = restTemplate.exchange(urlRequest, HttpMethod.POST, requestEntity,
                        String.class);
                return response.getBody();
            }
            else{
                return "Datos recibidos!";
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return e.getMessage();
        }
    }


    /***************************************************/
    /********************* ZpoControlConsentimientosSalida******/
    /***************************************************/
    // url, username, password
    protected String uploadControlConsentimientosSalida(String url, String username,
                                                        String password) throws Exception {
        try {
            if(mSalidasCons.size()>0){
                publishProgress("Enviando salidas de consentimientos!", String.valueOf(CONSSAL), TOTAL_TASK);
                // La URL de la solicitud POST
                final String urlRequest = url + "/movil/zpoSalidaCons";
                ZpoControlConsentimientosSalida[] envio = mSalidasCons.toArray(new ZpoControlConsentimientosSalida[mSalidasCons.size()]);
                HttpHeaders requestHeaders = new HttpHeaders();
                HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<ZpoControlConsentimientosSalida[]> requestEntity =
                        new HttpEntity<ZpoControlConsentimientosSalida[]>(envio, requestHeaders);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
                // Hace la solicitud a la red, pone la vivienda y espera un mensaje de respuesta del servidor
                ResponseEntity<String> response = restTemplate.exchange(urlRequest, HttpMethod.POST, requestEntity,
                        String.class);
                return response.getBody();
            }
            else{
                return "Datos recibidos!";
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return e.getMessage();
        }
    }

    /***************************************************/
    /********************* ZpoControlConsentimientosRecepcion******/
    /***************************************************/
    // url, username, password
    protected String uploadControlConsentimientosRecepcion(String url, String username,
                                                           String password) throws Exception {
        try {
            if(mRecepcionesCons.size()>0){
                publishProgress("Enviando recepciones de consentimientos!", String.valueOf(CONSREC), TOTAL_TASK);
                // La URL de la solicitud POST
                final String urlRequest = url + "/movil/zpoRecepcionCons";
                ZpoControlConsentimientosRecepcion[] envio = mRecepcionesCons.toArray(new ZpoControlConsentimientosRecepcion[mRecepcionesCons.size()]);
                HttpHeaders requestHeaders = new HttpHeaders();
                HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<ZpoControlConsentimientosRecepcion[]> requestEntity =
                        new HttpEntity<ZpoControlConsentimientosRecepcion[]>(envio, requestHeaders);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
                // Hace la solicitud a la red, pone la vivienda y espera un mensaje de respuesta del servidor
                ResponseEntity<String> response = restTemplate.exchange(urlRequest, HttpMethod.POST, requestEntity,
                        String.class);
                return response.getBody();
            }
            else{
                return "Datos recibidos!";
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return e.getMessage();
        }
    }

    /*******INFANTES*********/

    /***************************************************/
    /********************* ZpoInfantData******/
    /***************************************************/
    // url, username, password
    protected String uploadInfantData(String url, String username,
                                      String password) throws Exception {
        try {
            if(mInfantData.size()>0){
                publishProgress("Enviando datos de infantes!", String.valueOf(DAT_INFANTE), TOTAL_TASK);
                // La URL de la solicitud POST
                final String urlRequest = url + "/movil/zpoInfants";
                ZpoInfantData[] envio = mInfantData.toArray(new ZpoInfantData[mInfantData.size()]);
                HttpHeaders requestHeaders = new HttpHeaders();
                HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<ZpoInfantData[]> requestEntity =
                        new HttpEntity<ZpoInfantData[]>(envio, requestHeaders);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
                // Hace la solicitud a la red, pone la vivienda y espera un mensaje de respuesta del servidor
                ResponseEntity<String> response = restTemplate.exchange(urlRequest, HttpMethod.POST, requestEntity,
                        String.class);
                return response.getBody();
            }
            else{
                return "Datos recibidos!";
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return e.getMessage();
        }
    }

    /***************************************************/
    /********************* Zpo07InfantAssessmentVisit******/
    /***************************************************/
    // url, username, password
    protected String uploadInfantAssessment(String url, String username,
                                            String password) throws Exception {
        try {
            if(mInfantAssessment.size()>0){
                publishProgress("Enviando evaluaciones de infantes!", String.valueOf(EVAL_INFANTE), TOTAL_TASK);
                // La URL de la solicitud POST
                final String urlRequest = url + "/movil/zpo07InfantAssessmentVisits";
                Zpo07InfantAssessmentVisit[] envio = mInfantAssessment.toArray(new Zpo07InfantAssessmentVisit[mInfantAssessment.size()]);
                HttpHeaders requestHeaders = new HttpHeaders();
                HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<Zpo07InfantAssessmentVisit[]> requestEntity =
                        new HttpEntity<Zpo07InfantAssessmentVisit[]>(envio, requestHeaders);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
                // Hace la solicitud a la red, pone la vivienda y espera un mensaje de respuesta del servidor
                ResponseEntity<String> response = restTemplate.exchange(urlRequest, HttpMethod.POST, requestEntity,
                        String.class);
                return response.getBody();
            }
            else{
                return "Datos recibidos!";
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return e.getMessage();
        }
    }

    /***************************************************/
    /********************* Zpo07aInfantOpthResults******/
    /***************************************************/
    // url, username, password
    protected String uploadInfantOphtResults(String url, String username,
                                             String password) throws Exception {
        try {
            if( mAInfantOphtResults.size()>0){
                publishProgress("Enviando resultado oftalmologico infantes!", String.valueOf(OPHTH_RESULTS), TOTAL_TASK);
                // La URL de la solicitud POST
                final String urlRequest = url + "/movil/zpo07aInfantOphtResults";
                Zpo07aInfantOphtResults[] envio = mAInfantOphtResults.toArray(new Zpo07aInfantOphtResults[mAInfantOphtResults.size()]);
                HttpHeaders requestHeaders = new HttpHeaders();
                HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<Zpo07aInfantOphtResults[]> requestEntity =
                        new HttpEntity<Zpo07aInfantOphtResults[]>(envio, requestHeaders);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
                // Hace la solicitud a la red, pone la vivienda y espera un mensaje de respuesta del servidor
                ResponseEntity<String> response = restTemplate.exchange(urlRequest, HttpMethod.POST, requestEntity,
                        String.class);
                return response.getBody();
            }
            else{
                return "Datos recibidos!";
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return e.getMessage();
        }
    }


    /***************************************************/
    /********************* Zpo07bInfantAudioResults******/
    /***************************************************/
    // url, username, password
    protected String uploadInfantAudioResults(String url, String username,
                                              String password) throws Exception {
        try {
            if( mbInfantAudioResults.size()>0){
                publishProgress("Enviando resultado audiologico infantes!", String.valueOf(AUDIO_RESULTS), TOTAL_TASK);
                // La URL de la solicitud POST
                final String urlRequest = url + "/movil/zpo07bInfantAudioResults";
                Zpo07bInfantAudioResults[] envio = mbInfantAudioResults.toArray(new Zpo07bInfantAudioResults[mbInfantAudioResults.size()]);
                HttpHeaders requestHeaders = new HttpHeaders();
                HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<Zpo07bInfantAudioResults[]> requestEntity =
                        new HttpEntity<Zpo07bInfantAudioResults[]>(envio, requestHeaders);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
                // Hace la solicitud a la red, pone la vivienda y espera un mensaje de respuesta del servidor
                ResponseEntity<String> response = restTemplate.exchange(urlRequest, HttpMethod.POST, requestEntity,
                        String.class);
                return response.getBody();
            }
            else{
                return "Datos recibidos!";
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return e.getMessage();
        }
    }

    /***************************************************/
    /********************* Zpo07cInfantImageStudies******/
    /***************************************************/
    // url, username, password
    protected String uploadInfantImageStudies(String url, String username,
                                              String password) throws Exception {
        try {
            if( mcInfantImageStudies.size()>0){
                publishProgress("Enviando estudios de imagen!", String.valueOf(IMAGE_STUDIES), TOTAL_TASK);
                // La URL de la solicitud POST
                final String urlRequest = url + "/movil/zpo07cInfantImageStudies";
                Zpo07cInfantImageStudies[] envio = mcInfantImageStudies.toArray(new Zpo07cInfantImageStudies[mcInfantImageStudies.size()]);
                HttpHeaders requestHeaders = new HttpHeaders();
                HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<Zpo07cInfantImageStudies[]> requestEntity =
                        new HttpEntity<Zpo07cInfantImageStudies[]>(envio, requestHeaders);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
                // Hace la solicitud a la red, pone la vivienda y espera un mensaje de respuesta del servidor
                ResponseEntity<String> response = restTemplate.exchange(urlRequest, HttpMethod.POST, requestEntity,
                        String.class);
                return response.getBody();
            }
            else{
                return "Datos recibidos!";
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return e.getMessage();
        }
    }


    /***************************************************/
    /********************* Zpo07dInfantBayleyScales******/
    /***************************************************/
    // url, username, password
    protected String uploadInfantBayleyScales(String url, String username,
                                              String password) throws Exception {
        try {
            if( mdInfantBayleyScales.size()>0){
                publishProgress("Enviando escala de Bayley!", String.valueOf(BAYLEY_SCALES), TOTAL_TASK);
                // La URL de la solicitud POST
                final String urlRequest = url + "/movil/zpo07dInfantBayleyScales";
                Zpo07dInfantBayleyScales[] envio = mdInfantBayleyScales.toArray(new Zpo07dInfantBayleyScales[mdInfantBayleyScales.size()]);
                HttpHeaders requestHeaders = new HttpHeaders();
                HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<Zpo07dInfantBayleyScales[]> requestEntity =
                        new HttpEntity<Zpo07dInfantBayleyScales[]>(envio, requestHeaders);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
                // Hace la solicitud a la red, pone la vivienda y espera un mensaje de respuesta del servidor
                ResponseEntity<String> response = restTemplate.exchange(urlRequest, HttpMethod.POST, requestEntity,
                        String.class);
                return response.getBody();
            }
            else{
                return "Datos recibidos!";
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return e.getMessage();
        }
    }


    /***************************************************/
    /********************* ZpoEstadoInfante******/
    /***************************************************/
    // url, username, password
    protected String uploadInfantStatus(String url, String username,
                                        String password) throws Exception {
        try {
            if(mEstadoInfante.size()>0){
                publishProgress("Enviando datos de estado de infantes!", String.valueOf(ESTADO_INFANTE), TOTAL_TASK);
                // La URL de la solicitud POST
                final String urlRequest = url + "/movil/zpoEstadoInfantes";
                ZpoEstadoInfante[] envio = mEstadoInfante.toArray(new ZpoEstadoInfante[mEstadoInfante.size()]);
                HttpHeaders requestHeaders = new HttpHeaders();
                HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<ZpoEstadoInfante[]> requestEntity =
                        new HttpEntity<ZpoEstadoInfante[]>(envio, requestHeaders);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
                // Hace la solicitud a la red, pone la vivienda y espera un mensaje de respuesta del servidor
                ResponseEntity<String> response = restTemplate.exchange(urlRequest, HttpMethod.POST, requestEntity,
                        String.class);
                return response.getBody();
            }
            else{
                return "Datos recibidos!";
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return e.getMessage();
        }
    }

    /***************************************************/
    /********************* Zpo Visitas Fallidas ************************/
    /***************************************************/
    // url, username, password
    protected String uploadFailedVisits(String url, String username,
                                 String password) throws Exception {
        try {
            if(mVisitasFallidas.size()>0){
                publishProgress("Enviando visitas fallidas!", String.valueOf(VISITA_FALL), TOTAL_TASK);
                // La URL de la solicitud POST
                final String urlRequest = url + "/movil/zpoVisitasFallidas";
                ZpoVisitaFallida[] envio = mVisitasFallidas.toArray(new ZpoVisitaFallida[mVisitasFallidas.size()]);
                HttpHeaders requestHeaders = new HttpHeaders();
                HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                requestHeaders.setAuthorization(authHeader);
                HttpEntity<ZpoVisitaFallida[]> requestEntity =
                        new HttpEntity<ZpoVisitaFallida[]>(envio, requestHeaders);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
                restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
                // Hace la solicitud a la red, pone la visita y espera un mensaje de respuesta del servidor
                ResponseEntity<String> response = restTemplate.exchange(urlRequest, HttpMethod.POST, requestEntity,
                        String.class);
                return response.getBody();
            }
            else{
                return "Datos recibidos!";
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return e.getMessage();
        }
    }

}