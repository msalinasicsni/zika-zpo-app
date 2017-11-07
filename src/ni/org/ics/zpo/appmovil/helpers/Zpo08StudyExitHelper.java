package ni.org.ics.zpo.appmovil.helpers;

import android.content.ContentValues;
import android.database.Cursor;
import ni.org.ics.zpo.domain.Zpo08StudyExit;
import ni.org.ics.zpo.appmovil.utils.MainDBConstants;
import ni.org.ics.zpo.appmovil.utils.Zpo08DBConstants;

import java.util.Date;

/**
 * Created by FIRSTICT on 6/11/2017.
 * V1.0
 */
public class Zpo08StudyExitHelper {

    public static ContentValues crearZpo08StudyExit(Zpo08StudyExit studyExit){
        ContentValues cv = new ContentValues();

        cv.put(Zpo08DBConstants.recordId, studyExit.getRecordId());
        cv.put(Zpo08DBConstants.eventName, studyExit.getEventName());
        if (studyExit.getExtStudyExitDate()!=null) cv.put(Zpo08DBConstants.extStudyExitDate, studyExit.getExtStudyExitDate().getTime());
        cv.put(Zpo08DBConstants.extSubjClass, studyExit.getExtSubjClass());
        cv.put(Zpo08DBConstants.extStudyExitReason, studyExit.getExtStudyExitReason());
        cv.put(Zpo08DBConstants.extNonpregMatrnlDth, studyExit.getExtNonpregMatrnlDth());
        cv.put(Zpo08DBConstants.extAcuteHealthSpec, studyExit.getExtAcuteHealthSpec());
        cv.put(Zpo08DBConstants.extHealthCondSpec, studyExit.getExtHealthCondSpec());
        cv.put(Zpo08DBConstants.extFatalInjSpec, studyExit.getExtFatalInjSpec());
        cv.put(Zpo08DBConstants.extInfDeathTime, studyExit.getExtInfDeathTime());
        cv.put(Zpo08DBConstants.extTestResultsRcvd, studyExit.getExtTestResultsRcvd());
        cv.put(Zpo08DBConstants.extCounselingRcvd, studyExit.getExtCounselingRcvd());
        cv.put(Zpo08DBConstants.extComments, studyExit.getExtComments());
        cv.put(Zpo08DBConstants.extIdCompleting, studyExit.getExtIdCompleting());
        if (studyExit.getExtDateCompleted()!=null) cv.put(Zpo08DBConstants.extDateCompleted, studyExit.getExtDateCompleted().getTime());
        cv.put(Zpo08DBConstants.extIdReviewer, studyExit.getExtIdReviewer());
        if (studyExit.getExtDateReviewed()!=null) cv.put(Zpo08DBConstants.extDateReviewed, studyExit.getExtDateReviewed().getTime());
        cv.put(Zpo08DBConstants.extIdDataEntry, studyExit.getExtIdDataEntry());
        if (studyExit.getExtDateEntered()!=null) cv.put(Zpo08DBConstants.extDateEntered, studyExit.getExtDateEntered().getTime());

        if (studyExit.getRecordDate() != null) cv.put(MainDBConstants.recordDate, studyExit.getRecordDate().getTime());
        cv.put(MainDBConstants.recordUser, studyExit.getRecordUser());
        cv.put(MainDBConstants.pasive, String.valueOf(studyExit.getPasive()));
        cv.put(MainDBConstants.ID_INSTANCIA, studyExit.getIdInstancia());
        cv.put(MainDBConstants.FILE_PATH, studyExit.getInstancePath());
        cv.put(MainDBConstants.STATUS, studyExit.getEstado());
        cv.put(MainDBConstants.START, studyExit.getStart());
        cv.put(MainDBConstants.END, studyExit.getEnd());
        cv.put(MainDBConstants.DEVICE_ID, studyExit.getDeviceid());
        cv.put(MainDBConstants.SIM_SERIAL, studyExit.getSimserial());
        cv.put(MainDBConstants.PHONE_NUMBER, studyExit.getPhonenumber());
        if (studyExit.getToday() != null) cv.put(MainDBConstants.TODAY, studyExit.getToday().getTime());

        return cv;
    }

    public static Zpo08StudyExit crearZpo08StudyExit(Cursor cursor){
        Zpo08StudyExit studyExit = new Zpo08StudyExit();

        studyExit.setRecordId(cursor.getString(cursor.getColumnIndex(Zpo08DBConstants.recordId)));
        studyExit.setEventName(cursor.getString(cursor.getColumnIndex(Zpo08DBConstants.eventName)));
        if (cursor.getLong(cursor.getColumnIndex(Zpo08DBConstants.extStudyExitDate))>0) studyExit.setExtStudyExitDate(new Date(cursor.getLong(cursor.getColumnIndex(Zpo08DBConstants.extStudyExitDate))));
        studyExit.setExtSubjClass(cursor.getString(cursor.getColumnIndex(Zpo08DBConstants.extSubjClass)));
        studyExit.setExtStudyExitReason(cursor.getString(cursor.getColumnIndex(Zpo08DBConstants.extStudyExitReason)));
        studyExit.setExtNonpregMatrnlDth(cursor.getString(cursor.getColumnIndex(Zpo08DBConstants.extNonpregMatrnlDth)));
        studyExit.setExtAcuteHealthSpec(cursor.getString(cursor.getColumnIndex(Zpo08DBConstants.extAcuteHealthSpec)));
        studyExit.setExtHealthCondSpec(cursor.getString(cursor.getColumnIndex(Zpo08DBConstants.extHealthCondSpec)));
        studyExit.setExtFatalInjSpec(cursor.getString(cursor.getColumnIndex(Zpo08DBConstants.extFatalInjSpec)));
        studyExit.setExtInfDeathTime(cursor.getString(cursor.getColumnIndex(Zpo08DBConstants.extInfDeathTime)));
        studyExit.setExtTestResultsRcvd(cursor.getString(cursor.getColumnIndex(Zpo08DBConstants.extTestResultsRcvd)));
        studyExit.setExtCounselingRcvd(cursor.getString(cursor.getColumnIndex(Zpo08DBConstants.extCounselingRcvd)));
        studyExit.setExtComments(cursor.getString(cursor.getColumnIndex(Zpo08DBConstants.extComments)));
        studyExit.setExtIdCompleting(cursor.getString(cursor.getColumnIndex(Zpo08DBConstants.extIdCompleting)));
        if (cursor.getLong(cursor.getColumnIndex(Zpo08DBConstants.extDateCompleted))>0) studyExit.setExtDateCompleted(new Date(cursor.getLong(cursor.getColumnIndex(Zpo08DBConstants.extDateCompleted))));
        studyExit.setExtIdReviewer(cursor.getString(cursor.getColumnIndex(Zpo08DBConstants.extIdReviewer)));
        if (cursor.getLong(cursor.getColumnIndex(Zpo08DBConstants.extDateReviewed))>0) studyExit.setExtDateReviewed(new Date(cursor.getLong(cursor.getColumnIndex(Zpo08DBConstants.extDateReviewed))));
        studyExit.setExtIdDataEntry(cursor.getString(cursor.getColumnIndex(Zpo08DBConstants.extIdDataEntry)));
        if (cursor.getLong(cursor.getColumnIndex(Zpo08DBConstants.extDateEntered))>0) studyExit.setExtDateEntered(new Date(cursor.getLong(cursor.getColumnIndex(Zpo08DBConstants.extDateEntered))));

        if(cursor.getLong(cursor.getColumnIndex(MainDBConstants.recordDate))>0) studyExit.setRecordDate(new Date(cursor.getLong(cursor.getColumnIndex(MainDBConstants.recordDate))));
        studyExit.setRecordUser(cursor.getString(cursor.getColumnIndex(MainDBConstants.recordUser)));
        studyExit.setPasive(cursor.getString(cursor.getColumnIndex(MainDBConstants.pasive)).charAt(0));
        studyExit.setIdInstancia(cursor.getInt(cursor.getColumnIndex(MainDBConstants.ID_INSTANCIA)));
        studyExit.setInstancePath(cursor.getString(cursor.getColumnIndex(MainDBConstants.FILE_PATH)));
        studyExit.setEstado(cursor.getString(cursor.getColumnIndex(MainDBConstants.STATUS)));
        studyExit.setStart(cursor.getString(cursor.getColumnIndex(MainDBConstants.START)));
        studyExit.setEnd(cursor.getString(cursor.getColumnIndex(MainDBConstants.END)));
        studyExit.setSimserial(cursor.getString(cursor.getColumnIndex(MainDBConstants.SIM_SERIAL)));
        studyExit.setPhonenumber(cursor.getString(cursor.getColumnIndex(MainDBConstants.PHONE_NUMBER)));
        studyExit.setDeviceid(cursor.getString(cursor.getColumnIndex(MainDBConstants.DEVICE_ID)));
        if(cursor.getLong(cursor.getColumnIndex(MainDBConstants.TODAY))>0) studyExit.setToday(new Date(cursor.getLong(cursor.getColumnIndex(MainDBConstants.TODAY))));

        return studyExit;
    }
}
