package org.jpa.test.model;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

/**
 * User: ban
 * Date: 16. 2. 2015
 * Time: 15:39
 */
public class Phone {
	@Id
	private String id;

	@ManyToMany(mappedBy = "contactInfo.phones")
	private List<Employee> employees;

	private String type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}




