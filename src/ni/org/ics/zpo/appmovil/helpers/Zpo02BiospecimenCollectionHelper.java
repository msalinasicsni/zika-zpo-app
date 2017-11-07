package ni.org.ics.zpo.appmovil.helpers;

import android.content.ContentValues;
import android.database.Cursor;
import ni.org.ics.zpo.domain.Zpo02BiospecimenCollection;
import ni.org.ics.zpo.appmovil.utils.MainDBConstants;
import ni.org.ics.zpo.appmovil.utils.Zpo02DBConstants;

import java.util.Date;

/**
 * Created by FIRSTICT on 10/21/2016.
 * V1.0
 */
public class Zpo02BiospecimenCollectionHelper {

    public static ContentValues crearZpo02BiospecimenCollection(Zpo02BiospecimenCollection biospecimenCollection){
        ContentValues cv = new ContentValues();

        cv.put(Zpo02DBConstants.recordId, biospecimenCollection.getRecordId());
        cv.put(Zpo02DBConstants.eventName, biospecimenCollection.getEventName());
        if (biospecimenCollection.getBscDov()!=null) cv.put(Zpo02DBConstants.bscDov, biospecimenCollection.getBscDov().getTime());
        cv.put(Zpo02DBConstants.bscVisit, biospecimenCollection.getBscVisit());
        cv.put(Zpo02DBConstants.bscMatBldCol, biospecimenCollection.getBscMatBldCol());
        cv.put(Zpo02DBConstants.bscMatBldTyp1, biospecimenCollection.getBscMatBldTyp1());
        cv.put(Zpo02DBConstants.bscMatBldId1, biospecimenCollection.getBscMatBldId1());
        cv.put(Zpo02DBConstants.bscMatBldVol1, biospecimenCollection.getBscMatBldVol1());
        cv.put(Zpo02DBConstants.bscMatBldTime, biospecimenCollection.getBscMatBldTime());
        cv.put(Zpo02DBConstants.bscMatBldCom, biospecimenCollection.getBscMatBldCom());

        cv.put(Zpo02DBConstants.bscPerson1, biospecimenCollection.getBscPerson1());
        if (biospecimenCollection.getBscCompleteDate1()!=null)cv.put(Zpo02DBConstants.bscCompleteDate1, biospecimenCollection.getBscCompleteDate1().getTime());
        cv.put(Zpo02DBConstants.bscPerson2, biospecimenCollection.getBscPerson2());
        if (biospecimenCollection.getBscCompleteDate2()!=null) cv.put(Zpo02DBConstants.bscCompleteDate2, biospecimenCollection.getBscCompleteDate2().getTime());
        cv.put(Zpo02DBConstants.bscPerson3, biospecimenCollection.getBscPerson3());
        if (biospecimenCollection.getBscCompleteDate3()!=null) cv.put(Zpo02DBConstants.bscCompleteDate3, biospecimenCollection.getBscCompleteDate3().getTime());

        if (biospecimenCollection.getRecordDate() != null) cv.put(MainDBConstants.recordDate, biospecimenCollection.getRecordDate().getTime());
        cv.put(MainDBConstants.recordUser, biospecimenCollection.getRecordUser());
        cv.put(MainDBConstants.pasive, String.valueOf(biospecimenCollection.getPasive()));
        cv.put(MainDBConstants.ID_INSTANCIA, biospecimenCollection.getIdInstancia());
        cv.put(MainDBConstants.FILE_PATH, biospecimenCollection.getInstancePath());
        cv.put(MainDBConstants.STATUS, biospecimenCollection.getEstado());
        cv.put(MainDBConstants.START, biospecimenCollection.getStart());
        cv.put(MainDBConstants.END, biospecimenCollection.getEnd());
        cv.put(MainDBConstants.DEVICE_ID, biospecimenCollection.getDeviceid());
        cv.put(MainDBConstants.SIM_SERIAL, biospecimenCollection.getSimserial());
        cv.put(MainDBConstants.PHONE_NUMBER, biospecimenCollection.getPhonenumber());
        if (biospecimenCollection.getToday() != null) cv.put(MainDBConstants.TODAY, biospecimenCollection.getToday().getTime());

        return cv;
    }

    public static Zpo02BiospecimenCollection crearZpo02BiospecimenCollection(Cursor cursorBC){
        Zpo02BiospecimenCollection biospecimenCollection = new Zpo02BiospecimenCollection();
        biospecimenCollection.setRecordId(cursorBC.getString(cursorBC.getColumnIndex(Zpo02DBConstants.recordId)));
        biospecimenCollection.setEventName(cursorBC.getString(cursorBC.getColumnIndex(Zpo02DBConstants.eventName)));
        if (cursorBC.getLong(cursorBC.getColumnIndex(Zpo02DBConstants.bscDov))>0) biospecimenCollection.setBscDov(new Date(cursorBC.getLong(cursorBC.getColumnIndex(Zpo02DBConstants.bscDov))));
        biospecimenCollection.setBscVisit(cursorBC.getString(cursorBC.getColumnIndex(Zpo02DBConstants.bscVisit)));
        biospecimenCollection.setBscMatBldCol(cursorBC.getString(cursorBC.getColumnIndex(Zpo02DBConstants.bscMatBldCol)));
        biospecimenCollection.setBscMatBldTyp1(cursorBC.getString(cursorBC.getColumnIndex(Zpo02DBConstants.bscMatBldTyp1)));
        biospecimenCollection.setBscMatBldId1(cursorBC.getString(cursorBC.getColumnIndex(Zpo02DBConstants.bscMatBldId1)));
        if (cursorBC.getInt(cursorBC.getColumnIndex(Zpo02DBConstants.bscMatBldVol1))>0) biospecimenCollection.setBscMatBldVol1(cursorBC.getDouble(cursorBC.getColumnIndex(Zpo02DBConstants.bscMatBldVol1)));
        biospecimenCollection.setBscMatBldTime(cursorBC.getString(cursorBC.getColumnIndex(Zpo02DBConstants.bscMatBldTime)));
        biospecimenCollection.setBscMatBldCom(cursorBC.getString(cursorBC.getColumnIndex(Zpo02DBConstants.bscMatBldCom)));

        biospecimenCollection.setBscPerson1(cursorBC.getString(cursorBC.getColumnIndex(Zpo02DBConstants.bscPerson1)));
        if (cursorBC.getLong(cursorBC.getColumnIndex(Zpo02DBConstants.bscCompleteDate1))>0) biospecimenCollection.setBscCompleteDate1(new Date(cursorBC.getLong(cursorBC.getColumnIndex(Zpo02DBConstants.bscCompleteDate1))));
        biospecimenCollection.setBscPerson2(cursorBC.getString(cursorBC.getColumnIndex(Zpo02DBConstants.bscPerson2)));
        if (cursorBC.getLong(cursorBC.getColumnIndex(Zpo02DBConstants.bscCompleteDate2))>0) biospecimenCollection.setBscCompleteDate2(new Date(cursorBC.getLong(cursorBC.getColumnIndex(Zpo02DBConstants.bscCompleteDate2))));
        biospecimenCollection.setBscPerson3(cursorBC.getString(cursorBC.getColumnIndex(Zpo02DBConstants.bscPerson3)));
        if (cursorBC.getLong(cursorBC.getColumnIndex(Zpo02DBConstants.bscCompleteDate3))>0) biospecimenCollection.setBscCompleteDate3(new Date(cursorBC.getLong(cursorBC.getColumnIndex(Zpo02DBConstants.bscCompleteDate3))));

        if(cursorBC.getLong(cursorBC.getColumnIndex(MainDBConstants.recordDate))>0) biospecimenCollection.setRecordDate(new Date(cursorBC.getLong(cursorBC.getColumnIndex(MainDBConstants.recordDate))));
        biospecimenCollection.setRecordUser(cursorBC.getString(cursorBC.getColumnIndex(MainDBConstants.recordUser)));
        biospecimenCollection.setPasive(cursorBC.getString(cursorBC.getColumnIndex(MainDBConstants.pasive)).charAt(0));
        biospecimenCollection.setIdInstancia(cursorBC.getInt(cursorBC.getColumnIndex(MainDBConstants.ID_INSTANCIA)));
        biospecimenCollection.setInstancePath(cursorBC.getString(cursorBC.getColumnIndex(MainDBConstants.FILE_PATH)));
        biospecimenCollection.setEstado(cursorBC.getString(cursorBC.getColumnIndex(MainDBConstants.STATUS)));
        biospecimenCollection.setStart(cursorBC.getString(cursorBC.getColumnIndex(MainDBConstants.START)));
        biospecimenCollection.setEnd(cursorBC.getString(cursorBC.getColumnIndex(MainDBConstants.END)));
        biospecimenCollection.setSimserial(cursorBC.getString(cursorBC.getColumnIndex(MainDBConstants.SIM_SERIAL)));
        biospecimenCollection.setPhonenumber(cursorBC.getString(cursorBC.getColumnIndex(MainDBConstants.PHONE_NUMBER)));
        biospecimenCollection.setDeviceid(cursorBC.getString(cursorBC.getColumnIndex(MainDBConstants.DEVICE_ID)));
        if(cursorBC.getLong(cursorBC.getColumnIndex(MainDBConstants.TODAY))>0) biospecimenCollection.setToday(new Date(cursorBC.getLong(cursorBC.getColumnIndex(MainDBConstants.TODAY))));

        return biospecimenCollection;
    }
}
