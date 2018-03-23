package ni.org.ics.zpo.domain;

import java.util.Date;

/**
 * Created by Miguel Salinas on 11/29/2017.
 * V1.0
 */
public class ZpoVisitaFallida extends BaseMetaData {

    private static final long serialVersionUID = 1L;
    private String id;
    private String razon;
    private String otraRazon;
    private String persona;
    private Date fechaVisita;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }

    public String getOtraRazon() {
        return otraRazon;
    }

    public void setOtraRazon(String otraRazon) {
        this.otraRazon = otraRazon;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public Date getFechaVisita() {
        return fechaVisita;
    }

    public void setFechaVisita(Date fechaVisita) {
        this.fechaVisita = fechaVisita;
    }
}
