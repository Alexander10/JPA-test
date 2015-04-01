package org.jpa.test.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;

/**
 * User: ban
 * Date: 17. 2. 2015
 * Time: 16:56
 */
@Embeddable
public class ProjectId implements Serializable {

	@Column(name = "P_NAME")
	private String name;

	@Column(name = "DEPT")
	private String dept;

	public ProjectId(){

	}

	public ProjectId(String name, String dept) {
		this.name = name;
		this.dept = dept;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}
}
