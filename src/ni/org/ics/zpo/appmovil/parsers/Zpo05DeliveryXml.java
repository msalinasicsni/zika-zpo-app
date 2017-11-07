package ni.org.ics.zpo.appmovil.parsers;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.util.Date;

/**
 * Created by FIRSTICT on 10/28/2016.
 * V1.0
 */
public class Zpo05DeliveryXml {

    @Element(required=false)
    private Date deliVisitDate;
    @Element(required=false)
    private String deliOriginInfo;
    @Element(required=false)
    private Float deliMotherWt;
    @Element(required=false)
    private String deliWtUnit;
    @Element(required=false)
    private Integer deliSystolic;
    @Element(required=false)
    private Integer deliDiastolic;
    @Element(required=false)
    private Float deliTemperature;
    @Element(required=false)
    private String deliTempUnit;
    @Element(required=false)
    private Date deliDeliveryDate;
    @Element(required=false)
    private String deliMode;
    @Element(required=false)
    private String deliDeliveryWho;
    @Element(required=false)
    private String deliDeliveryOccur;
    @Element(required=false)
    private String deliHospitalId;
    @Element(required=false)
    private String deliClinicId;
    @Element(required=false)
    private String deliDeliveryOther;
    @Element(required=false)
    private String deliNumBirth;
    @Element(required=false)
    private String deliFetalOutcome1;
    @Element(required=false)
    private String deliCauseDeath1;
    @Element(required=false)
    private String deliSexBaby1;
    @Element(required=false)
    private String deliFetalOutcome2;
    @Element(required=false)
    private String deliCauseDeath2;
    @Element(required=false)
    private String deliSexBaby2;
    @Element(required=false)
    private String deliFetalOutcome3;
    @Element(required=false)
    private String deliCauseDeath3;
    @Element(required=false)
    private String deliSexBaby3;
    @Element(required=false)
    private String deliConsentInfant;
    @Element(required=false)
    private String deliReasonNoconsent;
    @Element(required=false)
    private String deliNoconsentOther;
    @Element(required=false)
    private String deliIdCompleting;
    @Element(required=false)
    private Date deliDateCompleted;
    @Element(required=false)
    private String deliIdReviewer;
    @Element(required=false)
    private Date deliDateReviewed;
    @Element(required=false)
    private String deliIdDataEntry;
    @Element(required=false)
    private Date deliDateEntered;
    @Element(required=false)
    private String deliHyperDisease;
    @Element(required=false)
    private String deliPreterm1;
    @Element(required=false)
    private String deliPreterm2;
    @Element(required=false)
    private String deliPreterm3;
    @Element(required=false)
    private String deliDeliverEarly;

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
    @Element(required=false)
    private String group6;
    @Element(required=false)
    private String group7;
    @Element(required=false)
    private String group8;
    @Element(required=false)
    private String group9;
    @Element(required=false)
    private String group10;
    @Element(required=false)
    private String group11;

    @Element(required=false)
    private String note1;
    @Element(required=false)
    private String note2;

    @Attribute
    private String id;
    @Attribute(required = false)
    private String version;
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

    public Date getDeliVisitDate() {
        return deliVisitDate;
    }

    public String getDeliOriginInfo() {
        return deliOriginInfo;
    }

    public Float getDeliMotherWt() {
        return deliMotherWt;
    }

    public String getDeliWtUnit() {
        return deliWtUnit;
    }

    public Integer getDeliSystolic() {
        return deliSystolic;
    }

    public Integer getDeliDiastolic() {
        return deliDiastolic;
    }

    public Float getDeliTemperature() {
        return deliTemperature;
    }

    public String getDeliTempUnit() {
        return deliTempUnit;
    }

    public Date getDeliDeliveryDate() {
        return deliDeliveryDate;
    }

    public String getDeliMode() {
        return deliMode;
    }

    public String getDeliDeliveryWho() {
        return deliDeliveryWho;
    }

    public String getDeliDeliveryOccur() {
        return deliDeliveryOccur;
    }

    public String getDeliHospitalId() {
        return deliHospitalId;
    }

    public String getDeliClinicId() {
        return deliClinicId;
    }

    public String getDeliDeliveryOther() {
        return deliDeliveryOther;
    }

    public String getDeliNumBirth() {
        return deliNumBirth;
    }

    public String getDeliFetalOutcome1() {
        return deliFetalOutcome1;
    }

    public String getDeliCauseDeath1() {
        return deliCauseDeath1;
    }

    public String getDeliSexBaby1() {
        return deliSexBaby1;
    }

    public String getDeliFetalOutcome2() {
        return deliFetalOutcome2;
    }

    public String getDeliCauseDeath2() {
        return deliCauseDeath2;
    }

    public String getDeliSexBaby2() {
        return deliSexBaby2;
    }

    public String getDeliFetalOutcome3() {
        return deliFetalOutcome3;
    }

    public String getDeliCauseDeath3() {
        return deliCauseDeath3;
    }

    public String getDeliSexBaby3() {
        return deliSexBaby3;
    }

    public String getDeliConsentInfant() {
        return deliConsentInfant;
    }

    public String getDeliReasonNoconsent() {
        return deliReasonNoconsent;
    }

    public String getDeliNoconsentOther() {
        return deliNoconsentOther;
    }

    public String getDeliIdCompleting() {
        return deliIdCompleting;
    }

    public Date getDeliDateCompleted() {
        return deliDateCompleted;
    }

    public String getDeliIdReviewer() {
        return deliIdReviewer;
    }

    public Date getDeliDateReviewed() {
        return deliDateReviewed;
    }

    public String getDeliIdDataEntry() {
        return deliIdDataEntry;
    }

    public Date getDeliDateEntered() {
        return deliDateEntered;
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

	public String getDeliPreterm1() {
		return deliPreterm1;
	}

	public String getDeliPreterm2() {
		return deliPreterm2;
	}

	public String getDeliPreterm3() {
		return deliPreterm3;
	}

	public void setDeliPreterm1(String deliPreterm1) {
		this.deliPreterm1 = deliPreterm1;
	}

	public void setDeliPreterm2(String deliPreterm2) {
		this.deliPreterm2 = deliPreterm2;
	}

	public void setDeliPreterm3(String deliPreterm3) {
		this.deliPreterm3 = deliPreterm3;
	}

	public String getDeliHyperDisease() {
		return deliHyperDisease;
	}

	public String getDeliDeliverEarly() {
		return deliDeliverEarly;
	}

	public void setDeliHyperDisease(String deliHyperDisease) {
		this.deliHyperDisease = deliHyperDisease;
	}

	public void setDeliDeliverEarly(String deliDeliverEarly) {
		this.deliDeliverEarly = deliDeliverEarly;
	}
    
    
}
