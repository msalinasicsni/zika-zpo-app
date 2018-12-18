package ni.org.ics.zpo.appmovil.helpers;

import android.content.ContentValues;
import android.database.Cursor;
import ni.org.ics.zpo.appmovil.utils.MainDBConstants;
import ni.org.ics.zpo.appmovil.utils.Zpo04AFDBConstants;
import ni.org.ics.zpo.domain.Zpo04ExtendedSectionAtoF;

import java.util.Date;

/**
 * Created by ics.
 * V1.0
 */
public class Zpo04AFExtendedHelper {

    /*********************************************************
           PARA OBJETO Zpo04AFExtendedSectionAtoF
     *********************************************************/
    public static ContentValues crearZpo04ExtendedSectionAtoF(Zpo04ExtendedSectionAtoF trimesterVisitSectionAtoF){
        ContentValues cv = new ContentValues();

        cv.put(Zpo04AFDBConstants.recordId, trimesterVisitSectionAtoF.getRecordId());
        cv.put(Zpo04AFDBConstants.eventName, trimesterVisitSectionAtoF.getEventName());
        if (trimesterVisitSectionAtoF.getTriDov()!=null) cv.put(Zpo04AFDBConstants.triDov, trimesterVisitSectionAtoF.getTriDov().getTime());
        cv.put(Zpo04AFDBConstants.triPrimJobInd, trimesterVisitSectionAtoF.getTriPrimJobInd());
        cv.put(Zpo04AFDBConstants.triPrimJobHours, trimesterVisitSectionAtoF.getTriPrimJobHours());
        cv.put(Zpo04AFDBConstants.triHouseSitInd, trimesterVisitSectionAtoF.getTriHouseSitInd());
        cv.put(Zpo04AFDBConstants.triCity, trimesterVisitSectionAtoF.getTriCity());
        cv.put(Zpo04AFDBConstants.triState, trimesterVisitSectionAtoF.getTriState());
        cv.put(Zpo04AFDBConstants.triCountry, trimesterVisitSectionAtoF.getTriCountry());
        cv.put(Zpo04AFDBConstants.triResidRef, trimesterVisitSectionAtoF.getTriResidRef());
        cv.put(Zpo04AFDBConstants.triCurrResidDur, trimesterVisitSectionAtoF.getTriCurrResidDur());
        cv.put(Zpo04AFDBConstants.triCurrResidDurRef, trimesterVisitSectionAtoF.getTriCurrResidDurRef());
        cv.put(Zpo04AFDBConstants.triNbhoodTyp, trimesterVisitSectionAtoF.getTriNbhoodTyp());
        cv.put(Zpo04AFDBConstants.triResidTyp, trimesterVisitSectionAtoF.getTriResidTyp());
        cv.put(Zpo04AFDBConstants.triResidTypSpecify, trimesterVisitSectionAtoF.getTriResidTypSpecify());
        cv.put(Zpo04AFDBConstants.triFloorMat, trimesterVisitSectionAtoF.getTriFloorMat());
        cv.put(Zpo04AFDBConstants.triFloorMatSpecify, trimesterVisitSectionAtoF.getTriFloorMatSpecify());
        cv.put(Zpo04AFDBConstants.triWallMat, trimesterVisitSectionAtoF.getTriWallMat());
        cv.put(Zpo04AFDBConstants.triWallMatSpecify, trimesterVisitSectionAtoF.getTriWallMatSpecify());
        cv.put(Zpo04AFDBConstants.triRoofMat, trimesterVisitSectionAtoF.getTriRoofMat());
        cv.put(Zpo04AFDBConstants.triRoofMatSpecify, trimesterVisitSectionAtoF.getTriRoofMatSpecify());
        cv.put(Zpo04AFDBConstants.triTrashDispos, trimesterVisitSectionAtoF.getTriTrashDispos());
        cv.put(Zpo04AFDBConstants.triTrashDisposSpecify, trimesterVisitSectionAtoF.getTriTrashDisposSpecify());
        cv.put(Zpo04AFDBConstants.triNumTotalRooms, trimesterVisitSectionAtoF.getTriNumTotalRooms());
        cv.put(Zpo04AFDBConstants.triNumSleepRooms, trimesterVisitSectionAtoF.getTriNumSleepRooms());
        cv.put(Zpo04AFDBConstants.triNumPeople, trimesterVisitSectionAtoF.getTriNumPeople());
        cv.put(Zpo04AFDBConstants.triScreensInd, trimesterVisitSectionAtoF.getTriScreensInd());
        cv.put(Zpo04AFDBConstants.triHouseAmenities, trimesterVisitSectionAtoF.getTriHouseAmenities());
        cv.put(Zpo04AFDBConstants.triTransAccess, trimesterVisitSectionAtoF.getTriTransAccess());
        cv.put(Zpo04AFDBConstants.triPrimWaterSrc, trimesterVisitSectionAtoF.getTriPrimWaterSrc());
        cv.put(Zpo04AFDBConstants.triWaterContainInd, trimesterVisitSectionAtoF.getTriWaterContainInd());
        cv.put(Zpo04AFDBConstants.triWaterContainTyp, trimesterVisitSectionAtoF.getTriWaterContainTyp());
        cv.put(Zpo04AFDBConstants.triWaterConSpecify, trimesterVisitSectionAtoF.getTriWaterConSpecify());
        cv.put(Zpo04AFDBConstants.triWaterTreatHome, trimesterVisitSectionAtoF.getTriWaterTreatHome());
        cv.put(Zpo04AFDBConstants.triWaterTreatFreq, trimesterVisitSectionAtoF.getTriWaterTreatFreq());
        cv.put(Zpo04AFDBConstants.triToiletTyp, trimesterVisitSectionAtoF.getTriToiletTyp());
        cv.put(Zpo04AFDBConstants.triOpSewageInd, trimesterVisitSectionAtoF.getTriOpSewageInd());
        cv.put(Zpo04AFDBConstants.triAnimalsInd, trimesterVisitSectionAtoF.getTriAnimalsInd());
        cv.put(Zpo04AFDBConstants.triAnimalTyp, trimesterVisitSectionAtoF.getTriAnimalTyp());
        cv.put(Zpo04AFDBConstants.triAnimalSpecify, trimesterVisitSectionAtoF.getTriAnimalSpecify());
        cv.put(Zpo04AFDBConstants.triNumOtherAnimal, trimesterVisitSectionAtoF.getTriNumOtherAnimal());
        cv.put(Zpo04AFDBConstants.triNumCattle, trimesterVisitSectionAtoF.getTriNumCattle());
        cv.put(Zpo04AFDBConstants.triNumPig, trimesterVisitSectionAtoF.getTriNumPig());
        cv.put(Zpo04AFDBConstants.triNumFowl, trimesterVisitSectionAtoF.getTriNumFowl());
        cv.put(Zpo04AFDBConstants.triNumGoatsSheep, trimesterVisitSectionAtoF.getTriNumGoatsSheep());
        cv.put(Zpo04AFDBConstants.triSmokeInd, trimesterVisitSectionAtoF.getTriSmokeInd());
        cv.put(Zpo04AFDBConstants.triDrinkInd, trimesterVisitSectionAtoF.getTriDrinkInd());
        cv.put(Zpo04AFDBConstants.triDrinkEverInd, trimesterVisitSectionAtoF.getTriDrinkEverInd());
        cv.put(Zpo04AFDBConstants.triBugNuisInd, trimesterVisitSectionAtoF.getTriBugNuisInd());
        cv.put(Zpo04AFDBConstants.triPestStorHomeInd, trimesterVisitSectionAtoF.getTriPestAppHomeInd());
        cv.put(Zpo04AFDBConstants.triPestAppHomeInd, trimesterVisitSectionAtoF.getTriPestAppHomeInd());
        cv.put(Zpo04AFDBConstants.triPestAppDay, trimesterVisitSectionAtoF.getTriPestAppDay());
        cv.put(Zpo04AFDBConstants.triPestAppMonth, trimesterVisitSectionAtoF.getTriPestAppMonth());
        cv.put(Zpo04AFDBConstants.triPestAppYear, trimesterVisitSectionAtoF.getTriPestAppYear());
        cv.put(Zpo04AFDBConstants.triPestAppName, trimesterVisitSectionAtoF.getTriPestAppName());
        cv.put(Zpo04AFDBConstants.triHomeTrtdInsctInd, trimesterVisitSectionAtoF.getTriHomeTrtdInsctInd());
        cv.put(Zpo04AFDBConstants.triHomeTrtdLoc, trimesterVisitSectionAtoF.getTriHomeTrtdLoc());
        cv.put(Zpo04AFDBConstants.triHomeTrtdEntity, trimesterVisitSectionAtoF.getTriHomeTrtdEntity());
        cv.put(Zpo04AFDBConstants.triHomeTrtdNames, trimesterVisitSectionAtoF.getTriHomeTrtdNames());
        cv.put(Zpo04AFDBConstants.triTrtmntAppDay, trimesterVisitSectionAtoF.getTriTrtmntAppDay());
        cv.put(Zpo04AFDBConstants.triTrtmntAppMonth, trimesterVisitSectionAtoF.getTriTrtmntAppMonth());
        cv.put(Zpo04AFDBConstants.triTrtmntAppYear, trimesterVisitSectionAtoF.getTriTrtmntAppYear());
        cv.put(Zpo04AFDBConstants.triLwnTrtmntAppInd, trimesterVisitSectionAtoF.getTriLwnTrtmntAppInd());
        cv.put(Zpo04AFDBConstants.triLwnTrtmntAppDay, trimesterVisitSectionAtoF.getTriLwnTrtmntAppDay());
        cv.put(Zpo04AFDBConstants.triLwnTrtmntAppMonth, trimesterVisitSectionAtoF.getTriLwnTrtmntAppMonth());
        cv.put(Zpo04AFDBConstants.triLwnTrtmntAppYear, trimesterVisitSectionAtoF.getTriLwnTrtmntAppYear());
        cv.put(Zpo04AFDBConstants.triLwnTrtmntAppName, trimesterVisitSectionAtoF.getTriPestAppName());
        cv.put(Zpo04AFDBConstants.triMosqRepInd, trimesterVisitSectionAtoF.getTriMosqRepInd());
        cv.put(Zpo04AFDBConstants.triMosqRepTyp, trimesterVisitSectionAtoF.getTriMosqRepTyp());
        cv.put(Zpo04AFDBConstants.triMosqRepNameSpray, trimesterVisitSectionAtoF.getTriMosqRepNameSpray());
        cv.put(Zpo04AFDBConstants.triMosqRepDkSpray, trimesterVisitSectionAtoF.getTriMosqRepDkSpray());
        cv.put(Zpo04AFDBConstants.triMosqRepNameLotion, trimesterVisitSectionAtoF.getTriMosqRepNameLotion());
        cv.put(Zpo04AFDBConstants.triMosqRepDkLotion, trimesterVisitSectionAtoF.getTriMosqRepDkLotion());
        cv.put(Zpo04AFDBConstants.triMosqRepNameSpiral, trimesterVisitSectionAtoF.getTriMosqRepNameSpiral());
        cv.put(Zpo04AFDBConstants.triMosqRepDkSpiral, trimesterVisitSectionAtoF.getTriMosqRepDkSpiral());
        cv.put(Zpo04AFDBConstants.triMosqRepNamePlugin, trimesterVisitSectionAtoF.getTriMosqRepNamePlugin());
        cv.put(Zpo04AFDBConstants.triMosqRepDkPlugin, trimesterVisitSectionAtoF.getTriMosqRepDkPlugin());
        cv.put(Zpo04AFDBConstants.triMosqRepNameOther, trimesterVisitSectionAtoF.getTriMosqRepNameOther());
        cv.put(Zpo04AFDBConstants.triMosqRepDkOther, trimesterVisitSectionAtoF.getTriMosqRepDkOther());
        cv.put(Zpo04AFDBConstants.triCompId, trimesterVisitSectionAtoF.getTriCompId());
        if (trimesterVisitSectionAtoF.getTriCompDat()!=null) cv.put(Zpo04AFDBConstants.triCompDat, trimesterVisitSectionAtoF.getTriCompDat().getTime());
        cv.put(Zpo04AFDBConstants.triRevId, trimesterVisitSectionAtoF.getTriRevId());
        if (trimesterVisitSectionAtoF.getTriRevDat()!=null) cv.put(Zpo04AFDBConstants.triRevDat, trimesterVisitSectionAtoF.getTriRevDat().getTime());
        cv.put(Zpo04AFDBConstants.triEntId, trimesterVisitSectionAtoF.getTriEntId());
        if (trimesterVisitSectionAtoF.getTriEntDat()!=null) cv.put(Zpo04AFDBConstants.triEntDat, trimesterVisitSectionAtoF.getTriEntDat().getTime());

        if (trimesterVisitSectionAtoF.getRecordDate() != null) cv.put(MainDBConstants.recordDate, trimesterVisitSectionAtoF.getRecordDate().getTime());
        cv.put(MainDBConstants.recordUser, trimesterVisitSectionAtoF.getRecordUser());
        cv.put(MainDBConstants.pasive, String.valueOf(trimesterVisitSectionAtoF.getPasive()));
        cv.put(MainDBConstants.ID_INSTANCIA, trimesterVisitSectionAtoF.getIdInstancia());
        cv.put(MainDBConstants.FILE_PATH, trimesterVisitSectionAtoF.getInstancePath());
        cv.put(MainDBConstants.STATUS, trimesterVisitSectionAtoF.getEstado());
        cv.put(MainDBConstants.START, trimesterVisitSectionAtoF.getStart());
        cv.put(MainDBConstants.END, trimesterVisitSectionAtoF.getEnd());
        cv.put(MainDBConstants.DEVICE_ID, trimesterVisitSectionAtoF.getDeviceid());
        cv.put(MainDBConstants.SIM_SERIAL, trimesterVisitSectionAtoF.getSimserial());
        cv.put(MainDBConstants.PHONE_NUMBER, trimesterVisitSectionAtoF.getPhonenumber());
        if (trimesterVisitSectionAtoF.getToday() != null) cv.put(MainDBConstants.TODAY, trimesterVisitSectionAtoF.getToday().getTime());

        return cv;
    }

    public static Zpo04ExtendedSectionAtoF crearZpo04ExtendedSectionAtoF(Cursor cursorAF){
        Zpo04ExtendedSectionAtoF trimesterVisitSectionAtoF = new Zpo04ExtendedSectionAtoF();

        trimesterVisitSectionAtoF.setRecordId(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.recordId)));
        trimesterVisitSectionAtoF.setEventName(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.eventName)));
        if (cursorAF.getLong(cursorAF.getColumnIndex(Zpo04AFDBConstants.triDov))>0) trimesterVisitSectionAtoF.setTriDov(new Date(cursorAF.getLong(cursorAF.getColumnIndex(Zpo04AFDBConstants.triDov))));
        trimesterVisitSectionAtoF.setTriPrimJobInd(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triPrimJobInd)));
        trimesterVisitSectionAtoF.setTriPrimJobHours(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triPrimJobHours)));
        trimesterVisitSectionAtoF.setTriHouseSitInd(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triHouseSitInd)));
        trimesterVisitSectionAtoF.setTriCity(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triCity)));
        trimesterVisitSectionAtoF.setTriState(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triState)));
        trimesterVisitSectionAtoF.setTriCountry(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triCountry)));
        trimesterVisitSectionAtoF.setTriResidRef(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triResidRef)));
        trimesterVisitSectionAtoF.setTriCurrResidDur(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triCurrResidDur)));
        trimesterVisitSectionAtoF.setTriCurrResidDurRef(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triCurrResidDurRef)));
        trimesterVisitSectionAtoF.setTriNbhoodTyp(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triNbhoodTyp)));
        trimesterVisitSectionAtoF.setTriResidTyp(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triResidTyp)));
        trimesterVisitSectionAtoF.setTriResidTypSpecify(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triResidTypSpecify)));
        trimesterVisitSectionAtoF.setTriFloorMat(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triFloorMat)));
        trimesterVisitSectionAtoF.setTriFloorMatSpecify(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triFloorMatSpecify)));
        trimesterVisitSectionAtoF.setTriWallMat(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triWallMat)));
        trimesterVisitSectionAtoF.setTriWallMatSpecify(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triWallMatSpecify)));
        trimesterVisitSectionAtoF.setTriRoofMat(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triRoofMat)));
        trimesterVisitSectionAtoF.setTriRoofMatSpecify(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triRoofMatSpecify)));
        trimesterVisitSectionAtoF.setTriTrashDispos(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triTrashDispos)));
        trimesterVisitSectionAtoF.setTriTrashDisposSpecify(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triTrashDisposSpecify)));
        if (cursorAF.getInt(cursorAF.getColumnIndex(Zpo04AFDBConstants.triNumTotalRooms))>0) trimesterVisitSectionAtoF.setTriNumTotalRooms(cursorAF.getInt(cursorAF.getColumnIndex(Zpo04AFDBConstants.triNumTotalRooms)));
        if (cursorAF.getInt(cursorAF.getColumnIndex(Zpo04AFDBConstants.triNumSleepRooms))>0) trimesterVisitSectionAtoF.setTriNumSleepRooms(cursorAF.getInt(cursorAF.getColumnIndex(Zpo04AFDBConstants.triNumSleepRooms)));
        if (cursorAF.getInt(cursorAF.getColumnIndex(Zpo04AFDBConstants.triNumPeople))>0) trimesterVisitSectionAtoF.setTriNumPeople(cursorAF.getInt(cursorAF.getColumnIndex(Zpo04AFDBConstants.triNumPeople)));
        trimesterVisitSectionAtoF.setTriScreensInd(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triScreensInd)));
        trimesterVisitSectionAtoF.setTriHouseAmenities(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triHouseAmenities)));
        trimesterVisitSectionAtoF.setTriTransAccess(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triTransAccess)));
        trimesterVisitSectionAtoF.setTriPrimWaterSrc(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triPrimWaterSrc)));
        trimesterVisitSectionAtoF.setTriWaterContainInd(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triWaterContainInd)));
        trimesterVisitSectionAtoF.setTriWaterContainTyp(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triWaterContainTyp)));
        trimesterVisitSectionAtoF.setTriWaterConSpecify(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triWaterConSpecify)));
        trimesterVisitSectionAtoF.setTriWaterTreatHome(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triWaterTreatHome)));
        trimesterVisitSectionAtoF.setTriWaterTreatFreq(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triWaterTreatFreq)));
        trimesterVisitSectionAtoF.setTriToiletTyp(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triToiletTyp)));
        trimesterVisitSectionAtoF.setTriOpSewageInd(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triOpSewageInd)));
        trimesterVisitSectionAtoF.setTriAnimalsInd(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triAnimalsInd)));
        trimesterVisitSectionAtoF.setTriAnimalTyp(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triAnimalTyp)));
        trimesterVisitSectionAtoF.setTriAnimalSpecify(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triAnimalSpecify)));
        if(cursorAF.getInt(cursorAF.getColumnIndex(Zpo04AFDBConstants.triNumOtherAnimal))>0) trimesterVisitSectionAtoF.setTriNumOtherAnimal(cursorAF.getInt(cursorAF.getColumnIndex(Zpo04AFDBConstants.triNumOtherAnimal)));
        if(cursorAF.getInt(cursorAF.getColumnIndex(Zpo04AFDBConstants.triNumCattle))>0) trimesterVisitSectionAtoF.setTriNumCattle(cursorAF.getInt(cursorAF.getColumnIndex(Zpo04AFDBConstants.triNumCattle)));
        if(cursorAF.getInt(cursorAF.getColumnIndex(Zpo04AFDBConstants.triNumPig))>0) trimesterVisitSectionAtoF.setTriNumPig(cursorAF.getInt(cursorAF.getColumnIndex(Zpo04AFDBConstants.triNumPig)));
        if(cursorAF.getInt(cursorAF.getColumnIndex(Zpo04AFDBConstants.triNumFowl))>0) trimesterVisitSectionAtoF.setTriNumFowl(cursorAF.getInt(cursorAF.getColumnIndex(Zpo04AFDBConstants.triNumFowl)));
        if(cursorAF.getInt(cursorAF.getColumnIndex(Zpo04AFDBConstants.triNumGoatsSheep))>0) trimesterVisitSectionAtoF.setTriNumGoatsSheep(cursorAF.getInt(cursorAF.getColumnIndex(Zpo04AFDBConstants.triNumGoatsSheep)));
        trimesterVisitSectionAtoF.setTriSmokeInd(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triSmokeInd)));
        trimesterVisitSectionAtoF.setTriDrinkInd(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triDrinkInd)));
        trimesterVisitSectionAtoF.setTriDrinkEverInd(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triDrinkEverInd)));
        trimesterVisitSectionAtoF.setTriBugNuisInd(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triBugNuisInd)));
        trimesterVisitSectionAtoF.setTriPestStorHomeInd(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triPestStorHomeInd)));
        trimesterVisitSectionAtoF.setTriPestAppHomeInd(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triPestAppHomeInd)));
        trimesterVisitSectionAtoF.setTriPestAppDay(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triPestAppDay)));
        trimesterVisitSectionAtoF.setTriPestAppMonth(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triPestAppMonth)));
        trimesterVisitSectionAtoF.setTriPestAppYear(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triPestAppYear)));
        trimesterVisitSectionAtoF.setTriPestAppName(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triPestAppName)));
        trimesterVisitSectionAtoF.setTriHomeTrtdInsctInd(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triHomeTrtdInsctInd)));
        trimesterVisitSectionAtoF.setTriHomeTrtdLoc(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triHomeTrtdLoc)));
        trimesterVisitSectionAtoF.setTriHomeTrtdEntity(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triHomeTrtdEntity)));
        trimesterVisitSectionAtoF.setTriHomeTrtdNames(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triHomeTrtdNames)));
        trimesterVisitSectionAtoF.setTriTrtmntAppDay(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triTrtmntAppDay)));
        trimesterVisitSectionAtoF.setTriTrtmntAppMonth(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triTrtmntAppMonth)));
        trimesterVisitSectionAtoF.setTriTrtmntAppYear(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triTrtmntAppYear)));
        trimesterVisitSectionAtoF.setTriLwnTrtmntAppInd(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triLwnTrtmntAppInd)));
        trimesterVisitSectionAtoF.setTriLwnTrtmntAppDay(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triLwnTrtmntAppDay)));
        trimesterVisitSectionAtoF.setTriLwnTrtmntAppMonth(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triLwnTrtmntAppMonth)));
        trimesterVisitSectionAtoF.setTriLwnTrtmntAppYear(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triLwnTrtmntAppYear)));
        trimesterVisitSectionAtoF.setTriLwnTrtmntAppName(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triLwnTrtmntAppName)));
        trimesterVisitSectionAtoF.setTriMosqRepInd(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triMosqRepInd)));
        trimesterVisitSectionAtoF.setTriMosqRepTyp(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triMosqRepTyp)));
        trimesterVisitSectionAtoF.setTriMosqRepNameSpray(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triMosqRepNameSpray)));
        trimesterVisitSectionAtoF.setTriMosqRepDkSpray(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triMosqRepDkSpray)));
        trimesterVisitSectionAtoF.setTriMosqRepNameLotion(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triMosqRepNameLotion)));
        trimesterVisitSectionAtoF.setTriMosqRepDkLotion(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triMosqRepDkLotion)));
        trimesterVisitSectionAtoF.setTriMosqRepNameSpiral(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triMosqRepNameSpiral)));
        trimesterVisitSectionAtoF.setTriMosqRepDkSpiral(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triMosqRepDkSpiral)));
        trimesterVisitSectionAtoF.setTriMosqRepNamePlugin(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triMosqRepNamePlugin)));
        trimesterVisitSectionAtoF.setTriMosqRepDkPlugin(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triMosqRepDkPlugin)));
        trimesterVisitSectionAtoF.setTriMosqRepNameOther(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triMosqRepNameOther)));
        trimesterVisitSectionAtoF.setTriMosqRepDkOther(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triMosqRepDkOther)));
        trimesterVisitSectionAtoF.setTriCompId(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triCompId)));
        if (cursorAF.getLong(cursorAF.getColumnIndex(Zpo04AFDBConstants.triCompDat))>0) trimesterVisitSectionAtoF.setTriCompDat(new Date(cursorAF.getLong(cursorAF.getColumnIndex(Zpo04AFDBConstants.triCompDat))));
        trimesterVisitSectionAtoF.setTriRevId(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triRevId)));
        if (cursorAF.getLong(cursorAF.getColumnIndex(Zpo04AFDBConstants.triRevDat))>0) trimesterVisitSectionAtoF.setTriRevDat(new Date(cursorAF.getLong(cursorAF.getColumnIndex(Zpo04AFDBConstants.triRevDat))));
        trimesterVisitSectionAtoF.setTriEntId(cursorAF.getString(cursorAF.getColumnIndex(Zpo04AFDBConstants.triEntId)));
        if (cursorAF.getLong(cursorAF.getColumnIndex(Zpo04AFDBConstants.triEntDat))>0) trimesterVisitSectionAtoF.setTriEntDat(new Date(cursorAF.getLong(cursorAF.getColumnIndex(Zpo04AFDBConstants.triEntDat))));

        //Movil y Metadata
        if(cursorAF.getLong(cursorAF.getColumnIndex(MainDBConstants.recordDate))>0) trimesterVisitSectionAtoF.setRecordDate(new Date(cursorAF.getLong(cursorAF.getColumnIndex(MainDBConstants.recordDate))));
        trimesterVisitSectionAtoF.setRecordUser(cursorAF.getString(cursorAF.getColumnIndex(MainDBConstants.recordUser)));
        trimesterVisitSectionAtoF.setPasive(cursorAF.getString(cursorAF.getColumnIndex(MainDBConstants.pasive)).charAt(0));
        trimesterVisitSectionAtoF.setIdInstancia(cursorAF.getInt(cursorAF.getColumnIndex(MainDBConstants.ID_INSTANCIA)));
        trimesterVisitSectionAtoF.setInstancePath(cursorAF.getString(cursorAF.getColumnIndex(MainDBConstants.FILE_PATH)));
        trimesterVisitSectionAtoF.setEstado(cursorAF.getString(cursorAF.getColumnIndex(MainDBConstants.STATUS)));
        trimesterVisitSectionAtoF.setStart(cursorAF.getString(cursorAF.getColumnIndex(MainDBConstants.START)));
        trimesterVisitSectionAtoF.setEnd(cursorAF.getString(cursorAF.getColumnIndex(MainDBConstants.END)));
        trimesterVisitSectionAtoF.setSimserial(cursorAF.getString(cursorAF.getColumnIndex(MainDBConstants.SIM_SERIAL)));
        trimesterVisitSectionAtoF.setPhonenumber(cursorAF.getString(cursorAF.getColumnIndex(MainDBConstants.PHONE_NUMBER)));
        trimesterVisitSectionAtoF.setDeviceid(cursorAF.getString(cursorAF.getColumnIndex(MainDBConstants.DEVICE_ID)));
        if(cursorAF.getLong(cursorAF.getColumnIndex(MainDBConstants.TODAY))>0) trimesterVisitSectionAtoF.setToday(new Date(cursorAF.getLong(cursorAF.getColumnIndex(MainDBConstants.TODAY))));

        return trimesterVisitSectionAtoF;
    }


}
