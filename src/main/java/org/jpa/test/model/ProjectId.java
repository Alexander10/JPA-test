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

	@Embedded
	private DeptId dept;

	public ProjectId(){

	}

	public ProjectId(String name, DeptId dept) {
		this.name = name;
		this.dept = dept;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DeptId getDept() {
		return dept;
	}

	public void setDept(DeptId deptId) {
		this.dept = deptId;
	}
}
