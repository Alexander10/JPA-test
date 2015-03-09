package org.jpa.test.model;

import javax.persistence.*;

/**
 * User: ban
 * Date: 16. 2. 2015
 * Time: 15:43
 */

@Entity
@IdClass(org.jpa.test.model.EmployeeId.class)
@SqlResultSetMapping(
		name ="EmployeWithAddress",
		entities = {
				@EntityResult(entityClass = Employee.class),
				@EntityResult(entityClass = Address.class)
		}
)
public class Employee {

	public Employee() {

	}


	@Id
	private int id;

	@Id
	private String country;

	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
