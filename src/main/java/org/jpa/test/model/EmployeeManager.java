package org.jpa.test.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * User: ban
 * Date: 19. 2. 2015
 * Time: 10:23
 */
@Entity
@IdClass(EmployeeId.class)
public class EmployeeManager {



	@Id
	private String country;

	@Id
	@Column(name = "EMP_ID")
	private int id;

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "CTRY", referencedColumnName = "EMP_ID"),
			@JoinColumn(name = "ID", referencedColumnName = "country")
	})
	private EmployeeManager manager;

	@OneToMany(mappedBy = "manager")
	private Collection<EmployeeManager> employees = new ArrayList<>();


	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EmployeeManager getManager() {
		return manager;
	}

	public void setManager(EmployeeManager manager) {
		this.manager = manager;
	}

	public Collection<EmployeeManager> getEmployees() {
		return employees;
	}

	public void setEmployees(Collection<EmployeeManager> employees) {
		this.employees = employees;
	}
}
