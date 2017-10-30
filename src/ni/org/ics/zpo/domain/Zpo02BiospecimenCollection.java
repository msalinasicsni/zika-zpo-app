package ni.org.ics.zpo.domain;

import java.util.Date;

/**
 * Created by FIRSTICT on 10/10/2016.
 * V1.0
 */
public class Zpo02BiospecimenCollection extends BaseMetaData {

    private static final long serialVersionUID = 1L;
    private String recordId;
    private String eventName;
    private Date bscDov;
    private String bscVisit;
    private String bscMatBldCol;
    private String bscMatBldTyp1;
    private String bscMatBldId1;
    private Integer bscMatBldVol1;
    private String bscMatBldTime;
    private String bscMatBldCom;
    private String bscPerson1;
    private Date bscCompleteDate1;
    private String bscPerson2;
    private Date bscCompleteDate2;
    private String bscPerson3;
    private Date bscCompleteDate3;

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

    
    public Date getBscDov() {
        return bscDov;
    }

    public void setBscDov(Date bscDov) {
        this.bscDov = bscDov;
    }

    
    public String getBscVisit() {
        return bscVisit;
    }

    public void setBscVisit(String bscVisit) {
        this.bscVisit = bscVisit;
    }

    
    public String getBscMatBldCol() {
        return bscMatBldCol;
    }

    public void setBscMatBldCol(String bscMatBldCol) {
        this.bscMatBldCol = bscMatBldCol;
    }

    
    public String getBscMatBldTyp1() {
        return bscMatBldTyp1;
    }

    public void setBscMatBldTyp1(String bscMatBldTyp1) {
        this.bscMatBldTyp1 = bscMatBldTyp1;
    }

    
    public String getBscMatBldId1() {
        return bscMatBldId1;
    }

    public void setBscMatBldId1(String bscMatBldId1) {
        this.bscMatBldId1 = bscMatBldId1;
    }

    
    public Integer getBscMatBldVol1() {
        return bscMatBldVol1;
    }

    public void setBscMatBldVol1(Integer bscMatBldVol1) {
        this.bscMatBldVol1 = bscMatBldVol1;
    }

    
    public String getBscMatBldTime() {
        return bscMatBldTime;
    }

    public void setBscMatBldTime(String bscMatBldTime) {
        this.bscMatBldTime = bscMatBldTime;
    }

    
    public String getBscMatBldCom() {
        return bscMatBldCom;
    }

    public void setBscMatBldCom(String bscMatBldCom) {
        this.bscMatBldCom = bscMatBldCom;
    }

    
    public String getBscPerson1() {
        return bscPerson1;
    }

    public void setBscPerson1(String bscPerson1) {
        this.bscPerson1 = bscPerson1;
    }

    
    public Date getBscCompleteDate1() {
        return bscCompleteDate1;
    }

    public void setBscCompleteDate1(Date bscCompleteDate1) {
        this.bscCompleteDate1 = bscCompleteDate1;
    }

    
    public String getBscPerson2() {
        return bscPerson2;
    }

    public void setBscPerson2(String bscPerson2) {
        this.bscPerson2 = bscPerson2;
    }

    
    public Date getBscCompleteDate2() {
        return bscCompleteDate2;
    }

    public void setBscCompleteDate2(Date bscCompleteDate2) {
        this.bscCompleteDate2 = bscCompleteDate2;
    }

    
    public String getBscPerson3() {
        return bscPerson3;
    }

    public void setBscPerson3(String bscPerson3) {
        this.bscPerson3 = bscPerson3;
    }

    
    public Date getBscCompleteDate3() {
        return bscCompleteDate3;
    }

    public void setBscCompleteDate3(Date bscCompleteDate3) {
        this.bscCompleteDate3 = bscCompleteDate3;
    }


    @Override
    public String toString() {
        return recordId + "," + eventName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Zpo02BiospecimenCollection)) return false;

        Zpo02BiospecimenCollection that = (Zpo02BiospecimenCollection) o;

        if (!recordId.equals(that.recordId) ) return false;
        if (!eventName.equals(that.eventName)) return false;

        return true;
    }
}
