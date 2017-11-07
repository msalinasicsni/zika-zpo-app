package ni.org.ics.zpo.domain;

import java.util.Date;

/**
 * Created by FIRSTICT on 2/10/2017.
 * V1.0
 */
public class ZpoEstadoInfante extends BaseMetaData{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String recordId;
    private char ingreso = '0';
    private char mes12 = '0';
    private char mes24 = '0';

    public ZpoEstadoInfante(){

    }

    public ZpoEstadoInfante(String recordId, char ingreso, char mes12,
                           char mes24, Date recordDate, String recordUser, char pasive,
                           Integer idInstancia, String instancePath, String estado,
                           String start, String end, String deviceid, String simserial,
                           String phonenumber, Date today) {
        super(recordDate, recordUser, pasive,
                idInstancia, instancePath, estado,
                start, end, deviceid, simserial,
                phonenumber, today);
        this.recordId = recordId;
        this.ingreso = ingreso;
        this.mes12 = mes12;
        this.mes24 = mes24;
    }
    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    
    public char getIngreso() {
        return ingreso;
    }

    public void setIngreso(char ingreso) {
        this.ingreso = ingreso;
    }

    
    public char getMes12() {
        return mes12;
    }

    public void setMes12(char mes12) {
        this.mes12 = mes12;
    }

    
    public char getMes24() {
        return mes24;
    }

    public void setMes24(char mes24) {
        this.mes24 = mes24;
    }
	
	@Override
	public String toString(){
		return this.recordId;
	}
	
	@Override
	public boolean equals(Object other) {
		
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ZpoEstadoInfante))
			return false;
		
		ZpoEstadoInfante castOther = (ZpoEstadoInfante) other;

		return (this.getRecordId().equals(castOther.getRecordId()));
	}
}
