package uidai.explore.hibernate.nf1.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name ="location_details")
public class LocationDetails {
	
	@Id
	int pincode;
	String state;
	String district;
	String subDistrict;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="pincode")
	List<AgencyDetails> agencyDetails = new ArrayList<AgencyDetails>();
	
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getSubDistrict() {
		return subDistrict;
	}
	public void setSubDistrict(String subDistrict) {
		this.subDistrict = subDistrict;
	}
	public List<AgencyDetails> getAgencyDetails() {
		return agencyDetails;
	}
	public void setAgencyDetails(List<AgencyDetails> agencyDetails) {
		this.agencyDetails = agencyDetails;
	}
	
	public void addAgencyDetail(AgencyDetails agencyDetail){
		agencyDetails.add(agencyDetail);
	}
	
}
