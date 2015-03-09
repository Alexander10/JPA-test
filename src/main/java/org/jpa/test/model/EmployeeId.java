package org.jpa.test.model;

import java.io.Serializable;

/**
 * User: ban
 * Date: 17. 2. 2015
 * Time: 9:18
 */

public class EmployeeId implements Serializable {


	private int id;
	private String country;


	public EmployeeId() {

	}

	public EmployeeId(String country, int id) {
		this.country = country;
		this.id = id;
	}

	public String getCountry() {
		return country;
	}


	public int getId() {
		return id;
	}

	public boolean equals(Object o) {
		return ((o instanceof EmployeeId) &&
				country.equals(((EmployeeId) o).getCountry()) &&
				id == ((EmployeeId) o).getId());
	}

	public int hashCode() {
		return country.hashCode() + id;
	}
}

