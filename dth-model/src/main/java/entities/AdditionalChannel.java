package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="dth.additionalchannel")
@IdClass(AdditionalChannelId.class)
//Association Override is needed if used @EmbeddedId in place of @IdClass
/*@AssociationOverrides({
	@AssociationOverride(name = "cust", 
		joinColumns = @JoinColumn(name = "customer_no")),
	@AssociationOverride(name = "chan", 
		joinColumns = @JoinColumn(name = "channel_no")) })*/
public class AdditionalChannel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "channel_no")
	private Channel chan;
	
	@Id
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "customer_no")
	@JsonIgnore
	private Customer cust;
	
	@Column(name="fromdate")
	private Date fromDate;
	
	@Column(name="tilldate")
	private Date tillDate;

	public Channel getChan() {
		return chan;
	}

	public void setChan(Channel chan) {
		this.chan = chan;
	}

	public Customer getCust() {
		return cust;
	}

	public void setCust(Customer cust) {
		this.cust = cust;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getTillDate() {
		return tillDate;
	}

	public void setTillDate(Date tillDate) {
		this.tillDate = tillDate;
	}
}
