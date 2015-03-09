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

import javax.persistence.*;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * User: ban
 * Date: 16.2.2015
 * Time: 11:03
 */
@RunWith(Arquillian.class)
@Transactional(TransactionMode.DEFAULT)
public class JPATestIT {

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
	public void testAddress() {

		Address address = new Address();
		address.setCity("New York");
//		address.setId((long)1);
//		address.setVersion(1);
		em.persist(address);

		TypedQuery<Address> addresses = em.createQuery("SELECT a FROM Address a", Address.class);
		List<Address> addressList = addresses.getResultList();
		addressList.forEach(add -> System.out.println("City: " + add.getCity()));

	}

	@Test
	public void idClass() {


		Employee emp = new Employee();
		emp.setCountry("country");
		emp.setId(1);
		em.persist(emp);
		EmployeeId id = new EmployeeId("country", 1);
		Employee findedEmp = em.find(Employee.class, id);



		assertEquals(emp.getCountry(), findedEmp.getCountry());

	}

	@Test
	public void embeddedIdClass() {
		Employee2 emp = new Employee2(new EmployeeIdEmbeddable("US", (long) 1), "country");
		em.persist(emp);

		Employee2 findedEmp = em.find(Employee2.class, new EmployeeIdEmbeddable("US", (long) 1));
		assertEquals(emp.getName(), findedEmp.getName());

		Employee2 emp3 = (Employee2) em.createQuery("SELECT e2 FROM Employee2 e2 where e2.id.id = :id and e2.id.country = :country")
				.setParameter("country", emp.getPk().getCountry())
				.setParameter("id", emp.getPk().getId()).getSingleResult();

		assertEquals(emp.getName(), emp3.getName());
	}

	@Test
	public void sharedPK() {
		Employee2 emp = new Employee2(new EmployeeIdEmbeddable("US", (long) 1), "country");
		em.persist(emp);
		EmployeeHistory history = new EmployeeHistory();
		history.setEmployee(emp);
		em.persist(history);

		EmployeeHistory findedEmpHistory = em.find(EmployeeHistory.class, new EmployeeIdEmbeddable("US", (long) 1));


		assertEquals(emp.getPk().getCountry(), findedEmpHistory.getEmployee().getPk().getCountry());
	}


	@Test
	public void usingEmbeddedId() {
		Department dept = new Department();
		DeptId deptId = new DeptId(1, "US");
		dept.setId(deptId);
		em.persist(dept);
		Project project = new Project();
		project.setDepartment(dept);
		project.setId(new ProjectId("CITy",deptId));
		em.persist(project);


	}

	/**
	 * manager has employess an each employee has one manager
	 */
	@Test
	public void selfReferencedPrimaryKey(){
		EmployeeManager manager = new EmployeeManager();
		manager.setId(1);
		manager.setCountry("sk");
		EmployeeManager emp1 = new EmployeeManager();
		emp1.setCountry("swe");
		emp1.setId(2);
		EmployeeManager emp2 = new EmployeeManager();
		emp2.setCountry("de");
		emp1.setId(3);
		emp1.setManager(manager);
		manager.getEmployees().add(emp1);
		em.persist(manager);
		em.persist(emp1);

		EmployeeManager fetchedManager = em.find(EmployeeManager.class, new EmployeeId("sk",1));

		assertEquals(1, fetchedManager.getEmployees().size());


	}

	@Test
	public void multipleTablesInOneEntity(){
		 Customer customer = new Customer();

		customer.setName("customer");
		customer.setNumber("2");
		customer.setStreet("street");

		em.persist(customer);

		Customer findedCustomer = em.find(Customer.class, (long)1);
		assertEquals(customer.getName(), findedCustomer.getName());
	}

	@Test
	public void employeeMultiSubtables(){
		EmployeeMultiTable emp = new EmployeeMultiTable();
		emp.setId(1);
		emp.setCountry("US");
		emp.setComments("comments".toCharArray());
		emp.setLob("lob".getBytes());

		em.persist(emp);

		EmployeeMultiTable fetchedEmp = em.find(EmployeeMultiTable.class, new EmployeeId("US",1 ));

		assertEquals(emp.getCountry(), fetchedEmp.getCountry());
		assertEquals(emp.getComments(), fetchedEmp.getComments());

	}

}
