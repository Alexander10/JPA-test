package org.jpa.test.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: ban
 * Date: 17. 2. 2015
 * Time: 13:22
 */

@Entity
public class Employee2 implements Serializable {

	public Employee2() {
	}

	public Employee2(EmployeeIdEmbeddable pk, String name) {
		this.pk = pk;
		this.name = name;
	}

	@EmbeddedId
	private EmployeeIdEmbeddable pk;

	private String name;

	public EmployeeIdEmbeddable getPk() {
		return pk;
	}

	public void setPk(EmployeeIdEmbeddable pk) {
		this.pk = pk;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
