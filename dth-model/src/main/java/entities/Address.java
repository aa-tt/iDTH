package entities;

import java.io.Serializable;
import java.util.Random;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Access(AccessType.PROPERTY)
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String doorNo;
	private String street;
	private String city;
	private String state;
	private String pincode;
	
	public Address() {
		this("door" + String.valueOf(new Random().nextInt(100)), 
				"street" + String.valueOf(new Random().nextInt(100)),
						"city" + String.valueOf(new Random().nextInt(100)),
								"state" + String.valueOf(new Random().nextInt(196)),
										"pin-" + String.valueOf(new Random().nextInt(100)));
	}	
	public Address(String doorNo, String street, String city, String state, String pincode) {
		this.doorNo = doorNo;
		this.street = street;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}
	@Column(name="doorno", length=10)
	public String getDoorNo() {
		return doorNo;
	}
	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}
	@Column(name="street", length=20)
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	@Column(name="city", length=20)
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Column(name="state", length=20)
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Column(name="pincode", length=10)
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
}
