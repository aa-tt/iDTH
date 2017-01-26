package entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="dth.package")
/*@NamedQueries({
	@NamedQuery(
	    name="Policy.getPoliciesGreaterThanGivenTerm",
	    query="SELECT policy FROM insurance.policy_master policy WHERE policy.policyTerm >= :policyterm order by policy.policyTerm"
	),
	@NamedQuery(
	    name="Policy.getPoliciesWithMaxSumAssured",
	    query="SELECT policy FROM insurance.policy_master policy order by policy.sumAssured desc"
	)
})*/
public class Packag implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "package_seq")
	@SequenceGenerator(name = "package_seq", sequenceName = "package_seq", initialValue = 1, allocationSize = 1) // if you do not provide the [sequencename], this will conflict with [hibernate_sequence]
	@Id @Column(name="packageid")
	private Integer packageId;
	@Column(name="packagename", length=20)
	private String packageName;
	@OneToMany(fetch=FetchType.EAGER, mappedBy="packag", cascade={CascadeType.PERSIST,  CascadeType.MERGE})
	private Set<Channel> channels;
	
	@ManyToMany(targetEntity=Customer.class, mappedBy="packags")
	@JsonIgnore
	private Set<Customer> customers;
	
	public Integer getPackageId() {
		return packageId;
	}
	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public Set<Channel> getChannels() {
		return channels;
	}
	public void setChannels(Set<Channel> channels) {
		this.channels = channels;
	}
	
	public Set<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((packageId == null) ? 0 : packageId.hashCode());
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
		Packag other = (Packag) obj;
		if (packageId == null) {
			if (other.packageId != null)
				return false;
		} else if (!packageId.equals(other.packageId))
			return false;
		return true;
	}	
}
