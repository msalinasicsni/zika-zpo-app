package ni.org.ics.zpo.appmovil.utils;

/**
 * Created by FIRSTICT on 10/24/2016.
 * V1.0
 */
public class Zpo04AFDBConstants {

    //Campos comunes
    public static final String recordId = "recordId";
    public static final String eventName = "eventName";

    //Tabla Zpo04ExtendedSectionAtoD
    public static final String EXTENDED_AF_TABLE = "zpo04_extended_section_a_to_f";

    //Campos de Zpo04ExtendedSectionAtoF
    public static final String triDov = "triDov";
    public static final String triPrimJobInd = "triPrimJobInd";
    public static final String triPrimJobHours = "triPrimJobHours";
    public static final String triHouseSitInd = "triHouseSitInd";
    public static final String triCity = "triCity";
    public static final String triState = "triState";
    public static final String triCountry = "triCountry";
    public static final String triResidRef = "triResidRef";
    public static final String triCurrResidDurRef = "triCurrResidDurRef";
    public static final String triCurrResidDur = "triCurrResidDur";
    public static final String triNbhoodTyp = "triNbhoodTyp";
    public static final String triResidTyp = "triResidTyp";
    public static final String triResidTypSpecify = "triResidTypSpecify";
    public static final String triFloorMat = "triFloorMat";
    public static final String triFloorMatSpecify = "triFloorMatSpecify";
    public static final String triWallMat = "triWallMat";
    public static final String triWallMatSpecify = "triWallMatSpecify";
    public static final String triRoofMat = "triRoofMat";
    public static final String triRoofMatSpecify = "triRoofMatSpecify";
    public static final String triTrashDispos = "triTrashDispos";
    public static final String triTrashDisposSpecify = "triTrashDisposSpecify";
    public static final String triNumTotalRooms = "triNumTotalRooms";
    public static final String triNumSleepRooms = "triNumSleepRooms";
    public static final String triNumPeople = "triNumPeople";
    public static final String triScreensInd = "triScreensInd";
    public static final String triHouseAmenities = "triHouseAmenities";
    public static final String triTransAccess = "triTransAccess";
    public static final String triPrimWaterSrc = "triPrimWaterSrc";
    public static final String triWaterContainInd = "triWaterContainInd";
    public static final String triWaterContainTyp = "triWaterContainTyp";
    public static final String triWaterConSpecify = "triWaterConSpecify";
    public static final String triWaterTreatHome = "triWaterTreatHome";
    public static final String triWaterTreatFreq = "triWaterTreatFreq";
    public static final String triToiletTyp = "triToiletTyp";
    public static final String triOpSewageInd = "triOpSewageInd";
    public static final String triAnimalsInd = "triAnimalsInd";
    public static final String triAnimalTyp = "triAnimalTyp";
    public static final String triAnimalSpecify= "triAnimalSpecify";
    public static final String triNumOtherAnimal = "triNumOtherAnimal";
    public static final String triNumCattle = "triNumCattle";
    public static final String triNumPig = "triNumPig";
    public static final String triNumFowl = "triNumFowl";
    public static final String triNumGoatsSheep = "triNumGoatsSheep";
    public static final String triSmokeInd = "triSmokeInd";
    public static final String triDrinkInd = "triDrinkInd";
    public static final String triDrinkEverInd = "triDrinkEverInd";
    public static final String triBugNuisInd = "triBugNuisInd";
    public static final String triPestStorHomeInd = "triPestStorHomeInd";
    public static final String triPestAppHomeInd = "triPestAppHomeInd";
    public static final String triPestAppDay = "triPestAppDay";
    public static final String triPestAppMonth = "triPestAppMonth";
    public static final String triPestAppYear = "triPestAppYear";
    public static final String triPestAppName = "triPestAppName";
    public static final String triHomeTrtdInsctInd = "triHomeTrtdInsctInd";
    public static final String triHomeTrtdLoc = "triHomeTrtdLoc";
    public static final String triHomeTrtdEntity = "triHomeTrtdEntity";
    public static final String triHomeTrtdNames = "triHomeTrtdNames";
    public static final String triTrtmntAppDay = "triTrtmntAppDay";
    public static final String triTrtmntAppMonth = "triTrtmntAppMonth";
    public static final String triTrtmntAppYear = "triTrtmntAppYear";
    public static final String triLwnTrtmntAppInd = "triLwnTrtmntAppInd";
    public static final String triLwnTrtmntAppDay = "triLwnTrtmntAppDay";
    public static final String triLwnTrtmntAppMonth = "triLwnTrtmntAppMonth";
    public static final String triLwnTrtmntAppYear = "triLwnTrtmntAppYear";
    public static final String triLwnTrtmntAppName = "triLwnTrtmntAppName";
    public static final String triMosqRepInd = "triMosqRepInd";
    public static final String triMosqRepTyp = "triMosqRepTyp";
    public static final String triMosqRepNameSpray = "triMosqRepNameSpray";
    public static final String triMosqRepDkSpray = "triMosqRepDkSpray";
    public static final String triMosqRepNameLotion = "triMosqRepNameLotion";
    public static final String triMosqRepDkLotion = "triMosqRepDkLotion";
    public static final String triMosqRepNameSpiral = "triMosqRepNameSpiral";
    public static final String triMosqRepDkSpiral = "triMosqRepDkSpiral";
    public static final String triMosqRepNamePlugin = "triMosqRepNamePlugin";
    public static final String triMosqRepDkPlugin = "triMosqRepDkPlugin";
    public static final String triMosqRepNameOther = "triMosqRepNameOther";
    public static final String triMosqRepDkOther = "triMosqRepDkOther";
    public static final String triCompId = "triCompId";
    public static final String triCompDat = "triCompDat";
    public static final String triRevId = "triRevId";
    public static final String triRevDat = "triRevDat";
    public static final String triEntId = "triEntId";
    public static final String triEntDat = "triEntDat";

    //Crear tabla Zpo04ExtendedSectionAtoF
    public static final String CREATE_EXTENDED_AF_TABLE = "create table if not exists "
            + EXTENDED_AF_TABLE + " ("
            + recordId + " text not null, "
            + eventName + " text, "
            + triDov + " date, "
            + triPrimJobInd + " text, "
            + triPrimJobHours + " text, "
            + triHouseSitInd + " text, "
            + triCity + " text, "
            + triState + " text, "
            + triCountry + " text, "
            + triResidRef + " text, "
            + triCurrResidDur + " text, "
            + triCurrResidDurRef + " text, "
            + triNbhoodTyp + " text, "
            + triResidTyp + " text, "
            + triResidTypSpecify + " text, "
            + triFloorMat + " text, "
            + triFloorMatSpecify + " text, "
            + triWallMat + " text, "
            + triWallMatSpecify + " text, "
            + triRoofMat + " text, "
            + triRoofMatSpecify + " text, "
            + triTrashDispos + " text, "
            + triTrashDisposSpecify + " text, "
            + triNumTotalRooms + " integer, "
            + triNumSleepRooms + " integer, "
            + triNumPeople + " integer, "
            + triScreensInd + " text, "
            + triHouseAmenities + " text, "
            + triTransAccess + " text, "
            + triPrimWaterSrc + " text, "
            + triWaterContainInd + " text, "
            + triWaterContainTyp + " text, "
            + triWaterConSpecify + " text, "
            + triWaterTreatHome + " text, "
            + triWaterTreatFreq + " text, "
            + triToiletTyp + " text, "
            + triOpSewageInd + " text, "
            + triAnimalsInd + " text, "
            + triAnimalTyp + " text, "
            + triAnimalSpecify + " text, "
            + triNumOtherAnimal + " integer, "
            + triNumCattle + " integer, "
            + triNumPig + " integer, "
            + triNumFowl + " integer, "
            + triNumGoatsSheep + " integer, "
            + triSmokeInd + " text, "
            + triDrinkInd + " text, "
            + triDrinkEverInd + " text, "
            + triBugNuisInd + " text, "
            + triPestStorHomeInd + " text, "
            + triPestAppHomeInd + " text, "
            + triPestAppDay + " text, "
            + triPestAppMonth + " text, "
            + triPestAppYear + " text, "
            + triPestAppName + " text, "
            + triHomeTrtdInsctInd + " text, "
            + triHomeTrtdLoc + " text, "
            + triHomeTrtdEntity + " text, "
            + triHomeTrtdNames + " text, "
            + triTrtmntAppDay + " text, "
            + triTrtmntAppMonth + " text, "
            + triTrtmntAppYear + " text, "
            + triLwnTrtmntAppInd + " text, "
            + triLwnTrtmntAppDay + " text, "
            + triLwnTrtmntAppMonth + " text, "
            + triLwnTrtmntAppYear + " text, "
            + triLwnTrtmntAppName + " text, "
            + triMosqRepInd + " text, "
            + triMosqRepTyp + " text, "
            + triMosqRepNameSpray + " text, "
            + triMosqRepDkSpray + " text, "
            + triMosqRepNameLotion + " text, "
            + triMosqRepDkLotion + " text, "
            + triMosqRepNameSpiral + " text, "
            + triMosqRepDkSpiral + " text, "
            + triMosqRepNamePlugin + " text, "
            + triMosqRepDkPlugin + " text, "
            + triMosqRepNameOther + " text, "
            + triMosqRepDkOther + " text, "
            + triCompId + " text, "
            + triCompDat + " date, "
            + triRevId + " text, "
            + triRevDat + " date, "
            + triEntId + " text, "
            + triEntDat + " date, "
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
