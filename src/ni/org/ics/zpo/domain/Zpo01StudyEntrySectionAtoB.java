package ni.org.ics.zpo.domain;

import java.util.Date;

/**
 * Created by FIRSTICT on 10/6/2016.
 * V1.0
 */
public class Zpo01StudyEntrySectionAtoB extends BaseMetaData {

    private static final long serialVersionUID = 1L;
    private String recordId;
    private String eventName;
    private Date seaVdate;
    private Date seaBirthdate;
    private String seaPregnantBefore;
    private String seaPregStillDeliAfter;
    private String seaCity;
    private String seaState;
    private String seaCountry;
    private String seaLive;
    private Integer seaAgeLeave;
    private String seaLeavena;
    private String seaMstatus;
    private String seaRace;
    private String seaEthnicityOther;
    private String seaDegreeYou;
    private Float seaYdegreeYears;
    private String seaDegreeSpouse;
    private Float seaSdegreeYears;


    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getSeaVdate() {
        return seaVdate;
    }

    public void setSeaVdate(Date seaVdate) {
        this.seaVdate = seaVdate;
    }

    public Date getSeaBirthdate() {
        return seaBirthdate;
    }

    public void setSeaBirthdate(Date seaBirthdate) {
        this.seaBirthdate = seaBirthdate;
    }

    public String getSeaPregnantBefore() {
        return seaPregnantBefore;
    }

    public void setSeaPregnantBefore(String seaPregnantBefore) {
        this.seaPregnantBefore = seaPregnantBefore;
    }

    public String getSeaPregStillDeliAfter() {
        return seaPregStillDeliAfter;
    }

    public void setSeaPregStillDeliAfter(String seaPregStillDeliAfter) {
        this.seaPregStillDeliAfter = seaPregStillDeliAfter;
    }

    public String getSeaCity() {
        return seaCity;
    }

    public void setSeaCity(String seaCity) {
        this.seaCity = seaCity;
    }

    public String getSeaState() {
        return seaState;
    }

    public void setSeaState(String seaState) {
        this.seaState = seaState;
    }

    public String getSeaCountry() {
        return seaCountry;
    }

    public void setSeaCountry(String seaCountry) {
        this.seaCountry = seaCountry;
    }

    public String getSeaLive() {
        return seaLive;
    }

    public void setSeaLive(String seaLive) {
        this.seaLive = seaLive;
    }

    public Integer getSeaAgeLeave() {
        return seaAgeLeave;
    }

    public void setSeaAgeLeave(Integer seaAgeLeave) {
        this.seaAgeLeave = seaAgeLeave;
    }

    public String getSeaLeavena() {
        return seaLeavena;
    }

    public void setSeaLeavena(String seaLeavena) {
        this.seaLeavena = seaLeavena;
    }

    public String getSeaMstatus() {
        return seaMstatus;
    }

    public void setSeaMstatus(String seaMstatus) {
        this.seaMstatus = seaMstatus;
    }

    public String getSeaRace() {
        return seaRace;
    }

    public void setSeaRace(String seaRace) {
        this.seaRace = seaRace;
    }

    public String getSeaEthnicityOther() {
        return seaEthnicityOther;
    }

    public void setSeaEthnicityOther(String seaEthnicityOther) {
        this.seaEthnicityOther = seaEthnicityOther;
    }

    public String getSeaDegreeYou() {
        return seaDegreeYou;
    }

    public void setSeaDegreeYou(String seaDegreeYou) {
        this.seaDegreeYou = seaDegreeYou;
    }

    public Float getSeaYdegreeYears() {
        return seaYdegreeYears;
    }

    public void setSeaYdegreeYears(Float seaYdegreeYears) {
        this.seaYdegreeYears = seaYdegreeYears;
    }

    public String getSeaDegreeSpouse() {
        return seaDegreeSpouse;
    }

    public void setSeaDegreeSpouse(String seaDegreeSpouse) {
        this.seaDegreeSpouse = seaDegreeSpouse;
    }

    public Float getSeaSdegreeYears() {
        return seaSdegreeYears;
    }

    public void setSeaSdegreeYears(Float seaSdegreeYears) {
        this.seaSdegreeYears = seaSdegreeYears;
    }


    @Override
    public String toString() {
        return this.recordId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Zpo01StudyEntrySectionAtoB)) return false;

        Zpo01StudyEntrySectionAtoB that = (Zpo01StudyEntrySectionAtoB) o;

        return (recordId.equals(that.recordId));
    }
}
