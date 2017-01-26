package entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.UniqueConstraint;

@Entity(name="dth.creditcardpayment")
/*@DiscriminatorValue("CC")*/
@PrimaryKeyJoinColumn(name="creditcardpaymentid", referencedColumnName="paymentid")
public class CreditCardPayment extends Payment {
	private static final long serialVersionUID = 1L;
	
	@Column(name="creditcardno", length=20)
	private String creditCardNo;
	@Column(name="expirymonth", length=15)
	private String expiryMonth;
	@Column(name="expiryyear")
	private Integer expiryYear;
	
	public String getCreditCardNo() {
		return creditCardNo;
	}
	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}
	public String getExpiryMonth() {
		return expiryMonth;
	}
	public void setExpiryMonth(String expiryMonth) {
		this.expiryMonth = expiryMonth;
	}
	public Integer getExpiryYear() {
		return expiryYear;
	}
	public void setExpiryYear(Integer expiryYear) {
		this.expiryYear = expiryYear;
	}	
}
