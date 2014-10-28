package uidai.explore.hibernate.nf1.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name= "aadhar_record_per_day")
public class AadharRecordPerDay {
	
	int aadharGenerated;
	int enrollmentRejected;
	int residentsWithEmail;
	int residentsWithMobileNumber;
	
	@EmbeddedId
	AadharRecPerDayKey key;

	
	public int getAadharGenerated() {
		return aadharGenerated;
	}

	public void setAadharGenerated(int aadharGenerated) {
		this.aadharGenerated = aadharGenerated;
	}

	public int getEnrollmentRejected() {
		return enrollmentRejected;
	}

	public void setEnrollmentRejected(int enrollmentRejected) {
		this.enrollmentRejected = enrollmentRejected;
	}

	public int getResidentsWithEmail() {
		return residentsWithEmail;
	}

	public void setResidentsWithEmail(int residentsWithEmail) {
		this.residentsWithEmail = residentsWithEmail;
	}

	public int getResidentsWithMobileNumber() {
		return residentsWithMobileNumber;
	}

	public void setResidentsWithMobileNumber(int residentsWithMobileNumber) {
		this.residentsWithMobileNumber = residentsWithMobileNumber;
	}

	public AadharRecPerDayKey getKey() {
		return key;
	}

	public void setKey(AadharRecPerDayKey key) {
		this.key = key;
	}
		

}
