package ni.org.ics.zpo.appmovil.parsers;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.util.Date;

/**
 * Created by FIRSTICT on 11/6/2017.
 * V1.0
 */
public class Zpo01StudyEntrySectionAtoBXml {

    @Element(required=true)
    private Date seaVdate;
    @Element(required=false)
    private Date seaBirthdate;
    @Element(required=false)
    private String seaPregnantBefore;
    @Element(required=false)
    private String seaPregStillDeliAfter;
    @Element(required=false)
    private String seaCity;
    @Element(required=false)
    private String seaState;
    @Element(required=false)
    private String seaCountry;
    @Element(required=false)
    private String seaLive;
    @Element(required=false)
    private Integer seaAgeLeave;
    @Element(required=false)
    private String seaLeavena;
    @Element(required=false)
    private String seaMstatus;
    @Element(required=false)
    private String seaRace;
    @Element(required=false)
    private String seaEthnicityOther;
    @Element(required=false)
    private String seaDegreeYou;
    @Element(required=false)
    private Float seaYdegreeYears;
    @Element(required=false)
    private String seaDegreeSpouse;
    @Element(required=false)
    private Float seaSdegreeYears;

    @Element(required=false)
    private String question1;

    @Element(required=false)
    private String note1;
    @Element(required=false)
    private String note2;

    @Element(required=false)
    private String group1;
    @Element(required=false)
    private String group2;
    @Element(required=false)
    private String group3;
    @Element(required=false)
    private String group4;
    @Element(required=false)
    private String group5;

    @Attribute
    private String id;
    @Element(required=false)
    private Meta meta;

    @Element(required=false)
    private String start;
    @Element(required=false)
    private String end;
    @Element(required=false)
    private String deviceid;
    @Element(required=false)
    private String simserial;
    @Element(required=false)
    private String phonenumber;
    @Element(required=false)
    private String imei;
    @Element(required=false)
    private Date today;
    @Attribute(required = false)
    private String version;

    public Date getSeaVdate() {
        return seaVdate;
    }

    public Date getSeaBirthdate() {
        return seaBirthdate;
    }

    public String getSeaPregnantBefore() {
        return seaPregnantBefore;
    }

    public String getSeaPregStillDeliAfter() {
        return seaPregStillDeliAfter;
    }

    public String getSeaCity() {
        return seaCity;
    }

    public String getSeaState() {
        return seaState;
    }

    public String getSeaCountry() {
        return seaCountry;
    }

    public String getSeaLive() {
        return seaLive;
    }

    public Integer getSeaAgeLeave() {
        return seaAgeLeave;
    }

    public String getSeaLeavena() {
        return seaLeavena;
    }

    public String getSeaMstatus() {
        return seaMstatus;
    }

    public String getSeaRace() {
        return seaRace;
    }

    public String getSeaEthnicityOther() {
        return seaEthnicityOther;
    }

    public String getSeaDegreeYou() {
        return seaDegreeYou;
    }

    public Float getSeaYdegreeYears() {
        return seaYdegreeYears;
    }

    public String getSeaDegreeSpouse() {
        return seaDegreeSpouse;
    }

    public Float getSeaSdegreeYears() {
        return seaSdegreeYears;
    }

    //movil y metadata
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public Meta getMeta() {
        return meta;
    }
    public void setMeta(Meta meta) {
        this.meta = meta;
    }
    public String getStart() {
        return start;
    }
    public void setStart(String start) {
        this.start = start;
    }
    public String getEnd() {
        return end;
    }
    public void setEnd(String end) {
        this.end = end;
    }
    public String getDeviceid() {
        return deviceid;
    }
    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }
    public String getSimserial() {
        return simserial;
    }
    public void setSimserial(String simserial) {
        this.simserial = simserial;
    }
    public String getPhonenumber() {
        return phonenumber;
    }
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
    public String getImei() {
        return imei;
    }
    public void setImei(String imei) {
        this.imei = imei;
    }
    public Date getToday() {
        return today;
    }
    public void setToday(Date today) {
        this.today = today;
    }

    
    
}
