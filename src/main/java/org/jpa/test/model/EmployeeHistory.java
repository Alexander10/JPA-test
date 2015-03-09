package org.jpa.test.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: ban
 * Date: 17. 2. 2015
 * Time: 14:50
 */

@Entity
public class EmployeeHistory implements Serializable {


	@Id
	@OneToOne
	@JoinColumns({
		@JoinColumn(name = "EMP_ID"),
		@JoinColumn(name = "COUNTRY")
	})
	private Employee2 employee;



	public Employee2 getEmployee() {
		return employee;
	}

	public void setEmployee(Employee2 employee) {
		this.employee = employee;
	}
}
