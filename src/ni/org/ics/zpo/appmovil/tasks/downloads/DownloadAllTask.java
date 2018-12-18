package ni.org.ics.zpo.appmovil.tasks.downloads;

import android.content.Context;
import android.util.Log;
import ni.org.ics.zpo.appmovil.database.ZpoAdapter;
import ni.org.ics.zpo.appmovil.tasks.DownloadTask;
import ni.org.ics.zpo.domain.*;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;


public class DownloadAllTask extends DownloadTask {
	
	private final Context mContext;
	
	public DownloadAllTask(Context context) {
		mContext = context;
	}
	
	protected static final String TAG = DownloadAllTask.class.getSimpleName();
	private ZpoAdapter zpoA = null;
    private static final String TOTAL_TASK = "22";

    private List<Zpo00Screening> mTamizajes = null;
    private List<Zpo01StudyEntrySectionAtoB> mIngresosAD = null;
    private List<Zpo01StudyEntrySectionC> mIngresosE = null;
    private List<Zpo01StudyEntrySectionDtoF> mIngresosFK = null;
    private List<Zpo02BiospecimenCollection> mCollections = null;
    private List<Zpo04ExtendedSectionAtoD> mTrimesterVisitAD = null;
    private List<Zpo04ExtendedSectionE> mTrimesterVisitE = null;
    private List<Zpo04ExtendedSectionF> mTrimesterVisitFH = null;
    private List<Zpo05Delivery> mDeliverys = null;
    private List<Zpo08StudyExit> mExits = null;
    private List<ZpoEstadoEmbarazada> mStatus = null;
    private List<ZpoControlConsentimientosSalida> mZpoControlConsentimientosSalida = null;
    private List<ZpoControlConsentimientosRecepcion> mZpoControlConsentimientosRecepcion = null;
    private List<ZpoVisitaFallida> mVisitasFallidas = null;
    private List<ZpoInfantData> mInfantData = null;
    private List<ZpoEstadoInfante> mEstadoInfante = null;
    private List<Zpo07InfantAssessmentVisit> mInfantAssessment = null;
    private List<Zpo07aInfantOphtResults> mAInfantOphtResult = null;
    private List<Zpo07bInfantAudioResults> mbInfantAudioResult = null;
    private List<Zpo07cInfantImageStudies> mcInfantImageSt = null;
    private List<Zpo07dInfantBayleyScales> mdInfantBayleySc = null;
    private List<Zpo07InfantOtoacousticEmissions> mOtoEmissions = null;
    private List<Zpo04ExtendedSectionAtoF> mTrimesterVisitAF = null;

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
    public static final int OTOEMI = 22;
    public static final int EXTENDEDAF = 23;
    
	private String error = null;
	private String url = null;
	private String username = null;
	private String password = null;
    private int v =0;
	
	@Override
	protected String doInBackground(String... values) {
		url = values[0];
		username = values[1];
		password = values[2];

		try {
			error = descargarDatos();
			if (error!=null) return error;
		} catch (Exception e) {
			// Regresa error al descargar
			e.printStackTrace();
			return e.getLocalizedMessage();
		}
		publishProgress("Abriendo base de datos...","1","1");
		zpoA = new ZpoAdapter(mContext, password, false,false);
		zpoA.open();
        //Borrar los datos de la base de datos
        zpoA.borrarZpo00Screening();
        zpoA.borrarZpo01StudyEntrySectionAtoB();
        zpoA.borrarZpo01StudyEntrySectionC();
        zpoA.borrarZpo01StudyEntrySectionDtoF();
        zpoA.borrarZpo02BiospecimenCollection();
        zpoA.borrarZpo04ExtendedSectionAtoD();
        zpoA.borrarZpo04ExtendedSectionE();
        zpoA.borrarZpo04ExtendedSectionF();
        zpoA.borrarZpo05Delivery();
        zpoA.borrarZpo08StudyExit();
        zpoA.borrarZpoEstadoMadre();
        zpoA.borrarZpoControlConsentimientosSalida();
        zpoA.borrarZpoControlConsentimientosRecepcion();
        zpoA.borrarZpo07InfantAssessmentVisit();
        zpoA.borrarZpo07aInfantOphtResults();
        zpoA.borrarZpo07bInfantAudioResults();
        zpoA.borrarZpo07cInfantImageStudies();
        zpoA.borrarZpo07dInfantBayleyScales();
        zpoA.borrarZpoVisitaFallida();
        zpoA.borrarZpo07InfantOtoacousticE();
        zpoA.borrarZpo04ExtendedSectionAtoF();

        zpoA.borrarZpoInfantData();
        zpoA.borrarZpoEstadoInfante();

        try {

            if (mTamizajes != null){
                v = mTamizajes.size();
                ListIterator<Zpo00Screening> iter = mTamizajes.listIterator();
                while (iter.hasNext()){
                    zpoA.crearZpo00Screening(iter.next());
                    publishProgress("Insertando tamizajes en la base de datos...", Integer.valueOf(iter.nextIndex()).toString(), Integer
                            .valueOf(v).toString());
                }
            }
            if (mStatus != null){
                v = mStatus.size();
                ListIterator<ZpoEstadoEmbarazada> iter = mStatus.listIterator();
                while (iter.hasNext()){
                    zpoA.crearZpoEstadoMadre(iter.next());
                    publishProgress("Insertando estado de las madres en la base de datos...", Integer.valueOf(iter.nextIndex()).toString(), Integer
                            .valueOf(v).toString());
                }
            }
            if (mInfantData != null){
                v = mInfantData.size();
                ListIterator<ZpoInfantData> iter = mInfantData.listIterator();
                while (iter.hasNext()){
                    zpoA.crearZpoInfantData(iter.next());
                    publishProgress("Insertando datos de infantes en la base de datos...", Integer.valueOf(iter.nextIndex()).toString(), Integer
                            .valueOf(v).toString());
                }
            }
            if (mEstadoInfante != null){
                v = mEstadoInfante.size();
                ListIterator<ZpoEstadoInfante> iter = mEstadoInfante.listIterator();
                while (iter.hasNext()){
                    zpoA.crearZpoEstadoInfante(iter.next());
                    publishProgress("Insertando datos de estado de infantes en la base de datos...", Integer.valueOf(iter.nextIndex()).toString(), Integer
                            .valueOf(v).toString());
                }
            }
            if (mIngresosAD != null){
                v = mIngresosAD.size();
                ListIterator<Zpo01StudyEntrySectionAtoB> iter = mIngresosAD.listIterator();
                while (iter.hasNext()){
                    zpoA.crearZpo01StudyEntrySectionAtoB(iter.next());
                    publishProgress("Insertando ingresos(1) en la base de datos...", Integer.valueOf(iter.nextIndex()).toString(), Integer
                            .valueOf(v).toString());
                }
            }
            if (mIngresosE != null){
                v = mIngresosE.size();
                ListIterator<Zpo01StudyEntrySectionC> iter = mIngresosE.listIterator();
                while (iter.hasNext()){
                    zpoA.crearZpo01StudyEntrySectionC(iter.next());
                    publishProgress("Insertando ingresos(2) en la base de datos...", Integer.valueOf(iter.nextIndex()).toString(), Integer
                            .valueOf(v).toString());
                }
            }
            if (mIngresosFK != null){
                v = mIngresosFK.size();
                ListIterator<Zpo01StudyEntrySectionDtoF> iter = mIngresosFK.listIterator();
                while (iter.hasNext()){
                    zpoA.crearZpo01StudyEntrySectionDtoF(iter.next());
                    publishProgress("Insertando ingresos(3) en la base de datos...", Integer.valueOf(iter.nextIndex()).toString(), Integer
                            .valueOf(v).toString());
                }
            }
            if (mTrimesterVisitAD != null){
                v = mTrimesterVisitAD.size();
                ListIterator<Zpo04ExtendedSectionAtoD> iter = mTrimesterVisitAD.listIterator();
                while (iter.hasNext()){
                    zpoA.crearZpo04ExtendedSectionAtoD(iter.next());
                    publishProgress("Insertando formulario extendido(1) en la base de datos...", Integer.valueOf(iter.nextIndex()).toString(), Integer
                            .valueOf(v).toString());
                }
            }
            if (mTrimesterVisitE != null){
                v = mTrimesterVisitE.size();
                ListIterator<Zpo04ExtendedSectionE> iter = mTrimesterVisitE.listIterator();
                while (iter.hasNext()){
                    zpoA.crearZpo04ExtendedSectionE(iter.next());
                    publishProgress("Insertando formulario extendido(2) en la base de datos...", Integer.valueOf(iter.nextIndex()).toString(), Integer
                            .valueOf(v).toString());
                }
            }
            if (mTrimesterVisitFH != null){
                v = mTrimesterVisitFH.size();
                ListIterator<Zpo04ExtendedSectionF> iter = mTrimesterVisitFH.listIterator();
                while (iter.hasNext()){
                    zpoA.crearZpo04ExtendedSectionF(iter.next());
                    publishProgress("Insertando formulario extendido(3) en la base de datos...", Integer.valueOf(iter.nextIndex()).toString(), Integer
                            .valueOf(v).toString());
                }
            }
            if (mDeliverys != null){
                v = mDeliverys.size();
                ListIterator<Zpo05Delivery> iter = mDeliverys.listIterator();
                while (iter.hasNext()){
                    zpoA.crearZpo05Delivery(iter.next());
                    publishProgress("Insertando partos en la base de datos...", Integer.valueOf(iter.nextIndex()).toString(), Integer
                            .valueOf(v).toString());
                }
            }
            if (mInfantAssessment != null){
                v = mInfantAssessment.size();
                ListIterator<Zpo07InfantAssessmentVisit> iter = mInfantAssessment.listIterator();
                while (iter.hasNext()){
                    zpoA.crearZpo07InfantAssessmentVisit(iter.next());
                    publishProgress("Insertando evaluaciones de infantes en la base de datos...", Integer.valueOf(iter.nextIndex()).toString(), Integer
                            .valueOf(v).toString());
                }
            }
            if (mAInfantOphtResult != null){
                v = mAInfantOphtResult.size();
                ListIterator<Zpo07aInfantOphtResults> iter = mAInfantOphtResult.listIterator();
                while (iter.hasNext()){
                    zpoA.crearZpo07aInfantOphtResults(iter.next());
                    publishProgress("Insertando resultados oftalmologicos de infantes...", Integer.valueOf(iter.nextIndex()).toString(), Integer
                            .valueOf(v).toString());
                }
            }

            if (mbInfantAudioResult != null){
                v = mbInfantAudioResult.size();
                ListIterator<Zpo07bInfantAudioResults> iter = mbInfantAudioResult.listIterator();
                while (iter.hasNext()){
                    zpoA.crearZpo07bInfantAudioResults(iter.next());
                    publishProgress("Insertando resultados audiologicos de infantes...", Integer.valueOf(iter.nextIndex()).toString(), Integer
                            .valueOf(v).toString());
                }
            }

            if (mcInfantImageSt != null){
                v = mcInfantImageSt.size();
                ListIterator<Zpo07cInfantImageStudies> iter = mcInfantImageSt.listIterator();
                while (iter.hasNext()){
                    zpoA.crearZpo07cInfantImageStudies(iter.next());
                    publishProgress("Insertando estudios de imagenes...", Integer.valueOf(iter.nextIndex()).toString(), Integer
                            .valueOf(v).toString());
                }
            }

            if (mdInfantBayleySc != null){
                v = mdInfantBayleySc.size();
                ListIterator<Zpo07dInfantBayleyScales> iter = mdInfantBayleySc.listIterator();
                while (iter.hasNext()){
                    zpoA.crearZpo07dInfantBayleyScales(iter.next());
                    publishProgress("Insertando escala Bayley...", Integer.valueOf(iter.nextIndex()).toString(), Integer
                            .valueOf(v).toString());
                }
            }
            if (mCollections != null){
                v = mCollections.size();
                ListIterator<Zpo02BiospecimenCollection> iter = mCollections.listIterator();
                while (iter.hasNext()){
                    zpoA.crearZpo02BiospecimenCollection(iter.next());
                    publishProgress("Insertando muestras en la base de datos...", Integer.valueOf(iter.nextIndex()).toString(), Integer
                            .valueOf(v).toString());
                }
            }
            if (mZpoControlConsentimientosSalida != null){
                v = mZpoControlConsentimientosSalida.size();
                ListIterator<ZpoControlConsentimientosSalida> iter = mZpoControlConsentimientosSalida.listIterator();
                while (iter.hasNext()){
                    zpoA.crearZpoControlConsentimientosSalida(iter.next());
                    publishProgress("Insertando salidas de consentimientos en la base de datos...", Integer.valueOf(iter.nextIndex()).toString(), Integer
                            .valueOf(v).toString());
                }
            }
            if (mZpoControlConsentimientosRecepcion != null){
                v = mZpoControlConsentimientosRecepcion.size();
                ListIterator<ZpoControlConsentimientosRecepcion> iter = mZpoControlConsentimientosRecepcion.listIterator();
                while (iter.hasNext()){
                    zpoA.crearZpoControlConsentimientosRecepcion(iter.next());
                    publishProgress("Insertando recepcion de consentimientos en la base de datos...", Integer.valueOf(iter.nextIndex()).toString(), Integer
                            .valueOf(v).toString());
                }
            }
            if (mExits != null){
                v = mExits.size();
                ListIterator<Zpo08StudyExit> iter = mExits.listIterator();
                while (iter.hasNext()){
                    zpoA.crearZpo08StudyExit(iter.next());
                    publishProgress("Insertando salidas del estudio en la base de datos...", Integer.valueOf(iter.nextIndex()).toString(), Integer
                            .valueOf(v).toString());
                }
            }
            if (mVisitasFallidas != null){
                v = mVisitasFallidas.size();
                ListIterator<ZpoVisitaFallida> iter = mVisitasFallidas.listIterator();
                while (iter.hasNext()){
                    zpoA.crearZpoVisitaFallida(iter.next());
                    publishProgress("Insertando visitas fallidas en la base de datos...", Integer.valueOf(iter.nextIndex()).toString(), Integer
                            .valueOf(v).toString());
                }
            }

            if (mOtoEmissions != null){
                v = mOtoEmissions.size();
                ListIterator<Zpo07InfantOtoacousticEmissions> iter = mOtoEmissions.listIterator();
                while (iter.hasNext()){
                    zpoA.crearZpo07InfantOtoacousticEm(iter.next());
                    publishProgress("Insertando Emisiones Otoacústicas en la base de datos...", Integer.valueOf(iter.nextIndex()).toString(), Integer
                            .valueOf(v).toString());
                }
            }

            if (mTrimesterVisitAF != null){
                v = mTrimesterVisitAF.size();
                ListIterator<Zpo04ExtendedSectionAtoF> iter = mTrimesterVisitAF.listIterator();
                while (iter.hasNext()){
                    zpoA.crearZpo04ExtendedSectionAtoF(iter.next());
                    publishProgress("Insertando formulario Factores de Riesgo (A-F) en la base de datos...", Integer.valueOf(iter.nextIndex()).toString(), Integer
                            .valueOf(v).toString());
                }
            }

        } catch (Exception e) {
            // Regresa error al insertar
            e.printStackTrace();
            zpoA.close();
            return e.getLocalizedMessage();
        }
		zpoA.close();
		return error;
	}

    // url, username, password
    protected String descargarDatos() throws Exception {
        try {
            // The URL for making the GET request
            String urlRequest;
            // Set the Accept header for "application/json"
            HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
            HttpHeaders requestHeaders = new HttpHeaders();
            List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
            acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
            requestHeaders.setAccept(acceptableMediaTypes);
            requestHeaders.setAuthorization(authHeader);
            // Populate the headers in an HttpEntity object to use for the request
            HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
            // Create a new RestTemplate instance
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            //Descargar tamizajes
            urlRequest = url + "/movil/zpo00Screenings/{username}";
            publishProgress("Solicitando tamizajes",String.valueOf(TAMIZAJE),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<Zpo00Screening[]> responseEntityZpo00Screening = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    Zpo00Screening[].class, username);
            // convert the array to a list and return it
            mTamizajes = Arrays.asList(responseEntityZpo00Screening.getBody());

            //Descargar estado de embarazadas
            urlRequest = url + "/movil/zpoEstadoEmb/{username}";
            publishProgress("Solicitando estado de embarazadas",String.valueOf(ESTADO),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<ZpoEstadoEmbarazada[]> responseZpoEstadoEmbarazada = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    ZpoEstadoEmbarazada[].class, username);
            // convert the array to a list and return it
            mStatus = Arrays.asList(responseZpoEstadoEmbarazada.getBody());

            //Descargar datos de infantes
            urlRequest = url + "/movil/zpoInfants/{username}";
            publishProgress("Solicitando datos de infantes",String.valueOf(DAT_INFANTE),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<ZpoInfantData[]> responseZpoInfantData = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    ZpoInfantData[].class, username);
            // convert the array to a list and return it
            mInfantData = Arrays.asList(responseZpoInfantData.getBody());

            //Descargar datos de estado de infantes
            urlRequest = url + "/movil/zpoEstadoInfantes/{username}";
            publishProgress("Solicitando datos de estado de infantes",String.valueOf(ESTADO_INFANTE),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<ZpoEstadoInfante[]> responseZpoEstadoInfante = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    ZpoEstadoInfante[].class, username);
            // convert the array to a list and return it
            mEstadoInfante = Arrays.asList(responseZpoEstadoInfante.getBody());

            //Descargar ingresos parte 1
            urlRequest = url + "/movil/zpo01StudyEntrySectionAtoBs/{username}";
            publishProgress("Solicitando ingresos (1)",String.valueOf(INGRESO1),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<Zpo01StudyEntrySectionAtoB[]> responseEntityZpo01StudyEntrySectionAtoB = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    Zpo01StudyEntrySectionAtoB[].class, username);
            // convert the array to a list and return it
            mIngresosAD = Arrays.asList(responseEntityZpo01StudyEntrySectionAtoB.getBody());

            //Descargar ingresos parte 2
            urlRequest = url + "/movil/zpo01StudyEntrySectionCs/{username}";
            publishProgress("Solicitando ingresos (2)",String.valueOf(INGRESO2),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<Zpo01StudyEntrySectionC[]> responseEntityZpo01StudyEntrySectionC = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    Zpo01StudyEntrySectionC[].class, username);
            // convert the array to a list and return it
            mIngresosE = Arrays.asList(responseEntityZpo01StudyEntrySectionC.getBody());

            //Descargar ingresos parte 3
            urlRequest = url + "/movil/zpo01StudyEntrySectionDtoFs/{username}";
            publishProgress("Solicitando ingresos (3)",String.valueOf(INGRESO3),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<Zpo01StudyEntrySectionDtoF[]> responseZpo01StudyEntrySectionDtoF = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    Zpo01StudyEntrySectionDtoF[].class, username);
            // convert the array to a list and return it
            mIngresosFK = Arrays.asList(responseZpo01StudyEntrySectionDtoF.getBody());

            //Descargar formulario extendido parte 1
            urlRequest = url + "/movil/zpo04ExtendedSectionAtoDs/{username}";
            publishProgress("Solicitando formulario extendido (1)",String.valueOf(EXTENDED1),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<Zpo04ExtendedSectionAtoD[]> responseZpo04ExtendedSectionAtoD = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    Zpo04ExtendedSectionAtoD[].class, username);
            // convert the array to a list and return it
            mTrimesterVisitAD = Arrays.asList(responseZpo04ExtendedSectionAtoD.getBody());

            //Descargar formulario extendido parte 2
            urlRequest = url + "/movil/zpo04ExtendedSectionEs/{username}";
            publishProgress("Solicitando formulario extendido (2)",String.valueOf(EXTENDED2),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<Zpo04ExtendedSectionE[]> responseZpo04ExtendedSectionE = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    Zpo04ExtendedSectionE[].class, username);
            // convert the array to a list and return it
            mTrimesterVisitE = Arrays.asList(responseZpo04ExtendedSectionE.getBody());

            //Descargar formulario extendido parte 3
            urlRequest = url + "/movil/zpo04ExtendedSectionFs/{username}";
            publishProgress("Solicitando formulario extendido (3)",String.valueOf(EXTENDED3),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<Zpo04ExtendedSectionF[]> responseZpo04ExtendedSectionF = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    Zpo04ExtendedSectionF[].class, username);
            // convert the array to a list and return it
            mTrimesterVisitFH = Arrays.asList(responseZpo04ExtendedSectionF.getBody());

            //Descargar partos
            urlRequest = url + "/movil/zpo05Deliverys/{username}";
            publishProgress("Solicitando partos",String.valueOf(PARTO),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<Zpo05Delivery[]> responseZpo05Delivery = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    Zpo05Delivery[].class, username);
            // convert the array to a list and return it
            mDeliverys = Arrays.asList(responseZpo05Delivery.getBody());

            //Descargar evaluaciones de infantes
            urlRequest = url + "/movil/zpo07InfantAssessmentVisits/{username}";
            publishProgress("Solicitando evaluaciones de infantes",String.valueOf(EVAL_INFANTE),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<Zpo07InfantAssessmentVisit[]> responseZpo07InfantAssessmentVisit = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    Zpo07InfantAssessmentVisit[].class, username);
            // convert the array to a list and return it
            mInfantAssessment = Arrays.asList(responseZpo07InfantAssessmentVisit.getBody());

            //Descargar resultados oftalmologicos
            urlRequest = url + "/movil/zpo07aInfantOphtResults/{username}";
            publishProgress("Solicitando resultados oftalmologicos de infantes",String.valueOf(OPHTH_RESULTS),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<Zpo07aInfantOphtResults[]> responseZpo07aOphtResults = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    Zpo07aInfantOphtResults[].class, username);
            // convert the array to a list and return it
            mAInfantOphtResult = Arrays.asList(responseZpo07aOphtResults.getBody());

            //Descargar resultados audiologicos
            urlRequest = url + "/movil/zpo07bInfantAudioResults/{username}";
            publishProgress("Solicitando resultados audiologicos de infantes",String.valueOf(AUDIO_RESULTS),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<Zpo07bInfantAudioResults[]> responseZpo07bAudioResults = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    Zpo07bInfantAudioResults[].class, username);
            // convert the array to a list and return it
            mbInfantAudioResult = Arrays.asList(responseZpo07bAudioResults.getBody());

            //Descargar estudios de imagenes
            urlRequest = url + "/movil/zpo07cInfantImageStudies/{username}";
            publishProgress("Solicitando estudios de imagenes de infantes",String.valueOf(IMAGE_STUDIES),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<Zpo07cInfantImageStudies[]> responseZpo07cImageStudies = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    Zpo07cInfantImageStudies[].class, username);
            // convert the array to a list and return it
            mcInfantImageSt = Arrays.asList(responseZpo07cImageStudies.getBody());

            //Descargar escala de bayley
            urlRequest = url + "/movil/zpo07dInfantBayleyScales/{username}";
            publishProgress("Solicitando escala de Bayley de infantes",String.valueOf(BAYLEY_SCALES),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<Zpo07dInfantBayleyScales[]> responseZpo07dBayleyScales = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    Zpo07dInfantBayleyScales[].class, username);
            // convert the array to a list and return it
            mdInfantBayleySc = Arrays.asList(responseZpo07dBayleyScales.getBody());

            //Descargar muestras
            urlRequest = url + "/movil/zpo02BiospecimenCollections/{username}";
            publishProgress("Solicitando muestras",String.valueOf(MUESTRAS),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<Zpo02BiospecimenCollection[]> responseZpo02BiospecimenCollection = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    Zpo02BiospecimenCollection[].class, username);
            // convert the array to a list and return it
            mCollections = Arrays.asList(responseZpo02BiospecimenCollection.getBody());

            //Descargar salidas de consentimientos
            urlRequest = url + "/movil/zpoSalidaCons";
            publishProgress("Solicitando ZpoControlConsentimientosSalida",String.valueOf(CONSSAL),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<ZpoControlConsentimientosSalida[]> responseZpoControlConsentimientosSalida = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    ZpoControlConsentimientosSalida[].class, username);
            // convert the array to a list and return it
            mZpoControlConsentimientosSalida = Arrays.asList(responseZpoControlConsentimientosSalida.getBody());

            //Descargar recepcion de consentimientos
            urlRequest = url + "/movil/zpoRecepcionCons";
            publishProgress("Solicitando ZpoControlConsentimientosRecepcion",String.valueOf(CONSREC),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<ZpoControlConsentimientosRecepcion[]> responseZpoControlConsentimientosRecepcion = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    ZpoControlConsentimientosRecepcion[].class, username);
            // convert the array to a list and return it
            mZpoControlConsentimientosRecepcion = Arrays.asList(responseZpoControlConsentimientosRecepcion.getBody());

            //Descargar salidas del estudio
            urlRequest = url + "/movil/zpo08StudyExits/{username}";
            publishProgress("Solicitando salidas del estudio",String.valueOf(SALIDA),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<Zpo08StudyExit[]> responseZpo08StudyExit = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    Zpo08StudyExit[].class, username);
            // convert the array to a list and return it
            mExits = Arrays.asList(responseZpo08StudyExit.getBody());

            //Descargar visitas fallidas
            urlRequest = url + "/movil/zpoVisitasFallidas";
            publishProgress("Solicitando visitas fallidas",String.valueOf(VISITA_FALL),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<ZpoVisitaFallida[]> responseZpoVisitaFallida = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    ZpoVisitaFallida[].class, username);
            // convert the array to a list and return it
            mVisitasFallidas = Arrays.asList(responseZpoVisitaFallida.getBody());

            //Descargar Emisiones Otoacústicas
            urlRequest = url + "/movil/zpo07InfantOtoacousticEms";
            publishProgress("Solicitando Emisiones Otoacústicas", String.valueOf(OTOEMI), TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<Zpo07InfantOtoacousticEmissions[]> responseZp07OtoEmi = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    Zpo07InfantOtoacousticEmissions[].class, username);
            // convert the array to a list and return it
            mOtoEmissions = Arrays.asList(responseZp07OtoEmi.getBody());

            //Descargar formulario factores de reiesgo
            urlRequest = url + "/movil/zpo04ExtendedSectionAtoFs/{username}";
            publishProgress("Solicitando formulario Factores de Riesgo (A-F)",String.valueOf(EXTENDEDAF),TOTAL_TASK);
            // Perform the HTTP GET request
            ResponseEntity<Zpo04ExtendedSectionAtoF[]> responseZpo04ExtendedSectionAtoF = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
                    Zpo04ExtendedSectionAtoF[].class, username);
            // convert the array to a list and return it
            mTrimesterVisitAF = Arrays.asList(responseZpo04ExtendedSectionAtoF.getBody());

            return null;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            return e.getLocalizedMessage();
        }
    }
}
