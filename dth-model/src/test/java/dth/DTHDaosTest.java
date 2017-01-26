package dth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import daos.CustomerDAO;
import persistence.config.AppPersistenceConfig;

/**
 * @author Josh Long
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppPersistenceConfig.class)
//@SpringApplicationConfiguration(classes = AppPersistenceConfig.class)

public class DTHDaosTest {
	
    @Autowired
    private CustomerDAO customerDAO;

    /*@Before
    public void setup() {
    	ApplicationContext ctx = new AnnotationConfigApplicationContext(AppPersistenceConfig.class);
    	LocalContainerEntityManagerFactoryBean lemf = (LocalContainerEntityManagerFactoryBean)ctx.getBean(LocalContainerEntityManagerFactoryBean.class);
    	EntityManagerFactory emf = lemf.getNativeEntityManagerFactory();
    	EntityManager em = emf.createEntityManager();
    	em.getTransaction().begin();
    	
    	Customer customer = new Customer();
    	Set<Packag> packags = new HashSet<Packag>();
    	Packag packag = new Packag();
    	em.persist(packag);
    	packags.add(packag);
    	customer.setPackags(packags);
    	
    	em.persist(customer);
    	em.getTransaction().commit();
    }*/
    
    @Test
    public void testCustomerDAO_getCustomerById() {
    	
    	System.out.println(customerDAO.getCustomerById(1));
    }
}
