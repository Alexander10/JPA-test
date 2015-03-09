import org.jpa.test.model.Address;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * User: ban
 * Date: 16. 2. 2015
 * Time: 9:30
 */

public class BasicTest {

	EntityManager em;

	@Before
	public void setup(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
		em = factory.createEntityManager();

	}

	@Test
	public void persistData(){
		em.getTransaction().begin();
		Address address = new Address();
		address.setCity("New York");
//		address.setId((long)1);
//		address.setVersion(1);
		em.persist(address);

		TypedQuery<Address> addresses = em.createQuery("SELECT a FROM Address a", Address.class);
		List<Address> addressList = addresses.getResultList();
		addressList.forEach(System.out::println);
		em.getTransaction().commit();
	}
}
