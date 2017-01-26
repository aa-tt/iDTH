package daos;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.AdditionalChannel;
import entities.Channel;
import entities.Customer;
import entities.Packag;

@Repository
public class ChannelDAO {
	
	//@Autowired EntityManagerFactory emf;
	@PersistenceContext EntityManager em; // use EM from PersitenceContext, instead of injecting EMF and get EM from it. Using this way, it requires a spring transactionManager(jpaTransactionManager in config.xml in this case) and @EnableTransactionManagement
	
	@Transactional(rollbackFor=Exception.class)
	public void createPackage(Packag packag) {
		
		Set<Channel> channels = new HashSet<Channel>();
		channels.add(new Channel(packag));
		channels.add(new Channel(packag));
		channels.add(new Channel(packag));
		packag.setChannels(channels);
		
		em.persist(packag);
		if(em.contains(packag)) {
			System.out.println("packag created, packagId-> " + packag.getPackageId());
			System.out.println("channels created for this package-> ");
			channels.forEach(c -> System.out.println(c.toString()));
		}
	}
	
	@Transactional(rollbackFor=Exception.class)
	/*select * from channel c, additionalchannel ac, customer cu where
    				c.channelno = ac.channel_no and
    				ac.customer_no = cu.customerid
    				group by cu.customerid;*/
	public void noOfAdditionalChannelByEachCustomerReport() {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
		Root<Customer> customer = cq.from(Customer.class);
		cq.select(customer);
		cq.groupBy(customer.get("customerId"));		
		
		
		/*Join<Customer, AdditionalChannel> additionalChannel = customer.join("additionalChannels");
		Join<AdditionalChannel, Channel> channel = additionalChannel.join("chan");*/
		customer.join("additionalChannels")
				.join("chan");
		
		List<Customer> listCustomer = em.createQuery(cq).getResultList();
		
		listCustomer.forEach(lc -> {
			System.out.println("additional channels by Customer id->" + lc.getCustomerId() + " are -->" + lc.getAdditionalChannels().size());
			Set<AdditionalChannel> listAdditionalChannel = lc.getAdditionalChannels();
			listAdditionalChannel.forEach(lac -> {
				System.out.println(lac.getChan().toString());
			});
		});
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void noOfAdditionalChannelByEachCustomer() {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Customer> customer = cq.from(Customer.class);
		cq.select(cb.count(customer));
		cq.groupBy(customer.get("customerId"));		
		
		
		/*Join<Customer, AdditionalChannel> additionalChannel = customer.join("additionalChannels");
		Join<AdditionalChannel, Channel> channel = additionalChannel.join("chan");*/
		customer.join("additionalChannels")
				.join("chan");
		
		List<Long> listOfNoOfAdditionalChannels = em.createQuery(cq).getResultList();
		
		listOfNoOfAdditionalChannels.forEach(lc -> {
			System.out.println(lc.toString());
		});
	}
}
