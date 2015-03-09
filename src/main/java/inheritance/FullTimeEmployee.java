package inheritance;

import javax.persistence.Entity;

/**
 * User: ban
 * Date: 19. 2. 2015
 * Time: 15:16
 */

@Entity
public class FullTimeEmployee extends CompanyEmployee {
	private long salary;

	private long pension;

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

	public long getPension() {
		return pension;
	}

	public void setPension(long pension) {
		this.pension = pension;
	}
}
