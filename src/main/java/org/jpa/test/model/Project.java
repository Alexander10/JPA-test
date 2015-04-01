package org.jpa.test.model;

import javax.persistence.*;

/**
 * User: ban
 * Date: 17. 2. 2015
 * Time: 16:56
 */
@Entity
public class Project {

	@EmbeddedId
	private ProjectId id;



	@ManyToOne
	@JoinColumns({
		@JoinColumn(name ="DEPT_NUM", referencedColumnName = "NUM"),
			@JoinColumn(name = "DEPT_CTRY", referencedColumnName = "CITY")
	})
	private Department department;

	public ProjectId getId() {
		return id;
	}

	public void setId(ProjectId id) {
		this.id = id;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
}
