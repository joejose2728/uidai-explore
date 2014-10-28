package uidai.explore.hibernate.nf1.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "agency_details")
public class AgencyDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="ag_id", updatable=false, nullable=false)
	long agId;
	String registrar;
	String enrollmentAgency;
	int pincode;
	
	@OneToMany
	@JoinColumn(name="ag_id")
	List<AadharRecordPerDay> aadharrecords = new ArrayList<AadharRecordPerDay>();
	
		
	public long getAgId() {
		return agId;
	}
	public void setAgId(long agId) {
		this.agId = agId;
	}
	public String getRegistrar() {
		return registrar;
	}
	public void setRegistrar(String registrar) {
		this.registrar = registrar;
	}
	public String getEnrollmentAgency() {
		return enrollmentAgency;
	}
	public void setEnrollmentAgency(String enrollmentAgency) {
		this.enrollmentAgency = enrollmentAgency;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public List<AadharRecordPerDay> getAadharrecords() {
		return aadharrecords;
	}
	public void setAadharrecords(List<AadharRecordPerDay> aadharrecords) {
		this.aadharrecords = aadharrecords;
	}
	
	public void addAadharRecord(AadharRecordPerDay record){
		aadharrecords.add(record);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((enrollmentAgency == null) ? 0 : enrollmentAgency.hashCode());
		result = prime * result + pincode;
		result = prime * result
				+ ((registrar == null) ? 0 : registrar.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AgencyDetails other = (AgencyDetails) obj;
		if (enrollmentAgency == null) {
			if (other.enrollmentAgency != null)
				return false;
		} else if (!enrollmentAgency.equals(other.enrollmentAgency))
			return false;
		if (pincode != other.pincode)
			return false;
		if (registrar == null) {
			if (other.registrar != null)
				return false;
		} else if (!registrar.equals(other.registrar))
			return false;
		return true;
	}
	
}
