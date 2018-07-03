package ni.org.ics.zpo.appmovil.helpers;

import android.content.ContentValues;
import android.database.Cursor;
import ni.org.ics.zpo.appmovil.utils.MainDBConstants;
import ni.org.ics.zpo.appmovil.utils.Zpo07OtoEDBConstants;
import ni.org.ics.zpo.domain.Zpo07InfantOtoacousticEmissions;

import java.util.Date;

/**
 * Created by ics.
 * V1.0
 */
public class Zpo07InfantOtoacousticEmissionsHelper {

    public static ContentValues crearZpo07InfantOtoacousticEmissions(Zpo07InfantOtoacousticEmissions zpo07InfantOtoEms){
        ContentValues cv = new ContentValues();
        cv.put(Zpo07OtoEDBConstants.recordId, zpo07InfantOtoEms.getRecordId());
        cv.put(Zpo07OtoEDBConstants.eventName, zpo07InfantOtoEms.getEventName());
        if (zpo07InfantOtoEms.getInfantVisitDate()!=null) cv.put(Zpo07OtoEDBConstants.infantVisitDate, zpo07InfantOtoEms.getInfantVisitDate().getTime());
        cv.put(Zpo07OtoEDBConstants.infantStatus, zpo07InfantOtoEms.getInfantStatus());
        cv.put(Zpo07OtoEDBConstants.infantVisit, zpo07InfantOtoEms.getInfantVisit());
        if (zpo07InfantOtoEms.getInfantDeathDt()!=null) cv.put(Zpo07OtoEDBConstants.infantDeathDt, zpo07InfantOtoEms.getInfantDeathDt().getTime());
        cv.put(Zpo07OtoEDBConstants.infantOae, zpo07InfantOtoEms.getInfantOae());
        cv.put(Zpo07OtoEDBConstants.infantOphthType, zpo07InfantOtoEms.getInfantOphthType());
        cv.put(Zpo07OtoEDBConstants.infantHearingOverall, zpo07InfantOtoEms.getInfantHearingOverall());
        cv.put(Zpo07OtoEDBConstants.infantRoae, zpo07InfantOtoEms.getInfantRoae());
        cv.put(Zpo07OtoEDBConstants.infantRaabr, zpo07InfantOtoEms.getInfantRaabr());
        cv.put(Zpo07OtoEDBConstants.infantLoae, zpo07InfantOtoEms.getInfantLoae());
        cv.put(Zpo07OtoEDBConstants.infantLaabr, zpo07InfantOtoEms.getInfantLaabr());
        cv.put(Zpo07OtoEDBConstants.infantComments2, zpo07InfantOtoEms.getInfantComments2());
        cv.put(Zpo07OtoEDBConstants.infantIdCompleting, zpo07InfantOtoEms.getInfantIdCompleting());
        if (zpo07InfantOtoEms.getInfantDtComp()!=null) cv.put(Zpo07OtoEDBConstants.infantDtComp, zpo07InfantOtoEms.getInfantDtComp().getTime());
        cv.put(Zpo07OtoEDBConstants.infantIdReviewer, zpo07InfantOtoEms.getInfantIdReviewer());
        if (zpo07InfantOtoEms.getInfantDtReview()!=null) cv.put(Zpo07OtoEDBConstants.infantDtReview, zpo07InfantOtoEms.getInfantDtReview().getTime());
        cv.put(Zpo07OtoEDBConstants.infantIdDataEntry, zpo07InfantOtoEms.getInfantIdDataEntry());
        if (zpo07InfantOtoEms.getInfantDtEnter()!=null) cv.put(Zpo07OtoEDBConstants.infantDtEnter, zpo07InfantOtoEms.getInfantDtEnter().getTime());
        if (zpo07InfantOtoEms.getRecordDate() != null) cv.put(MainDBConstants.recordDate, zpo07InfantOtoEms.getRecordDate().getTime());
        cv.put(MainDBConstants.recordUser, zpo07InfantOtoEms.getRecordUser());
        cv.put(MainDBConstants.pasive, String.valueOf(zpo07InfantOtoEms.getPasive()));
        cv.put(MainDBConstants.ID_INSTANCIA, zpo07InfantOtoEms.getIdInstancia());
        cv.put(MainDBConstants.FILE_PATH, zpo07InfantOtoEms.getInstancePath());
        cv.put(MainDBConstants.STATUS, zpo07InfantOtoEms.getEstado());
        cv.put(MainDBConstants.START, zpo07InfantOtoEms.getStart());
        cv.put(MainDBConstants.END, zpo07InfantOtoEms.getEnd());
        cv.put(MainDBConstants.DEVICE_ID, zpo07InfantOtoEms.getDeviceid());
        cv.put(MainDBConstants.SIM_SERIAL, zpo07InfantOtoEms.getSimserial());
        cv.put(MainDBConstants.PHONE_NUMBER, zpo07InfantOtoEms.getPhonenumber());
        if (zpo07InfantOtoEms.getToday() != null) cv.put(MainDBConstants.TODAY, zpo07InfantOtoEms.getToday().getTime());
        return cv;
    }

    public static Zpo07InfantOtoacousticEmissions crearZpo07InfantOtoacousticEmissions(Cursor cursorIA){
        Zpo07InfantOtoacousticEmissions infantOtoE = new Zpo07InfantOtoacousticEmissions();
        infantOtoE.setRecordId(cursorIA.getString(cursorIA.getColumnIndex(Zpo07OtoEDBConstants.recordId)));
        infantOtoE.setEventName(cursorIA.getString(cursorIA.getColumnIndex(Zpo07OtoEDBConstants.eventName)));
        if (cursorIA.getLong(cursorIA.getColumnIndex(Zpo07OtoEDBConstants.infantVisitDate))>0) infantOtoE.setInfantVisitDate(new Date(cursorIA.getLong(cursorIA.getColumnIndex(Zpo07OtoEDBConstants.infantVisitDate))));
        infantOtoE.setInfantStatus(cursorIA.getString(cursorIA.getColumnIndex(Zpo07OtoEDBConstants.infantStatus)));
        infantOtoE.setInfantVisit(cursorIA.getString(cursorIA.getColumnIndex(Zpo07OtoEDBConstants.infantVisit)));
        if (cursorIA.getLong(cursorIA.getColumnIndex(Zpo07OtoEDBConstants.infantDeathDt))>0) infantOtoE.setInfantDeathDt(new Date(cursorIA.getLong(cursorIA.getColumnIndex(Zpo07OtoEDBConstants.infantDeathDt))));
        infantOtoE.setInfantOae(cursorIA.getString(cursorIA.getColumnIndex(Zpo07OtoEDBConstants.infantOae)));
        infantOtoE.setInfantOphthType(cursorIA.getString(cursorIA.getColumnIndex(Zpo07OtoEDBConstants.infantOphthType)));
        infantOtoE.setInfantHearingOverall(cursorIA.getString(cursorIA.getColumnIndex(Zpo07OtoEDBConstants.infantHearingOverall)));
        infantOtoE.setInfantRoae(cursorIA.getString(cursorIA.getColumnIndex(Zpo07OtoEDBConstants.infantRoae)));
        infantOtoE.setInfantRaabr(cursorIA.getString(cursorIA.getColumnIndex(Zpo07OtoEDBConstants.infantRaabr)));
        infantOtoE.setInfantLoae(cursorIA.getString(cursorIA.getColumnIndex(Zpo07OtoEDBConstants.infantLoae)));
        infantOtoE.setInfantLaabr(cursorIA.getString(cursorIA.getColumnIndex(Zpo07OtoEDBConstants.infantLaabr)));
        infantOtoE.setInfantComments2(cursorIA.getString(cursorIA.getColumnIndex(Zpo07OtoEDBConstants.infantComments2)));

        infantOtoE.setInfantIdCompleting(cursorIA.getString(cursorIA.getColumnIndex(Zpo07OtoEDBConstants.infantIdCompleting)));
        if (cursorIA.getLong(cursorIA.getColumnIndex(Zpo07OtoEDBConstants.infantDtComp))>0) infantOtoE.setInfantDtComp(new Date(cursorIA.getLong(cursorIA.getColumnIndex(Zpo07OtoEDBConstants.infantDtComp))));
        infantOtoE.setInfantIdReviewer(cursorIA.getString(cursorIA.getColumnIndex(Zpo07OtoEDBConstants.infantIdReviewer)));
        if (cursorIA.getLong(cursorIA.getColumnIndex(Zpo07OtoEDBConstants.infantDtReview))>0) infantOtoE.setInfantDtReview(new Date(cursorIA.getLong(cursorIA.getColumnIndex(Zpo07OtoEDBConstants.infantDtReview))));
        infantOtoE.setInfantIdDataEntry(cursorIA.getString(cursorIA.getColumnIndex(Zpo07OtoEDBConstants.infantIdDataEntry)));
        if (cursorIA.getLong(cursorIA.getColumnIndex(Zpo07OtoEDBConstants.infantDtEnter))>0) infantOtoE.setInfantDtEnter(new Date(cursorIA.getLong(cursorIA.getColumnIndex(Zpo07OtoEDBConstants.infantDtEnter))));

        if(cursorIA.getLong(cursorIA.getColumnIndex(MainDBConstants.recordDate))>0) infantOtoE.setRecordDate(new Date(cursorIA.getLong(cursorIA.getColumnIndex(MainDBConstants.recordDate))));
        infantOtoE.setRecordUser(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.recordUser)));
        infantOtoE.setPasive(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.pasive)).charAt(0));
        infantOtoE.setIdInstancia(cursorIA.getInt(cursorIA.getColumnIndex(MainDBConstants.ID_INSTANCIA)));
        infantOtoE.setInstancePath(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.FILE_PATH)));
        infantOtoE.setEstado(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.STATUS)));
        infantOtoE.setStart(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.START)));
        infantOtoE.setEnd(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.END)));
        infantOtoE.setSimserial(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.SIM_SERIAL)));
        infantOtoE.setPhonenumber(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.PHONE_NUMBER)));
        infantOtoE.setDeviceid(cursorIA.getString(cursorIA.getColumnIndex(MainDBConstants.DEVICE_ID)));
        if(cursorIA.getLong(cursorIA.getColumnIndex(MainDBConstants.TODAY))>0) infantOtoE.setToday(new Date(cursorIA.getLong(cursorIA.getColumnIndex(MainDBConstants.TODAY))));

        return infantOtoE;
    }

}
