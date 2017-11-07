package ni.org.ics.zpo.appmovil.utils;

/**
 * Created by FIRSTICT on 10/21/2016.
 * V1.0
 */
public class Zpo02DBConstants {

    //Tabla Zp02BiospecimenCollection
    public static final String BIOCOLLECTION_TABLE = "zpo02_biospecimen_collection";

    //Campos Zp02BiospecimenCollection
    public static final String recordId = "recordId";
    public static final String eventName = "eventName";
    public static final String bscDov = "bscDov";
    public static final String bscVisit = "bscVisit";
    public static final String bscMatBldCol = "bscMatBldCol";
    public static final String bscMatBldTyp1 = "bscMatBldTyp1";
    public static final String bscMatBldId1 = "bscMatBldId1";
    public static final String bscMatBldVol1 = "bscMatBldVol1";
    public static final String bscMatBldTime = "bscMatBldTime";
    public static final String bscMatBldCom = "bscMatBldCom";
    public static final String bscPerson1 = "bscPerson1";
    public static final String bscCompleteDate1 = "bscCompleteDate1";
    public static final String bscPerson2 = "bscPerson2";
    public static final String bscCompleteDate2 = "bscCompleteDate2";
    public static final String bscPerson3 = "bscPerson3";
    public static final String bscCompleteDate3 = "bscCompleteDate3";

    //Crear tabla Zp02BiospecimenCollection
    public static final String CREATE_BIOCOLLECTION_TABLE = "create table if not exists "
            + BIOCOLLECTION_TABLE + " ("
            + recordId + " text not null, "
            + eventName + " text, "
            + bscDov + " date, "
            + bscVisit + " text, "
            + bscMatBldCol  + " text, "
            + bscMatBldTyp1 + " text, "
            + bscMatBldId1  + " text, "
            + bscMatBldVol1  + " integer, "
            + bscMatBldTime + " text, "
            + bscMatBldCom + " text, "
            + bscPerson1 + " text, "
            + bscCompleteDate1 + " date, "
            + bscPerson2 + " text, "
            + bscCompleteDate2 + " date, "
            + bscPerson3 + " text, "
            + bscCompleteDate3 + " date, "
            + MainDBConstants.recordDate + " date, "
            + MainDBConstants.recordUser + " text, "
            + MainDBConstants.pasive + " text, "
            + MainDBConstants.ID_INSTANCIA + " integer,"
            + MainDBConstants.FILE_PATH + " text,"
            + MainDBConstants.STATUS + " text not null, "
            + MainDBConstants.START  + " text, "
            + MainDBConstants.END  + " text, "
            + MainDBConstants.DEVICE_ID  + " text, "
            + MainDBConstants.SIM_SERIAL + " text, "
            + MainDBConstants.PHONE_NUMBER  + " text, "
            + MainDBConstants.TODAY  + " date, "
            + "primary key (" + recordId + ", "+eventName+"));";

}
