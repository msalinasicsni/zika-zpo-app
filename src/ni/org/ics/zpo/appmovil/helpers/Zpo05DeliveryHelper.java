package ni.org.ics.zpo.appmovil.helpers;

import android.content.ContentValues;
import android.database.Cursor;
import ni.org.ics.zpo.domain.Zpo05Delivery;
import ni.org.ics.zpo.appmovil.utils.MainDBConstants;
import ni.org.ics.zpo.appmovil.utils.Zpo05DBConstants;

import java.util.Date;

/**
 * Created by FIRSTICT on 10/25/2016.
 * V1.0
 */
public class Zpo05DeliveryHelper {

    public static ContentValues crearZpo05Delivery(Zpo05Delivery zpo05Delivery){
        ContentValues cv = new ContentValues();

        cv.put(Zpo05DBConstants.recordId, zpo05Delivery.getRecordId());
        cv.put(Zpo05DBConstants.eventName, zpo05Delivery.getEventName());
        if (zpo05Delivery.getDeliVisitDate()!=null) cv.put(Zpo05DBConstants.deliVisitDate, zpo05Delivery.getDeliVisitDate().getTime());
        cv.put(Zpo05DBConstants.deliOriginInfo, zpo05Delivery.getDeliOriginInfo());
        cv.put(Zpo05DBConstants.deliMotherWt, zpo05Delivery.getDeliMotherWt());
        cv.put(Zpo05DBConstants.deliWtUnit, zpo05Delivery.getDeliWtUnit());
        cv.put(Zpo05DBConstants.deliSystolic, zpo05Delivery.getDeliSystolic());
        cv.put(Zpo05DBConstants.deliDiastolic, zpo05Delivery.getDeliDiastolic());
        cv.put(Zpo05DBConstants.deliTemperature, zpo05Delivery.getDeliTemperature());
        cv.put(Zpo05DBConstants.deliTempUnit, zpo05Delivery.getDeliTempUnit());
        if (zpo05Delivery.getDeliDeliveryDate()!=null) cv.put(Zpo05DBConstants.deliDeliveryDate, zpo05Delivery.getDeliDeliveryDate().getTime());
        cv.put(Zpo05DBConstants.deliMode, zpo05Delivery.getDeliMode());
        cv.put(Zpo05DBConstants.deliDeliveryWho, zpo05Delivery.getDeliDeliveryWho());
        cv.put(Zpo05DBConstants.deliDeliveryOccur, zpo05Delivery.getDeliDeliveryOccur());
        cv.put(Zpo05DBConstants.deliHospitalId, zpo05Delivery.getDeliHospitalId());
        cv.put(Zpo05DBConstants.deliClinicId, zpo05Delivery.getDeliClinicId());
        cv.put(Zpo05DBConstants.deliDeliveryOther, zpo05Delivery.getDeliDeliveryOther());
        cv.put(Zpo05DBConstants.deliNumBirth, zpo05Delivery.getDeliNumBirth());
        cv.put(Zpo05DBConstants.deliFetalOutcome1, zpo05Delivery.getDeliFetalOutcome1());
        cv.put(Zpo05DBConstants.deliCauseDeath1, zpo05Delivery.getDeliCauseDeath1());
        cv.put(Zpo05DBConstants.deliSexBaby1, zpo05Delivery.getDeliSexBaby1());
        cv.put(Zpo05DBConstants.deliFetalOutcome2, zpo05Delivery.getDeliFetalOutcome2());
        cv.put(Zpo05DBConstants.deliCauseDeath2, zpo05Delivery.getDeliCauseDeath2());
        cv.put(Zpo05DBConstants.deliSexBaby2, zpo05Delivery.getDeliSexBaby2());
        cv.put(Zpo05DBConstants.deliFetalOutcome3, zpo05Delivery.getDeliFetalOutcome3());
        cv.put(Zpo05DBConstants.deliCauseDeath3, zpo05Delivery.getDeliCauseDeath3());
        cv.put(Zpo05DBConstants.deliSexBaby3, zpo05Delivery.getDeliSexBaby3());
        cv.put(Zpo05DBConstants.deliConsentInfant, zpo05Delivery.getDeliConsentInfant());
        cv.put(Zpo05DBConstants.deliReasonNoconsent, zpo05Delivery.getDeliReasonNoconsent());
        cv.put(Zpo05DBConstants.deliNoconsentOther, zpo05Delivery.getDeliNoconsentOther());
        cv.put(Zpo05DBConstants.deliIdCompleting, zpo05Delivery.getDeliIdCompleting());
        if (zpo05Delivery.getDeliDateCompleted()!=null) cv.put(Zpo05DBConstants.deliDateCompleted, zpo05Delivery.getDeliDateCompleted().getTime());
        cv.put(Zpo05DBConstants.deliIdReviewer, zpo05Delivery.getDeliIdReviewer());
        if (zpo05Delivery.getDeliDateReviewed()!=null) cv.put(Zpo05DBConstants.deliDateReviewed, zpo05Delivery.getDeliDateReviewed().getTime());
        cv.put(Zpo05DBConstants.deliIdDataEntry, zpo05Delivery.getDeliIdDataEntry());
        if (zpo05Delivery.getDeliDateEntered()!=null) cv.put(Zpo05DBConstants.deliDateEntered, zpo05Delivery.getDeliDateEntered().getTime());

        cv.put(Zpo05DBConstants.deliHyperDisease, zpo05Delivery.getDeliHyperDisease());
        cv.put(Zpo05DBConstants.deliPreterm1, zpo05Delivery.getDeliPreterm1());
        cv.put(Zpo05DBConstants.deliPreterm2, zpo05Delivery.getDeliPreterm2());
        cv.put(Zpo05DBConstants.deliPreterm3, zpo05Delivery.getDeliPreterm3());
        cv.put(Zpo05DBConstants.deliDeliverEarly, zpo05Delivery.getDeliDeliverEarly());
        
        if (zpo05Delivery.getRecordDate() != null) cv.put(MainDBConstants.recordDate, zpo05Delivery.getRecordDate().getTime());
        cv.put(MainDBConstants.recordUser, zpo05Delivery.getRecordUser());
        cv.put(MainDBConstants.pasive, String.valueOf(zpo05Delivery.getPasive()));
        cv.put(MainDBConstants.ID_INSTANCIA, zpo05Delivery.getIdInstancia());
        cv.put(MainDBConstants.FILE_PATH, zpo05Delivery.getInstancePath());
        cv.put(MainDBConstants.STATUS, zpo05Delivery.getEstado());
        cv.put(MainDBConstants.START, zpo05Delivery.getStart());
        cv.put(MainDBConstants.END, zpo05Delivery.getEnd());
        cv.put(MainDBConstants.DEVICE_ID, zpo05Delivery.getDeviceid());
        cv.put(MainDBConstants.SIM_SERIAL, zpo05Delivery.getSimserial());
        cv.put(MainDBConstants.PHONE_NUMBER, zpo05Delivery.getPhonenumber());
        if (zpo05Delivery.getToday() != null) cv.put(MainDBConstants.TODAY, zpo05Delivery.getToday().getTime());

        return cv;
    }

    public static Zpo05Delivery crearZpo05Delivery(Cursor cursor){
        Zpo05Delivery zpo05Delivery = new Zpo05Delivery();

        zpo05Delivery.setRecordId(cursor.getString(cursor.getColumnIndex(Zpo05DBConstants.recordId)));
        zpo05Delivery.setEventName(cursor.getString(cursor.getColumnIndex(Zpo05DBConstants.eventName)));

        if (cursor.getLong(cursor.getColumnIndex(Zpo05DBConstants.deliVisitDate))>0) zpo05Delivery.setDeliVisitDate(new Date(cursor.getLong(cursor.getColumnIndex(Zpo05DBConstants.deliVisitDate))));

        zpo05Delivery.setDeliOriginInfo(cursor.getString(cursor.getColumnIndex(Zpo05DBConstants.deliOriginInfo)));
        if (cursor.getFloat(cursor.getColumnIndex(Zpo05DBConstants.deliMotherWt))>0) zpo05Delivery.setDeliMotherWt(cursor.getFloat(cursor.getColumnIndex(Zpo05DBConstants.deliMotherWt)));

        zpo05Delivery.setDeliWtUnit(cursor.getString(cursor.getColumnIndex(Zpo05DBConstants.deliWtUnit)));
        if (cursor.getInt(cursor.getColumnIndex(Zpo05DBConstants.deliSystolic))>0) zpo05Delivery.setDeliSystolic(cursor.getInt(cursor.getColumnIndex(Zpo05DBConstants.deliSystolic)));
        if (cursor.getInt(cursor.getColumnIndex(Zpo05DBConstants.deliDiastolic))>0) zpo05Delivery.setDeliDiastolic(cursor.getInt(cursor.getColumnIndex(Zpo05DBConstants.deliDiastolic)));
        if (cursor.getFloat(cursor.getColumnIndex(Zpo05DBConstants.deliTemperature))>0) zpo05Delivery.setDeliTemperature(cursor.getFloat(cursor.getColumnIndex(Zpo05DBConstants.deliTemperature)));
        zpo05Delivery.setDeliTempUnit(cursor.getString(cursor.getColumnIndex(Zpo05DBConstants.deliTempUnit)));
        if (cursor.getLong(cursor.getColumnIndex(Zpo05DBConstants.deliDeliveryDate))>0) zpo05Delivery.setDeliDeliveryDate(new Date(cursor.getLong(cursor.getColumnIndex(Zpo05DBConstants.deliDeliveryDate))));
        zpo05Delivery.setDeliMode(cursor.getString(cursor.getColumnIndex(Zpo05DBConstants.deliMode)));
        zpo05Delivery.setDeliDeliveryWho(cursor.getString(cursor.getColumnIndex(Zpo05DBConstants.deliDeliveryWho)));
        zpo05Delivery.setDeliDeliveryOccur(cursor.getString(cursor.getColumnIndex(Zpo05DBConstants.deliDeliveryOccur)));
        zpo05Delivery.setDeliHospitalId(cursor.getString(cursor.getColumnIndex(Zpo05DBConstants.deliHospitalId)));
        zpo05Delivery.setDeliClinicId(cursor.getString(cursor.getColumnIndex(Zpo05DBConstants.deliClinicId)));
        zpo05Delivery.setDeliDeliveryOther(cursor.getString(cursor.getColumnIndex(Zpo05DBConstants.deliDeliveryOther)));
        zpo05Delivery.setDeliNumBirth(cursor.getString(cursor.getColumnIndex(Zpo05DBConstants.deliNumBirth)));
        zpo05Delivery.setDeliFetalOutcome1(cursor.getString(cursor.getColumnIndex(Zpo05DBConstants.deliFetalOutcome1)));
        zpo05Delivery.setDeliCauseDeath1(cursor.getString(cursor.getColumnIndex(Zpo05DBConstants.deliCauseDeath1)));
        zpo05Delivery.setDeliSexBaby1(cursor.getString(cursor.getColumnIndex(Zpo05DBConstants.deliSexBaby1)));
        zpo05Delivery.setDeliFetalOutcome2(cursor.getString(cursor.getColumnIndex(Zpo05DBConstants.deliFetalOutcome2)));
        zpo05Delivery.setDeliCauseDeath2(cursor.getString(cursor.getColumnIndex(Zpo05DBConstants.deliCauseDeath2)));
        zpo05Delivery.setDeliSexBaby2(cursor.getString(cursor.getColumnIndex(Zpo05DBConstants.deliSexBaby2)));
        zpo05Delivery.setDeliFetalOutcome3(cursor.getString(cursor.getColumnIndex(Zpo05DBConstants.deliFetalOutcome3)));
        zpo05Delivery.setDeliCauseDeath3(cursor.getString(cursor.getColumnIndex(Zpo05DBConstants.deliCauseDeath3)));
        zpo05Delivery.setDeliSexBaby3(cursor.getString(cursor.getColumnIndex(Zpo05DBConstants.deliSexBaby3)));
        zpo05Delivery.setDeliConsentInfant(cursor.getString(cursor.getColumnIndex(Zpo05DBConstants.deliConsentInfant)));
        zpo05Delivery.setDeliReasonNoconsent(cursor.getString(cursor.getColumnIndex(Zpo05DBConstants.deliReasonNoconsent)));
        zpo05Delivery.setDeliNoconsentOther(cursor.getString(cursor.getColumnIndex(Zpo05DBConstants.deliNoconsentOther)));
        zpo05Delivery.setDeliIdCompleting(cursor.getString(cursor.getColumnIndex(Zpo05DBConstants.deliIdCompleting)));
        if (cursor.getLong(cursor.getColumnIndex(Zpo05DBConstants.deliDateCompleted))>0) zpo05Delivery.setDeliDateCompleted(new Date(cursor.getLong(cursor.getColumnIndex(Zpo05DBConstants.deliDateCompleted))));
        zpo05Delivery.setDeliIdReviewer(cursor.getString(cursor.getColumnIndex(Zpo05DBConstants.deliIdReviewer)));
        if (cursor.getLong(cursor.getColumnIndex(Zpo05DBConstants.deliDateReviewed))>0) zpo05Delivery.setDeliDateReviewed(new Date(cursor.getLong(cursor.getColumnIndex(Zpo05DBConstants.deliDateReviewed))));
        zpo05Delivery.setDeliIdDataEntry(cursor.getString(cursor.getColumnIndex(Zpo05DBConstants.deliIdDataEntry)));
        if (cursor.getLong(cursor.getColumnIndex(Zpo05DBConstants.deliDateEntered))>0) zpo05Delivery.setDeliDateEntered(new Date(cursor.getLong(cursor.getColumnIndex(Zpo05DBConstants.deliDateEntered))));

        zpo05Delivery.setDeliHyperDisease(cursor.getString(cursor.getColumnIndex(Zpo05DBConstants.deliHyperDisease)));
        zpo05Delivery.setDeliPreterm1(cursor.getString(cursor.getColumnIndex(Zpo05DBConstants.deliPreterm1)));
        zpo05Delivery.setDeliPreterm2(cursor.getString(cursor.getColumnIndex(Zpo05DBConstants.deliPreterm2)));
        zpo05Delivery.setDeliPreterm3(cursor.getString(cursor.getColumnIndex(Zpo05DBConstants.deliPreterm3)));
        zpo05Delivery.setDeliDeliverEarly(cursor.getString(cursor.getColumnIndex(Zpo05DBConstants.deliDeliverEarly)));
        
        if(cursor.getLong(cursor.getColumnIndex(MainDBConstants.recordDate))>0) zpo05Delivery.setRecordDate(new Date(cursor.getLong(cursor.getColumnIndex(MainDBConstants.recordDate))));
        zpo05Delivery.setRecordUser(cursor.getString(cursor.getColumnIndex(MainDBConstants.recordUser)));
        zpo05Delivery.setPasive(cursor.getString(cursor.getColumnIndex(MainDBConstants.pasive)).charAt(0));
        zpo05Delivery.setIdInstancia(cursor.getInt(cursor.getColumnIndex(MainDBConstants.ID_INSTANCIA)));
        zpo05Delivery.setInstancePath(cursor.getString(cursor.getColumnIndex(MainDBConstants.FILE_PATH)));
        zpo05Delivery.setEstado(cursor.getString(cursor.getColumnIndex(MainDBConstants.STATUS)));
        zpo05Delivery.setStart(cursor.getString(cursor.getColumnIndex(MainDBConstants.START)));
        zpo05Delivery.setEnd(cursor.getString(cursor.getColumnIndex(MainDBConstants.END)));
        zpo05Delivery.setSimserial(cursor.getString(cursor.getColumnIndex(MainDBConstants.SIM_SERIAL)));
        zpo05Delivery.setPhonenumber(cursor.getString(cursor.getColumnIndex(MainDBConstants.PHONE_NUMBER)));
        zpo05Delivery.setDeviceid(cursor.getString(cursor.getColumnIndex(MainDBConstants.DEVICE_ID)));
        if(cursor.getLong(cursor.getColumnIndex(MainDBConstants.TODAY))>0) zpo05Delivery.setToday(new Date(cursor.getLong(cursor.getColumnIndex(MainDBConstants.TODAY))));

        return zpo05Delivery;
    }
}
