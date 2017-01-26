package daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.Customer;
import entities.Payment;

@Repository
public class PaymentDAO {
	
	@PersistenceContext EntityManager em;
	
	@Transactional(rollbackFor=Exception.class)
	public void makePayment(Payment payment) {
		
		Customer customer = em.find(Customer.class, 1);
		payment.setCustomer(customer);
		
		em.persist(payment);
		if(em.contains(payment)) {
			System.out.println("payment done, paymentId-> " + payment.getPaymentId());
		}
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void makeCreditCardPayment(Payment payment) {
		
		Customer customer = em.find(Customer.class, 1);
		payment.setCustomer(customer);
		
		em.persist(payment);
		if(em.contains(payment)) {
			System.out.println("payment done, paymentId-> " + payment.getPaymentId());
		}
	}
}
