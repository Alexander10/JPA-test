import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jpa.test.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * User: ban
 * Date: 20. 2. 2015
 * Time: 10:39
 */
@RunWith(Arquillian.class)
@Transactional(TransactionMode.DEFAULT)
public class JPAQueries {

	@PersistenceContext(unitName = "jta")
	private EntityManager em;


	@Deployment
	public static JavaArchive setup() {
		JavaArchive archive = ShrinkWrap.create(JavaArchive.class)
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
				.addClass(Address.class)
				.addClass(Employee.class)
				.addClass(EmployeeId.class)
				.addClass(Employee2.class)
				.addClass(EmployeeHistory.class)
				.addClass(Project.class)
				.addClass(ProjectId.class)
				.addClass(Department.class)
				.addClass(DeptId.class)
				.addClass(EmployeeManager.class)
				.addClass(Customer.class)
				.addClass(EmployeeMultiTable.class)
				.addClass(EmployeeIdEmbeddable.class);

		//.addAsResource(EmptyAsset.INSTANCE, "beans.xml");
		System.out.println(archive.toString(true));
		return archive;
	}

	@Test
	public void multipleResultMappings(){
		  String nativeQuery = "SELECT e.id, e.country, a.id, a.version, a.city, a.zip" +
				  "FROM employee e, address a" ;
	}
}
