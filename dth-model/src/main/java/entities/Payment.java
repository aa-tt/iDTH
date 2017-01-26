package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity(name="dth.payment")
@Inheritance(strategy=InheritanceType.JOINED)
//if DiscriminatorColumn used, a column with that name is created; this can be used if table has any such column defined by business
/*@DiscriminatorColumn(name="paymenttype", discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue("CASH")*/ 
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "payment_seq")
	@SequenceGenerator(name = "payment_seq", sequenceName = "payment_seq", initialValue = 1, allocationSize = 1)
	@Id @Column(name="paymentid")
	private Integer paymentId;
	@Column(name="amountpaid", columnDefinition="DECIMAL(10,2)")
	private Float amountPaid;
	@Column(name="paiddate")
	private Date paidDate;
	/*@Column(name="customerno")*/
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="customerid")
	private Customer customer;
	
	public Integer getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}
	public Float getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(Float amountPaid) {
		this.amountPaid = amountPaid;
	}
	public Date getPaidDate() {
		return paidDate;
	}
	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}	
}
