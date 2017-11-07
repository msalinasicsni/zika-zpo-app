package ni.org.ics.zpo.domain;

import java.util.Date;

/**
 * Created by FIRSTICT on 24/10/2017.
 * V1.0
 */
public class Zpo05Delivery extends BaseMetaData{

    private static final long serialVersionUID = 1L;
    private String recordId;
    private String eventName;
    private Date deliVisitDate;
    private String deliOriginInfo;
    private Float deliMotherWt;
    private String deliWtUnit;
    private Integer deliSystolic;
    private Integer deliDiastolic;
    private Float deliTemperature;
    private String deliTempUnit;
    private Date deliDeliveryDate;
    private String deliMode;
    private String deliDeliveryWho;
    private String deliDeliveryOccur;
    private String deliHospitalId;
    private String deliClinicId;
    private String deliDeliveryOther;
    private String deliNumBirth;
    private String deliFetalOutcome1;
    private String deliCauseDeath1;
    private String deliSexBaby1;
    private String deliFetalOutcome2;
    private String deliCauseDeath2;
    private String deliSexBaby2;
    private String deliFetalOutcome3;
    private String deliCauseDeath3;
    private String deliSexBaby3;
    private String deliConsentInfant;
    private String deliReasonNoconsent;
    private String deliNoconsentOther;
    private String deliIdCompleting;
    private Date deliDateCompleted;
    private String deliIdReviewer;
    private Date deliDateReviewed;
    private String deliIdDataEntry;
    private Date deliDateEntered;

    //v2.4
    private String deliHyperDisease;
    private String deliPreterm1;
    private String deliPreterm2;
    private String deliPreterm3;
    private String deliDeliverEarly;

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

    public Date getDeliVisitDate() {
        return deliVisitDate;
    }

    public void setDeliVisitDate(Date deliVisitDate) {
        this.deliVisitDate = deliVisitDate;
    }

    public String getDeliOriginInfo() {
        return deliOriginInfo;
    }

    public void setDeliOriginInfo(String deliOriginInfo) {
        this.deliOriginInfo = deliOriginInfo;
    }

    public Float getDeliMotherWt() {
        return deliMotherWt;
    }

    public void setDeliMotherWt(Float deliMotherWt) {
        this.deliMotherWt = deliMotherWt;
    }

    public String getDeliWtUnit() {
        return deliWtUnit;
    }

    public void setDeliWtUnit(String deliWtUnit) {
        this.deliWtUnit = deliWtUnit;
    }

    public Integer getDeliSystolic() {
        return deliSystolic;
    }

    public void setDeliSystolic(Integer deliSystolic) {
        this.deliSystolic = deliSystolic;
    }

    public Integer getDeliDiastolic() {
        return deliDiastolic;
    }

    public void setDeliDiastolic(Integer deliDiastolic) {
        this.deliDiastolic = deliDiastolic;
    }

    public Float getDeliTemperature() {
        return deliTemperature;
    }

    public void setDeliTemperature(Float deliTemperature) {
        this.deliTemperature = deliTemperature;
    }

    public String getDeliTempUnit() {
        return deliTempUnit;
    }

    public void setDeliTempUnit(String deliTempUnit) {
        this.deliTempUnit = deliTempUnit;
    }

    public Date getDeliDeliveryDate() {
        return deliDeliveryDate;
    }

    public void setDeliDeliveryDate(Date deliDeliveryDate) {
        this.deliDeliveryDate = deliDeliveryDate;
    }

    public String getDeliMode() {
        return deliMode;
    }

    public void setDeliMode(String deliMode) {
        this.deliMode = deliMode;
    }

    public String getDeliDeliveryWho() {
        return deliDeliveryWho;
    }

    public void setDeliDeliveryWho(String deliDeliveryWho) {
        this.deliDeliveryWho = deliDeliveryWho;
    }

    public String getDeliDeliveryOccur() {
        return deliDeliveryOccur;
    }

    public void setDeliDeliveryOccur(String deliDeliveryOccur) {
        this.deliDeliveryOccur = deliDeliveryOccur;
    }

    public String getDeliHospitalId() {
        return deliHospitalId;
    }

    public void setDeliHospitalId(String deliHospitalId) {
        this.deliHospitalId = deliHospitalId;
    }

    public String getDeliClinicId() {
        return deliClinicId;
    }

    public void setDeliClinicId(String deliClinicId) {
        this.deliClinicId = deliClinicId;
    }

    public String getDeliDeliveryOther() {
        return deliDeliveryOther;
    }

    public void setDeliDeliveryOther(String deliDeliveryOther) {
        this.deliDeliveryOther = deliDeliveryOther;
    }

    public String getDeliNumBirth() {
        return deliNumBirth;
    }

    public void setDeliNumBirth(String deliNumBirth) {
        this.deliNumBirth = deliNumBirth;
    }

    public String getDeliFetalOutcome1() {
        return deliFetalOutcome1;
    }

    public void setDeliFetalOutcome1(String deliFetalOutcome1) {
        this.deliFetalOutcome1 = deliFetalOutcome1;
    }

    public String getDeliCauseDeath1() {
        return deliCauseDeath1;
    }

    public void setDeliCauseDeath1(String deliCauseDeath1) {
        this.deliCauseDeath1 = deliCauseDeath1;
    }

    public String getDeliSexBaby1() {
        return deliSexBaby1;
    }

    public void setDeliSexBaby1(String deliSexBaby1) {
        this.deliSexBaby1 = deliSexBaby1;
    }

    public String getDeliFetalOutcome2() {
        return deliFetalOutcome2;
    }

    public void setDeliFetalOutcome2(String deliFetalOutcome2) {
        this.deliFetalOutcome2 = deliFetalOutcome2;
    }

    public String getDeliCauseDeath2() {
        return deliCauseDeath2;
    }

    public void setDeliCauseDeath2(String deliCauseDeath2) {
        this.deliCauseDeath2 = deliCauseDeath2;
    }

    public String getDeliSexBaby2() {
        return deliSexBaby2;
    }

    public void setDeliSexBaby2(String deliSexBaby2) {
        this.deliSexBaby2 = deliSexBaby2;
    }

    public String getDeliFetalOutcome3() {
        return deliFetalOutcome3;
    }

    public void setDeliFetalOutcome3(String deliFetalOutcome3) {
        this.deliFetalOutcome3 = deliFetalOutcome3;
    }

    public String getDeliCauseDeath3() {
        return deliCauseDeath3;
    }

    public void setDeliCauseDeath3(String deliCauseDeath3) {
        this.deliCauseDeath3 = deliCauseDeath3;
    }

    public String getDeliSexBaby3() {
        return deliSexBaby3;
    }

    public void setDeliSexBaby3(String deliSexBaby3) {
        this.deliSexBaby3 = deliSexBaby3;
    }

    public String getDeliConsentInfant() {
        return deliConsentInfant;
    }

    public void setDeliConsentInfant(String deliConsentInfant) {
        this.deliConsentInfant = deliConsentInfant;
    }

    public String getDeliReasonNoconsent() {
        return deliReasonNoconsent;
    }

    public void setDeliReasonNoconsent(String deliReasonNoconsent) {
        this.deliReasonNoconsent = deliReasonNoconsent;
    }

    public String getDeliNoconsentOther() {
        return deliNoconsentOther;
    }

    public void setDeliNoconsentOther(String deliNoconsentOther) {
        this.deliNoconsentOther = deliNoconsentOther;
    }

    public String getDeliIdCompleting() {
        return deliIdCompleting;
    }

    public void setDeliIdCompleting(String deliIdCompleting) {
        this.deliIdCompleting = deliIdCompleting;
    }

    public Date getDeliDateCompleted() {
        return deliDateCompleted;
    }

    public void setDeliDateCompleted(Date deliDateCompleted) {
        this.deliDateCompleted = deliDateCompleted;
    }

    public String getDeliIdReviewer() {
        return deliIdReviewer;
    }

    public void setDeliIdReviewer(String deliIdReviewer) {
        this.deliIdReviewer = deliIdReviewer;
    }

    public Date getDeliDateReviewed() {
        return deliDateReviewed;
    }

    public void setDeliDateReviewed(Date deliDateReviewed) {
        this.deliDateReviewed = deliDateReviewed;
    }

    public String getDeliIdDataEntry() {
        return deliIdDataEntry;
    }

    public void setDeliIdDataEntry(String deliIdDataEntry) {
        this.deliIdDataEntry = deliIdDataEntry;
    }

    public Date getDeliDateEntered() {
        return deliDateEntered;
    }

    public void setDeliDateEntered(Date deliDateEntered) {
        this.deliDateEntered = deliDateEntered;
    }

    public String getDeliHyperDisease() {
        return deliHyperDisease;
    }

    public void setDeliHyperDisease(String deliHyperDisease) {
        this.deliHyperDisease = deliHyperDisease;
    }

    public String getDeliPreterm1() {
        return deliPreterm1;
    }

    public void setDeliPreterm1(String deliPreterm1) {
        this.deliPreterm1 = deliPreterm1;
    }

    public String getDeliPreterm2() {
        return deliPreterm2;
    }

    public void setDeliPreterm2(String deliPreterm2) {
        this.deliPreterm2 = deliPreterm2;
    }

    public String getDeliPreterm3() {
        return deliPreterm3;
    }

    public void setDeliPreterm3(String deliPreterm3) {
        this.deliPreterm3 = deliPreterm3;
    }

    public String getDeliDeliverEarly() {
        return deliDeliverEarly;
    }

    public void setDeliDeliverEarly(String deliDeliverEarly) {
        this.deliDeliverEarly = deliDeliverEarly;
    }

    @Override
    public String toString() {
        return this.recordId;
    }	

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Zpo05Delivery)) return false;

        Zpo05Delivery that = (Zpo05Delivery) o;

        return (recordId.equals(that.recordId));
    }
}
