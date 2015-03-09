package org.jpa.test.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * User: ban
 * Date: 17. 2. 2015
 * Time: 17:01
 */
@Entity
public class Department {
	@EmbeddedId
	private DeptId id;

	@OneToMany(mappedBy = "department")
	private List<Project> projects;

	public DeptId getId() {
		return id;
	}

	public void setId(DeptId id) {
		this.id = id;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
}
