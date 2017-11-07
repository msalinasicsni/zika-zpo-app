package ni.org.ics.zpo.appmovil.parsers;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.util.Date;

/**
 * Created by FIRSTICT on 10/6/2016.
 * V1.0
 */

public class Zpo00ScreeningXml {

    /**
	 * 
	 */
    @Element(required=true)
    private String scrCs;
    @Element(required=true)
    private Date scrVisitDate;
    @Element(required=true)
    private String scrConsentObta;
    @Element(required=false)
    private String scrObDobDay;
    @Element(required=false)
    private String scrObDobMon;
    @Element(required=false)
    private Integer scrObDobYear;
    @Element(required=false)
    private Integer scrObAge;
    @Element(required=false)
    private String scrObAssent;
    @Element(required=false)
    private String scrConsentA; //envio muestras a EEUU
    @Element(required=false)
    private String scrConsentB; //USO FUTORO DE MUESTRAS BIOLOGICAS
    @Element(required=false)
    private String scrConsentC; //ESTUDIOS GENETICOS
    @Element(required=false)
    private String scrName1Tutor;
    @Element(required=false)
    private String scrName2Tutor;
    @Element(required=false)
    private String scrLastName1Tutor;
    @Element(required=false)
    private String scrLastName2Tutor;
    @Element(required=false)
    private String scrFamilyRelationship;
    @Element(required=false)
    private String scrFamilyRelOther;
    @Element(required=false)
    private String scrIlliterate;
    @Element(required=false)
    private String scrName1Witness;
    @Element(required=false)
    private String scrName2Witness;
    @Element(required=false)
    private String scrLastName1Witness;
    @Element(required=false)
    private String scrLastName2Witness;
    @Element(required=false)
    private String scrReasonNot;
    @Element(required=false)
    private String scrReasonOther;

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
    private String question1;
	@Element(required=false)
    private String question2;

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
	
	
	//Getters

    public String getScrCs() {
        return scrCs;
    }

    public Date getScrVisitDate() {
        return scrVisitDate;
    }

    public String getScrConsentObta() {
        return scrConsentObta;
    }

    public String getScrObDobDay() {
        return scrObDobDay;
    }

    public String getScrObDobMon() {
        return scrObDobMon;
    }

    public Integer getScrObDobYear() {
        return scrObDobYear;
    }

    public Integer getScrObAge() {
        return scrObAge;
    }

    public String getScrObAssent() {
        return scrObAssent;
    }

    public String getScrConsentA() {
        return scrConsentA;
    }

    public String getScrConsentB() {
        return scrConsentB;
    }

    public String getScrConsentC() {
        return scrConsentC;
    }

    public String getScrName1Tutor() {
        return scrName1Tutor;
    }

    public String getScrName2Tutor() {
        return scrName2Tutor;
    }

    public String getScrLastName1Tutor() {
        return scrLastName1Tutor;
    }

    public String getScrLastName2Tutor() {
        return scrLastName2Tutor;
    }

    public String getScrFamilyRelationship() {
        return scrFamilyRelationship;
    }

    public String getScrFamilyRelOther() {
        return scrFamilyRelOther;
    }

    public String getScrIlliterate() {
        return scrIlliterate;
    }

    public String getScrName1Witness() {
        return scrName1Witness;
    }

    public String getScrName2Witness() {
        return scrName2Witness;
    }

    public String getScrLastName1Witness() {
        return scrLastName1Witness;
    }

    public String getScrLastName2Witness() {
        return scrLastName2Witness;
    }

    public String getScrReasonNot() {
        return scrReasonNot;
    }

    public String getScrReasonOther() {
        return scrReasonOther;
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
