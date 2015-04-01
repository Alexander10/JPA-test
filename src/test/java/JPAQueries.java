import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jpa.test.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

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
				.addClass(Project.class)
				.addClass(ProjectId.class)
				.addClass(Department.class)
				.addClass(Address.class)
				.addClass(Customer.class)
				.addClass(DeptId.class);


		//.addAsResource(EmptyAsset.INSTANCE, "beans.xml");
		System.out.println(archive.toString(true));
		return archive;
	}

	@Test
	public void multipleResultMappings() {
		String nativeQuery = "SELECT e.id, e.country, a.id, a.version, a.city, a.zip" +
				"FROM employee e, address a";
	}

	@Test
	public void simpleCriteriaQuery() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Project> query = builder.createQuery(Project.class);
		Root<Project> c = query.from(Project.class);
		query.select(c);
		assertEquals(em.createQuery(query).getResultList().size(), 3);

		CriteriaBuilder builder1 = em.getCriteriaBuilder();
		CriteriaQuery<Project> query1 = builder1.createQuery(Project.class);
		Root<Project> root = query1.from(Project.class);
		ParameterExpression<String> expr = builder1.parameter(String.class);
		query1.select(root).where(builder.equal(root.get("id").get("name"), "Alfa"));

		assertEquals(em.createQuery(query1).getResultList().size(), 1);

	}

	@Test
	public void multiSelectQuery() {
		CriteriaBuilder builder2 = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> query2 = builder2.createQuery(Object[].class);
		Root<Project> root1 = query2.from(Project.class);
		query2.multiselect(root1.get("id").get("name"), root1.get("id").get("dept"));

		assertEquals(1, em.createQuery(query2).getResultList().stream().filter(obj -> obj[1].equals("Office")).toArray().length);
	}

	@Test
	public void joinQuery() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
		Root<Customer> rootCustomer = query.from(Customer.class);
		Join<Customer, Address> joinAddress = rootCustomer.join("addressList", JoinType.LEFT);
		query.multiselect(rootCustomer.get("street"), joinAddress.get("zip"));
		assertEquals(em.createQuery(query).getResultList().size(), 2);

	}

	@Test
	public void queryNotValid() {
//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		CriteriaQuery cq = cb.createQuery(Customer.class);
//		Root root = cq.from(Customer.class);
//		ParameterExpression expr = cb.parameter(Integer.class, "column");
//		Predicate predicate = cb.ge(root.get("attr"),expr);
//		cq.where(predicate);

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery(Customer.class);
		Root<Customer> root = cq.from(Customer.class);
		Join<Customer, Address> join = root.join("addresses");


	}


	public void whereClause() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Address> query = builder.createQuery(Address.class);
		Root<Address> root = query.from(Address.class);


	}

	@Before
	public void initData() {
		createOneRecord("Alfa", "ZA", 1, "Office");
		createOneRecord("Beta", "BA", 2, "Manager");
		createOneRecord("Gama", "Wien", 3, "Lead");
	}

	@Before
	public void initData2() {
		Address address1 = new Address();
		address1.setCity("NO");
		address1.setZip("zip");

		Address address2 = new Address();
		address2.setCity("BA");
		address2.setZip("address");

		Customer customer = new Customer();
		customer.setName("Customer 1");
		customer.setNumber("1");
		customer.setStreet("Street1");
		customer.setAddressList(new ArrayList<>(Arrays.asList(address1, address2)));
		em.persist(address1);
		em.persist(address2);
		em.persist(customer);
	}

	private void createOneRecord(String projectName, String deptCity, int deptId, String department) {
		Project project1 = new Project();

		ProjectId id1 = new ProjectId();
		id1.setName(projectName);
		id1.setDept(department);
		project1.setId(id1);

		DeptId deptId1 = new DeptId();
		deptId1.setCity(deptCity);
		deptId1.setNumber(deptId);

		Department department1 = new Department();
		department1.setId(deptId1);

		department1.setProjects(new ArrayList<>(Arrays.asList(project1)));
		project1.setDepartment(department1);

		em.persist(project1);
		em.persist(department1);


	}
}
