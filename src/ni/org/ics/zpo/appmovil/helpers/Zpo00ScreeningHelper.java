package ni.org.ics.zpo.appmovil.helpers;

import android.content.ContentValues;
import android.database.Cursor;
import ni.org.ics.zpo.domain.Zpo00Screening;
import ni.org.ics.zpo.appmovil.utils.MainDBConstants;

import java.util.Date;

public class Zpo00ScreeningHelper {
	
	public static ContentValues crearZpo00ScreeningValues(Zpo00Screening screening){
		ContentValues cv = new ContentValues();
		cv.put(MainDBConstants.recordId, screening.getRecordId());
		cv.put(MainDBConstants.scrCs, screening.getScrCs());
		cv.put(MainDBConstants.eventName, screening.getEventName());
		if (screening.getScrVisitDate() != null) cv.put(MainDBConstants.scrVisitDate, screening.getScrVisitDate().getTime());
		cv.put(MainDBConstants.scrConsentObta, screening.getScrConsentObta());
		cv.put(MainDBConstants.scrObDobDay, screening.getScrObDobDay());
		cv.put(MainDBConstants.scrObDobMon, screening.getScrObDobMon());
		cv.put(MainDBConstants.scrObDobYear, screening.getScrObDobYear());
        cv.put(MainDBConstants.scrObAge, screening.getScrObAge());
		cv.put(MainDBConstants.scrObAssent, screening.getScrObAssent());
		cv.put(MainDBConstants.scrConsentA, screening.getScrConsentA());
		cv.put(MainDBConstants.scrConsentB, screening.getScrConsentB());
		cv.put(MainDBConstants.scrConsentC, screening.getScrConsentC());
		cv.put(MainDBConstants.scrName1Tutor, screening.getScrName1Tutor());
		cv.put(MainDBConstants.scrName2Tutor, screening.getScrName2Tutor());
		cv.put(MainDBConstants.scrLastName1Tutor, screening.getScrLastName1Tutor());
		cv.put(MainDBConstants.scrLastName2Tutor, screening.getScrLastName2Tutor());
		cv.put(MainDBConstants.scrFamilyRelationship, screening.getScrFamilyRelationship());
		cv.put(MainDBConstants.scrFamilyRelOther, screening.getScrFamilyRelOther());
		cv.put(MainDBConstants.scrIlliterate, screening.getScrIlliterate());
        cv.put(MainDBConstants.scrName1Witness, screening.getScrName1Witness());
        cv.put(MainDBConstants.scrName2Witness, screening.getScrName2Witness());
        cv.put(MainDBConstants.scrLastName1Witness, screening.getScrLastName1Witness());
        cv.put(MainDBConstants.scrLastName2Witness, screening.getScrLastName2Witness());
		cv.put(MainDBConstants.scrReasonNot, screening.getScrReasonNot());
		cv.put(MainDBConstants.scrReasonOther, screening.getScrReasonOther());

        if (screening.getRecordDate() != null) cv.put(MainDBConstants.recordDate, screening.getRecordDate().getTime());
		cv.put(MainDBConstants.recordUser, screening.getRecordUser());
		cv.put(MainDBConstants.pasive, String.valueOf(screening.getPasive()));
		cv.put(MainDBConstants.ID_INSTANCIA, screening.getIdInstancia());
		cv.put(MainDBConstants.FILE_PATH, screening.getInstancePath());
		cv.put(MainDBConstants.STATUS, screening.getEstado());
		cv.put(MainDBConstants.START, screening.getStart());
		cv.put(MainDBConstants.END, screening.getEnd());
		cv.put(MainDBConstants.DEVICE_ID, screening.getDeviceid());
		cv.put(MainDBConstants.SIM_SERIAL, screening.getSimserial());
		cv.put(MainDBConstants.PHONE_NUMBER, screening.getPhonenumber());
		if (screening.getToday() != null) cv.put(MainDBConstants.TODAY, screening.getToday().getTime());
		return cv; 
	}	
	
	public static Zpo00Screening crearZpo00Screening(Cursor cursorScreening){
		Zpo00Screening mScreening = new Zpo00Screening();
		mScreening.setRecordId(cursorScreening.getString(cursorScreening.getColumnIndex(MainDBConstants.recordId)));
		mScreening.setScrCs(cursorScreening.getString(cursorScreening.getColumnIndex(MainDBConstants.scrCs)));
		mScreening.setEventName(cursorScreening.getString(cursorScreening.getColumnIndex(MainDBConstants.eventName)));
		if(cursorScreening.getLong(cursorScreening.getColumnIndex(MainDBConstants.scrVisitDate))>0) mScreening.setScrVisitDate(new Date(cursorScreening.getLong(cursorScreening.getColumnIndex(MainDBConstants.scrVisitDate))));
		mScreening.setScrConsentObta(cursorScreening.getString(cursorScreening.getColumnIndex(MainDBConstants.scrConsentObta)));
		mScreening.setScrObDobDay(cursorScreening.getString(cursorScreening.getColumnIndex(MainDBConstants.scrObDobDay)));
		mScreening.setScrObDobMon(cursorScreening.getString(cursorScreening.getColumnIndex(MainDBConstants.scrObDobMon)));
		mScreening.setScrObDobYear(cursorScreening.getInt(cursorScreening.getColumnIndex(MainDBConstants.scrObDobYear)));
		if (cursorScreening.getInt(cursorScreening.getColumnIndex(MainDBConstants.scrObAge))>0) mScreening.setScrObAge(cursorScreening.getInt(cursorScreening.getColumnIndex(MainDBConstants.scrObAge)));
		mScreening.setScrObAssent(cursorScreening.getString(cursorScreening.getColumnIndex(MainDBConstants.scrObAssent)));
		mScreening.setScrConsentA(cursorScreening.getString(cursorScreening.getColumnIndex(MainDBConstants.scrConsentA)));
		mScreening.setScrConsentB(cursorScreening.getString(cursorScreening.getColumnIndex(MainDBConstants.scrConsentB)));
		mScreening.setScrConsentC(cursorScreening.getString(cursorScreening.getColumnIndex(MainDBConstants.scrConsentC)));
		mScreening.setScrName1Tutor(cursorScreening.getString(cursorScreening.getColumnIndex(MainDBConstants.scrName1Tutor)));
		mScreening.setScrName2Tutor(cursorScreening.getString(cursorScreening.getColumnIndex(MainDBConstants.scrName2Tutor)));
		mScreening.setScrLastName1Tutor(cursorScreening.getString(cursorScreening.getColumnIndex(MainDBConstants.scrLastName1Tutor)));
		mScreening.setScrLastName2Tutor(cursorScreening.getString(cursorScreening.getColumnIndex(MainDBConstants.scrLastName2Tutor)));
		mScreening.setScrFamilyRelationship(cursorScreening.getString(cursorScreening.getColumnIndex(MainDBConstants.scrFamilyRelationship)));
		mScreening.setScrFamilyRelOther(cursorScreening.getString(cursorScreening.getColumnIndex(MainDBConstants.scrFamilyRelOther)));
		mScreening.setScrIlliterate(cursorScreening.getString(cursorScreening.getColumnIndex(MainDBConstants.scrIlliterate)));
        mScreening.setScrName1Witness(cursorScreening.getString(cursorScreening.getColumnIndex(MainDBConstants.scrName1Witness)));
        mScreening.setScrName2Witness(cursorScreening.getString(cursorScreening.getColumnIndex(MainDBConstants.scrName2Witness)));
        mScreening.setScrLastName1Witness(cursorScreening.getString(cursorScreening.getColumnIndex(MainDBConstants.scrLastName1Witness)));
        mScreening.setScrLastName2Witness(cursorScreening.getString(cursorScreening.getColumnIndex(MainDBConstants.scrLastName2Witness)));

        mScreening.setScrReasonNot(cursorScreening.getString(cursorScreening.getColumnIndex(MainDBConstants.scrReasonNot)));
		mScreening.setScrReasonOther(cursorScreening.getString(cursorScreening.getColumnIndex(MainDBConstants.scrReasonOther)));

        if(cursorScreening.getLong(cursorScreening.getColumnIndex(MainDBConstants.recordDate))>0) mScreening.setRecordDate(new Date(cursorScreening.getLong(cursorScreening.getColumnIndex(MainDBConstants.recordDate))));
		mScreening.setRecordUser(cursorScreening.getString(cursorScreening.getColumnIndex(MainDBConstants.recordUser)));
		mScreening.setPasive(cursorScreening.getString(cursorScreening.getColumnIndex(MainDBConstants.pasive)).charAt(0));
		mScreening.setIdInstancia(cursorScreening.getInt(cursorScreening.getColumnIndex(MainDBConstants.ID_INSTANCIA)));
		mScreening.setInstancePath(cursorScreening.getString(cursorScreening.getColumnIndex(MainDBConstants.FILE_PATH)));
		mScreening.setEstado(cursorScreening.getString(cursorScreening.getColumnIndex(MainDBConstants.STATUS)));
		mScreening.setStart(cursorScreening.getString(cursorScreening.getColumnIndex(MainDBConstants.START)));
		mScreening.setEnd(cursorScreening.getString(cursorScreening.getColumnIndex(MainDBConstants.END)));
		mScreening.setSimserial(cursorScreening.getString(cursorScreening.getColumnIndex(MainDBConstants.SIM_SERIAL)));
		mScreening.setPhonenumber(cursorScreening.getString(cursorScreening.getColumnIndex(MainDBConstants.PHONE_NUMBER)));
		mScreening.setDeviceid(cursorScreening.getString(cursorScreening.getColumnIndex(MainDBConstants.DEVICE_ID)));
		if(cursorScreening.getLong(cursorScreening.getColumnIndex(MainDBConstants.TODAY))>0) mScreening.setToday(new Date(cursorScreening.getLong(cursorScreening.getColumnIndex(MainDBConstants.TODAY))));
		return mScreening;
	}
}
