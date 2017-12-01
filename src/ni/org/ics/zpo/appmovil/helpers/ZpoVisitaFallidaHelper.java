package ni.org.ics.zpo.appmovil.helpers;

import android.content.ContentValues;
import android.database.Cursor;
import ni.org.ics.zpo.appmovil.utils.MainDBConstants;
import ni.org.ics.zpo.domain.ZpoVisitaFallida;

import java.util.Date;

/**
 * Created by Miguel Salinas on 11/29/2017.
 * V1.0
 */
public class ZpoVisitaFallidaHelper {

    public static ContentValues crearZpoVisitaFallida(ZpoVisitaFallida datos){
        ContentValues cv = new ContentValues();
        cv.put(MainDBConstants.id, datos.getId());
        cv.put(MainDBConstants.razon, datos.getRazon());
        cv.put(MainDBConstants.otraRazon, datos.getOtraRazon());
        if (datos.getFechaVisita() != null) cv.put(MainDBConstants.fechaVisita, datos.getFechaVisita().getTime());
        cv.put(MainDBConstants.persona, datos.getPersona());

        if (datos.getRecordDate() != null) cv.put(MainDBConstants.recordDate, datos.getRecordDate().getTime());
        cv.put(MainDBConstants.recordUser, datos.getRecordUser());
        cv.put(MainDBConstants.pasive, String.valueOf(datos.getPasive()));
        cv.put(MainDBConstants.ID_INSTANCIA, datos.getIdInstancia());
        cv.put(MainDBConstants.FILE_PATH, datos.getInstancePath());
        cv.put(MainDBConstants.STATUS, datos.getEstado());
        cv.put(MainDBConstants.START, datos.getStart());
        cv.put(MainDBConstants.END, datos.getEnd());
        cv.put(MainDBConstants.DEVICE_ID, datos.getDeviceid());
        cv.put(MainDBConstants.SIM_SERIAL, datos.getSimserial());
        cv.put(MainDBConstants.PHONE_NUMBER, datos.getPhonenumber());
        if (datos.getToday() != null) cv.put(MainDBConstants.TODAY, datos.getToday().getTime());
        return cv;
    }

    public static ZpoVisitaFallida crearZpoVisitaFallida(Cursor cursorDatos){
        ZpoVisitaFallida mDatos = new ZpoVisitaFallida();
        mDatos.setId(cursorDatos.getString(cursorDatos.getColumnIndex(MainDBConstants.id)));
        mDatos.setRazon(cursorDatos.getString(cursorDatos.getColumnIndex(MainDBConstants.razon)));
        mDatos.setOtraRazon(cursorDatos.getString(cursorDatos.getColumnIndex(MainDBConstants.otraRazon)));
        if(cursorDatos.getLong(cursorDatos.getColumnIndex(MainDBConstants.fechaVisita))>0) mDatos.setFechaVisita(new Date(cursorDatos.getLong(cursorDatos.getColumnIndex(MainDBConstants.fechaVisita))));
        mDatos.setPersona(cursorDatos.getString(cursorDatos.getColumnIndex(MainDBConstants.persona)));

        if(cursorDatos.getLong(cursorDatos.getColumnIndex(MainDBConstants.recordDate))>0) mDatos.setRecordDate(new Date(cursorDatos.getLong(cursorDatos.getColumnIndex(MainDBConstants.recordDate))));
        mDatos.setRecordUser(cursorDatos.getString(cursorDatos.getColumnIndex(MainDBConstants.recordUser)));
        mDatos.setPasive(cursorDatos.getString(cursorDatos.getColumnIndex(MainDBConstants.pasive)).charAt(0));
        mDatos.setIdInstancia(cursorDatos.getInt(cursorDatos.getColumnIndex(MainDBConstants.ID_INSTANCIA)));
        mDatos.setInstancePath(cursorDatos.getString(cursorDatos.getColumnIndex(MainDBConstants.FILE_PATH)));
        mDatos.setEstado(cursorDatos.getString(cursorDatos.getColumnIndex(MainDBConstants.STATUS)));
        mDatos.setStart(cursorDatos.getString(cursorDatos.getColumnIndex(MainDBConstants.START)));
        mDatos.setEnd(cursorDatos.getString(cursorDatos.getColumnIndex(MainDBConstants.END)));
        mDatos.setSimserial(cursorDatos.getString(cursorDatos.getColumnIndex(MainDBConstants.SIM_SERIAL)));
        mDatos.setPhonenumber(cursorDatos.getString(cursorDatos.getColumnIndex(MainDBConstants.PHONE_NUMBER)));
        mDatos.setDeviceid(cursorDatos.getString(cursorDatos.getColumnIndex(MainDBConstants.DEVICE_ID)));
        if(cursorDatos.getLong(cursorDatos.getColumnIndex(MainDBConstants.TODAY))>0) mDatos.setToday(new Date(cursorDatos.getLong(cursorDatos.getColumnIndex(MainDBConstants.TODAY))));
        return mDatos;
    }
}
