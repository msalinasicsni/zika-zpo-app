package ni.org.ics.zpo.appmovil.helpers;

import android.content.ContentValues;
import android.database.Cursor;
import ni.org.ics.zpo.domain.ZpoEstadoEmbarazada;
import ni.org.ics.zpo.appmovil.utils.MainDBConstants;

import java.util.Date;

public class ZpoEstadoMadreHelper {
	
	public static ContentValues crearZpoEstadoMadreValues(ZpoEstadoEmbarazada estado){
		ContentValues cv = new ContentValues();
		cv.put(MainDBConstants.recordId, estado.getRecordId());
		cv.put(MainDBConstants.ingreso, String.valueOf(estado.getIngreso()));
		if (estado.getRecordDate() != null) cv.put(MainDBConstants.recordDate, estado.getRecordDate().getTime());
		cv.put(MainDBConstants.recordUser, estado.getRecordUser());
		cv.put(MainDBConstants.pasive, String.valueOf(estado.getPasive()));
		cv.put(MainDBConstants.ID_INSTANCIA, estado.getIdInstancia());
		cv.put(MainDBConstants.FILE_PATH, estado.getInstancePath());
		cv.put(MainDBConstants.STATUS, estado.getEstado());
		cv.put(MainDBConstants.START, estado.getStart());
		cv.put(MainDBConstants.END, estado.getEnd());
		cv.put(MainDBConstants.DEVICE_ID, estado.getDeviceid());
		cv.put(MainDBConstants.SIM_SERIAL, estado.getSimserial());
		cv.put(MainDBConstants.PHONE_NUMBER, estado.getPhonenumber());
		if (estado.getToday() != null) cv.put(MainDBConstants.TODAY, estado.getToday().getTime());
		return cv; 
	}	
	
	public static ZpoEstadoEmbarazada crearZpoEstadoMadre(Cursor cursorEstado){
		ZpoEstadoEmbarazada mEstado = new ZpoEstadoEmbarazada();
		mEstado.setRecordId(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.recordId)));
		mEstado.setIngreso(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.ingreso)).charAt(0));
		if(cursorEstado.getLong(cursorEstado.getColumnIndex(MainDBConstants.recordDate))>0) mEstado.setRecordDate(new Date(cursorEstado.getLong(cursorEstado.getColumnIndex(MainDBConstants.recordDate))));
		mEstado.setRecordUser(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.recordUser)));
		mEstado.setPasive(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.pasive)).charAt(0));
		mEstado.setIdInstancia(cursorEstado.getInt(cursorEstado.getColumnIndex(MainDBConstants.ID_INSTANCIA)));
		mEstado.setInstancePath(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.FILE_PATH)));
		mEstado.setEstado(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.STATUS)));
		mEstado.setStart(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.START)));
		mEstado.setEnd(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.END)));
		mEstado.setSimserial(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.SIM_SERIAL)));
		mEstado.setPhonenumber(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.PHONE_NUMBER)));
		mEstado.setDeviceid(cursorEstado.getString(cursorEstado.getColumnIndex(MainDBConstants.DEVICE_ID)));
		if(cursorEstado.getLong(cursorEstado.getColumnIndex(MainDBConstants.TODAY))>0) mEstado.setToday(new Date(cursorEstado.getLong(cursorEstado.getColumnIndex(MainDBConstants.TODAY))));
		return mEstado;
	}
}
