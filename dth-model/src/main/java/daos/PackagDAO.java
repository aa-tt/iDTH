package daos;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.Channel;
import entities.Packag;

@Repository
public class PackagDAO {
	
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
}
