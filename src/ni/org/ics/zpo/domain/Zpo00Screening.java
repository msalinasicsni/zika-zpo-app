package ni.org.ics.zpo.domain;


import java.util.Date;

/**
 * Created by FIRSTICT on 10/6/2016.
 * V1.0
 */
public class Zpo00Screening extends BaseMetaData{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String recordId;
    private String eventName;
    private Date scrVisitDate;
    private String scrConsentObta;
    private String scrObDobDay;
    private String scrObDobMon;
    private Integer scrObDobYear;
    private Integer scrObAge;
    private String scrObAssent;
    private String scrConsentA; //envio muestras a EEUU
    private String scrConsentB; //USO FUTORO DE MUESTRAS BIOLOGICAS
    private String scrConsentC; //ESTUDIOS GENETICOS
    private String scrName1Tutor;
    private String scrName2Tutor;
    private String scrLastName1Tutor;
    private String scrLastName2Tutor;
    private String scrFamilyRelationship;
    private String scrFamilyRelOther;
    private String scrIlliterate;
    private String scrName1Witness;
    private String scrName2Witness;
    private String scrLastName1Witness;
    private String scrLastName2Witness;
    private String scrReasonNot;
    private String scrReasonOther;
    private String scrCs;

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

    public Date getScrVisitDate() {
        return scrVisitDate;
    }

    public void setScrVisitDate(Date scrVisitDate) {
        this.scrVisitDate = scrVisitDate;
    }

    public String getScrConsentObta() {
        return scrConsentObta;
    }

    public void setScrConsentObta(String scrConsentObta) {
        this.scrConsentObta = scrConsentObta;
    }

    public String getScrObDobDay() {
        return scrObDobDay;
    }

    public void setScrObDobDay(String scrObDobDay) {
        this.scrObDobDay = scrObDobDay;
    }

    public String getScrObDobMon() {
        return scrObDobMon;
    }

    public void setScrObDobMon(String scrObDobMon) {
        this.scrObDobMon = scrObDobMon;
    }

    public Integer getScrObDobYear() {
        return scrObDobYear;
    }

    public void setScrObDobYear(Integer scrObDobYear) {
        this.scrObDobYear = scrObDobYear;
    }

    public Integer getScrObAge() {
        return scrObAge;
    }

    public void setScrObAge(Integer scrObAge) {
        this.scrObAge = scrObAge;
    }

    public String getScrObAssent() {
        return scrObAssent;
    }

    public void setScrObAssent(String scrObAssent) {
        this.scrObAssent = scrObAssent;
    }

    public String getScrConsentA() {
        return scrConsentA;
    }

    public void setScrConsentA(String scrConsentA) {
        this.scrConsentA = scrConsentA;
    }

    public String getScrConsentB() {
        return scrConsentB;
    }

    public void setScrConsentB(String scrConsentB) {
        this.scrConsentB = scrConsentB;
    }

    public String getScrConsentC() {
        return scrConsentC;
    }

    public void setScrConsentC(String scrConsentC) {
        this.scrConsentC = scrConsentC;
    }

    public String getScrName1Tutor() {
        return scrName1Tutor;
    }

    public void setScrName1Tutor(String scrName1Tutor) {
        this.scrName1Tutor = scrName1Tutor;
    }

    public String getScrName2Tutor() {
        return scrName2Tutor;
    }

    public void setScrName2Tutor(String scrName2Tutor) {
        this.scrName2Tutor = scrName2Tutor;
    }

    public String getScrLastName1Tutor() {
        return scrLastName1Tutor;
    }

    public void setScrLastName1Tutor(String scrLastName1Tutor) {
        this.scrLastName1Tutor = scrLastName1Tutor;
    }

    public String getScrLastName2Tutor() {
        return scrLastName2Tutor;
    }

    public void setScrLastName2Tutor(String scrLastName2Tutor) {
        this.scrLastName2Tutor = scrLastName2Tutor;
    }

    public String getScrFamilyRelationship() {
        return scrFamilyRelationship;
    }

    public void setScrFamilyRelationship(String scrFamilyRelationship) {
        this.scrFamilyRelationship = scrFamilyRelationship;
    }

    public String getScrFamilyRelOther() {
        return scrFamilyRelOther;
    }

    public void setScrFamilyRelOther(String scrFamilyRelOther) {
        this.scrFamilyRelOther = scrFamilyRelOther;
    }

    public String getScrIlliterate() {
        return scrIlliterate;
    }

    public void setScrIlliterate(String scrIlliterate) {
        this.scrIlliterate = scrIlliterate;
    }

    public String getScrName1Witness() {
        return scrName1Witness;
    }

    public void setScrName1Witness(String scrName1Witness) {
        this.scrName1Witness = scrName1Witness;
    }

    public String getScrName2Witness() {
        return scrName2Witness;
    }

    public void setScrName2Witness(String scrName2Witness) {
        this.scrName2Witness = scrName2Witness;
    }

    public String getScrLastName1Witness() {
        return scrLastName1Witness;
    }

    public void setScrLastName1Witness(String scrLastName1Witness) {
        this.scrLastName1Witness = scrLastName1Witness;
    }

    public String getScrLastName2Witness() {
        return scrLastName2Witness;
    }

    public void setScrLastName2Witness(String scrLastName2Witness) {
        this.scrLastName2Witness = scrLastName2Witness;
    }

    public String getScrReasonNot() {
        return scrReasonNot;
    }

    public void setScrReasonNot(String scrReasonNot) {
        this.scrReasonNot = scrReasonNot;
    }

    public String getScrReasonOther() {
        return scrReasonOther;
    }

    public void setScrReasonOther(String scrReasonOther) {
        this.scrReasonOther = scrReasonOther;
    }

    public String getScrCs() {
        return scrCs;
    }

    public void setScrCs(String scrCs) {
        this.scrCs = scrCs;
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
		if (!(other instanceof Zpo00Screening))
			return false;
		
		Zpo00Screening castOther = (Zpo00Screening) other;

		return (this.getRecordId().equals(castOther.getRecordId()));
	}
}

