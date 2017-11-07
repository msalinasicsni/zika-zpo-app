package ni.org.ics.zpo.appmovil.helpers;

import android.content.ContentValues;
import android.database.Cursor;
import ni.org.ics.zpo.domain.ZpoEstadoInfante;
import ni.org.ics.zpo.appmovil.utils.MainDBConstants;

import java.util.Date;

/**
 * Created by FIRSTICT on 2/8/2017.
 * V1.0
 */
public class ZpoEstadoInfanteHelper {

    public static ContentValues crearZpoEstadoInfante(ZpoEstadoInfante mZpoEstadoInfante){
        ContentValues cv = new ContentValues();

        cv.put(MainDBConstants.recordId, mZpoEstadoInfante.getRecordId());
        cv.put(MainDBConstants.ingreso, String.valueOf(mZpoEstadoInfante.getIngreso()));
        cv.put(MainDBConstants.mes12, String.valueOf(mZpoEstadoInfante.getMes12()));
        cv.put(MainDBConstants.mes24, String.valueOf(mZpoEstadoInfante.getMes24()));

        if (mZpoEstadoInfante.getRecordDate() != null) cv.put(MainDBConstants.recordDate, mZpoEstadoInfante.getRecordDate().getTime());
        cv.put(MainDBConstants.recordUser, mZpoEstadoInfante.getRecordUser());
        cv.put(MainDBConstants.pasive, String.valueOf(mZpoEstadoInfante.getPasive()));
        cv.put(MainDBConstants.ID_INSTANCIA, mZpoEstadoInfante.getIdInstancia());
        cv.put(MainDBConstants.FILE_PATH, mZpoEstadoInfante.getInstancePath());
        cv.put(MainDBConstants.STATUS, mZpoEstadoInfante.getEstado());
        cv.put(MainDBConstants.START, mZpoEstadoInfante.getStart());
        cv.put(MainDBConstants.END, mZpoEstadoInfante.getEnd());
        cv.put(MainDBConstants.DEVICE_ID, mZpoEstadoInfante.getDeviceid());
        cv.put(MainDBConstants.SIM_SERIAL, mZpoEstadoInfante.getSimserial());
        cv.put(MainDBConstants.PHONE_NUMBER, mZpoEstadoInfante.getPhonenumber());
        if (mZpoEstadoInfante.getToday() != null) cv.put(MainDBConstants.TODAY, mZpoEstadoInfante.getToday().getTime());

        return cv;

    }

    public static ZpoEstadoInfante crearZpoEstadoInfante(Cursor cursor){

    	ZpoEstadoInfante mZpoEstadoInfante = new ZpoEstadoInfante();
        mZpoEstadoInfante.setRecordId(cursor.getString(cursor.getColumnIndex(MainDBConstants.recordId)));
        
        mZpoEstadoInfante.setIngreso(cursor.getString(cursor.getColumnIndex(MainDBConstants.ingreso)).charAt(0));
        mZpoEstadoInfante.setMes12(cursor.getString(cursor.getColumnIndex(MainDBConstants.mes12)).charAt(0));
        mZpoEstadoInfante.setMes24(cursor.getString(cursor.getColumnIndex(MainDBConstants.mes24)).charAt(0));

        if(cursor.getLong(cursor.getColumnIndex(MainDBConstants.recordDate))>0) mZpoEstadoInfante.setRecordDate(new Date(cursor.getLong(cursor.getColumnIndex(MainDBConstants.recordDate))));
        mZpoEstadoInfante.setRecordUser(cursor.getString(cursor.getColumnIndex(MainDBConstants.recordUser)));
        mZpoEstadoInfante.setPasive(cursor.getString(cursor.getColumnIndex(MainDBConstants.pasive)).charAt(0));
        mZpoEstadoInfante.setIdInstancia(cursor.getInt(cursor.getColumnIndex(MainDBConstants.ID_INSTANCIA)));
        mZpoEstadoInfante.setInstancePath(cursor.getString(cursor.getColumnIndex(MainDBConstants.FILE_PATH)));
        mZpoEstadoInfante.setEstado(cursor.getString(cursor.getColumnIndex(MainDBConstants.STATUS)));
        mZpoEstadoInfante.setStart(cursor.getString(cursor.getColumnIndex(MainDBConstants.START)));
        mZpoEstadoInfante.setEnd(cursor.getString(cursor.getColumnIndex(MainDBConstants.END)));
        mZpoEstadoInfante.setSimserial(cursor.getString(cursor.getColumnIndex(MainDBConstants.SIM_SERIAL)));
        mZpoEstadoInfante.setPhonenumber(cursor.getString(cursor.getColumnIndex(MainDBConstants.PHONE_NUMBER)));
        mZpoEstadoInfante.setDeviceid(cursor.getString(cursor.getColumnIndex(MainDBConstants.DEVICE_ID)));
        if(cursor.getLong(cursor.getColumnIndex(MainDBConstants.TODAY))>0) mZpoEstadoInfante.setToday(new Date(cursor.getLong(cursor.getColumnIndex(MainDBConstants.TODAY))));

        return mZpoEstadoInfante;
    }
}
