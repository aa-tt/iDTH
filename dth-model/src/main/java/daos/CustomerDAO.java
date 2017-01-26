package daos;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.AdditionalChannel;
import entities.Channel;
import entities.Customer;
import entities.Packag;

@Repository
public class CustomerDAO {
	
	@PersistenceContext EntityManager em;
	
	@Transactional(rollbackFor=Exception.class)
	public void registerCustomer(Customer customer) {
		Set<Packag> packags = new HashSet<Packag>();
		Packag packag = em.find(Packag.class, 2);
		
		packags.add(packag);
		customer.setPackags(packags);
		em.persist(customer);
		if(em.contains(customer)) System.out.println("customer created, customerId-> " + customer.getCustomerId());
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void addChannel(AdditionalChannel additionalChannel) {
		
		Customer customer = em.find(Customer.class, 3);
		
		Channel channel = em.find(Channel.class, 7);
		additionalChannel.setCust(customer);
		additionalChannel.setChan(channel);
		
		em.persist(additionalChannel);
		if(em.contains(additionalChannel)) {
			System.out.println("channels added");
		}
	}
	
	@Transactional(rollbackFor=Exception.class)
	public Customer getCustomerById(Integer customerId) {
		return em.find(Customer.class, customerId);
	}

}
