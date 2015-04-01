package org.jpa.test.model;

import javax.persistence.*;

/**
 * User: ban
 * Date: 16. 2. 2015
 * Time: 15:43
 */

@Entity
public class Employee {

	@Id
	private int id;

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
