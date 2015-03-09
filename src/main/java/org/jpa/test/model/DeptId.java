package org.jpa.test.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * User: ban
 * Date: 17. 2. 2015
 * Time: 16:57
 */

@Embeddable
public class DeptId implements Serializable {

	@Column(name = "NUM")
	private int number;

	@Column(name = "CTRY")
	private String city;


	public DeptId() {
	}

	public DeptId(int number, String city) {
		this.number = number;
		this.city = city;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
