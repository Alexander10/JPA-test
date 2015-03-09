package org.jpa.test.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * User: ban
 * Date: 17. 2. 2015
 * Time: 13:36
 */
@Embeddable
public class EmployeeIdEmbeddable implements Serializable {

	@Column(name = "COUNTRY")
	private String country;

	@Column(name = "EMP_ID")
	private Long id;

	public EmployeeIdEmbeddable() {
	}

	public EmployeeIdEmbeddable(String country, Long id) {
		this.country = country;
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
