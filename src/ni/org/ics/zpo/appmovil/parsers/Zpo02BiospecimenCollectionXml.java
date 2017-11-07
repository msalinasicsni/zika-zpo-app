package ni.org.ics.zpo.appmovil.parsers;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.util.Date;

/**
 * Created by FIRSTICT on 10/10/2016.
 * V1.0
 */
public class Zpo02BiospecimenCollectionXml {

    @Element(required=false)
    private Date bscDov;
    @Element(required=false)
    private String bscVisit;
    @Element(required=false)
    private String bscMatBldCol;
    @Element(required=false)
    private String bscMatBldTyp1;
    @Element(required=false)
    private String bscMatBldId1;
    @Element(required=false)
    private Double bscMatBldVol1;
    @Element(required=false)
    private String bscMatBldTime;
    @Element(required=false)
    private String bscMatBldCom;
    @Element(required=false)
    private String bscPerson1;
    @Element(required=false)
    private Date bscCompleteDate1;
    @Element(required=false)
    private String bscPerson2;
    @Element(required=false)
    private Date bscCompleteDate2;
    @Element(required=false)
    private String bscPerson3;
    @Element(required=false)
    private Date bscCompleteDate3;

    @Element(required=false)
    private String group1;
    @Element(required=false)
    private String group2;
    @Element(required=false)
    private String group3;

    @Element(required=false)
    private String note1;
    @Element(required=false)
    private String question1;
    @Element(required=false)
    private String text1;
    @Element(required=false)
    private String barcode1;


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

    public Date getBscDov() {
        return bscDov;
    }

    public String getBscVisit() {
        return bscVisit;
    }

    public String getBscMatBldCol() {
        return bscMatBldCol;
    }

    public String getBscMatBldTyp1() {
        return bscMatBldTyp1;
    }

    public String getBscMatBldId1() {
        return bscMatBldId1;
    }

    public Double getBscMatBldVol1() {
        return bscMatBldVol1;
    }

    public String getBscMatBldTime() {
        return bscMatBldTime;
    }

    public String getBscMatBldCom() {
        return bscMatBldCom;
    }

    public String getBscPerson1() {
        return bscPerson1;
    }

    public Date getBscCompleteDate1() {
        return bscCompleteDate1;
    }

    public String getBscPerson2() {
        return bscPerson2;
    }

    public Date getBscCompleteDate2() {
        return bscCompleteDate2;
    }

    public String getBscPerson3() {
        return bscPerson3;
    }

    public Date getBscCompleteDate3() {
        return bscCompleteDate3;
    }

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
