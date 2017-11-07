package ni.org.ics.zpo.appmovil.utils;

/**
 * Created by FIRSTICT on 10/25/2016.
 * V1.0
 */
public class Zpo05DBConstants {

    //Tabla Zpo05Delivery
    public static String DELIVERY_TABLE = "zpo05_delivery";

    //Campos Zpo05Delivery
    public static final String recordId = "recordId";
    public static final String eventName = "eventName";
    public static final String deliVisitDate = "deliVisitDate";
    public static final String deliOriginInfo = "deliOriginInfo";
    public static final String deliMotherWt = "deliMotherWt";
    public static final String deliWtUnit = "deliWtUnit";
    public static final String deliSystolic = "deliSystolic";
    public static final String deliDiastolic = "deliDiastolic";
    public static final String deliTemperature = "deliTemperature";
    public static final String deliTempUnit = "deliTempUnit";
    public static final String deliDeliveryDate = "deliDeliveryDate";
    public static final String deliMode = "deliMode";
    public static final String deliDeliveryWho = "deliDeliveryWho";
    public static final String deliDeliveryOccur = "deliDeliveryOccur";
    public static final String deliHospitalId = "deliHospitalId";
    public static final String deliClinicId = "deliClinicId";
    public static final String deliDeliveryOther = "deliDeliveryOther";
    public static final String deliNumBirth = "deliNumBirth";
    public static final String deliFetalOutcome1 = "deliFetalOutcome1";
    public static final String deliCauseDeath1 = "deliCauseDeath1";
    public static final String deliSexBaby1 = "deliSexBaby1";
    public static final String deliFetalOutcome2 = "deliFetalOutcome2";
    public static final String deliCauseDeath2 = "deliCauseDeath2";
    public static final String deliSexBaby2 = "deliSexBaby2";
    public static final String deliFetalOutcome3 = "deliFetalOutcome3";
    public static final String deliCauseDeath3 = "deliCauseDeath3";
    public static final String deliSexBaby3 = "deliSexBaby3";
    public static final String deliConsentInfant = "deliConsentInfant";
    public static final String deliReasonNoconsent = "deliReasonNoconsent";
    public static final String deliNoconsentOther = "deliNoconsentOther";
    public static final String deliIdCompleting = "deliIdCompleting";
    public static final String deliDateCompleted = "deliDateCompleted";
    public static final String deliIdReviewer = "deliIdReviewer";
    public static final String deliDateReviewed = "deliDateReviewed";
    public static final String deliIdDataEntry = "deliIdDataEntry";
    public static final String deliDateEntered = "deliDateEntered";
    
    public static final String deliHyperDisease = "deliHyperDisease";
    public static final String deliPreterm1 = "deliPreterm1";
    public static final String deliPreterm2 = "deliPreterm2";
    public static final String deliPreterm3 = "deliPreterm3";
    public static final String deliDeliverEarly = "deliDeliverEarly";
    

    //Crear tabla Zpo05Delivery
    public static final String CREATE_DELIVERY_TABLE = "create table if not exists "
            + DELIVERY_TABLE + " ("
            + recordId + " text not null, "
            + eventName + " text, "
            + deliVisitDate + " date, "
            + deliOriginInfo + " text, "
            + deliMotherWt + " real, "
            + deliWtUnit + " text, "
            + deliSystolic + " integer, "
            + deliDiastolic + " integer, "
            + deliTemperature + " real, "
            + deliTempUnit + " text, "
            + deliDeliveryDate + " date, "
            + deliMode + " text, "
            + deliDeliveryWho + " text, "
            + deliDeliveryOccur + " text, "
            + deliHospitalId + " text, "
            + deliClinicId + " text, "
            + deliDeliveryOther + " text, "
            + deliNumBirth + " text, "
            + deliFetalOutcome1 + " text, "
            + deliCauseDeath1 + " text, "
            + deliSexBaby1 + " text, "
            + deliFetalOutcome2 + " text, "
            + deliCauseDeath2 + " text, "
            + deliSexBaby2 + " text, "
            + deliFetalOutcome3 + " text, "
            + deliCauseDeath3 + " text, "
            + deliSexBaby3 + " text, "
            + deliConsentInfant + " text, "
            + deliReasonNoconsent + " text, "
            + deliNoconsentOther + " text, "
            + deliIdCompleting + " text, "
            + deliDateCompleted + " date, "
            + deliIdReviewer + " text, "
            + deliDateReviewed + " date, "
            + deliIdDataEntry + " text, "
            + deliDateEntered + " date, "
            + deliHyperDisease + " text, "
            + deliPreterm1 + " text, "
            + deliPreterm2 + " text, "
            + deliPreterm3 + " text, "
            + deliDeliverEarly + " text, "
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
            + "primary key (" + recordId + ", "+ eventName +"));";


}
