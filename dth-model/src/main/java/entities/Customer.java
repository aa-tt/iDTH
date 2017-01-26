package entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity(name="dth.customer")
//@JsonAutoDetect
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "customer_seq")
	@SequenceGenerator(name = "customer_seq", sequenceName = "customer_seq", initialValue = 1, allocationSize = 1)
	@Id @Column(name="customerid")
	//@JsonProperty
	private Integer customerId;
	@Column(name="customername", length=20)
	//@JsonProperty
	private String customerName;	
	@Embedded
	//@JsonIgnore
	private Address address;	
	@Column(name="contactno", length=15)
	//@JsonProperty
	private String contactNo;
	@Column(name="emailid", length=20)	//@JsonProperty
	private String emailId;
	
	@JoinTable(name = "dth.subscription", 
            joinColumns = {@JoinColumn(name = "customer_id", referencedColumnName="customerid")}, 
            inverseJoinColumns = {@JoinColumn(name = "package_id", referencedColumnName="packageid")}
    ) /*join table is specified on owning side; here Customer has Packags registered, so Customer is owning and Packag is non-owning or inverse*/
    @ManyToMany(targetEntity=entities.Packag.class, fetch = FetchType.EAGER)
	private Set<Packag> packags;
	
	@OneToMany(fetch = FetchType.EAGER,
			targetEntity=entities.AdditionalChannel.class, 
			mappedBy = "cust", 
			cascade=CascadeType.ALL)
	private Set<AdditionalChannel> additionalChannels;
	
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public Set<Packag> getPackags() {
		return packags;
	}
	public void setPackags(Set<Packag> packags) {
		this.packags = packags;
	}
	public Set<AdditionalChannel> getAdditionalChannels() {
		return additionalChannels;
	}
	public void setAdditionalChannels(Set<AdditionalChannel> additionalChannels) {
		this.additionalChannels = additionalChannels;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
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
		Customer other = (Customer) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + "]";
	}
	
}
