package inheritance;

import org.jpa.test.model.*;

import javax.persistence.MappedSuperclass;

/**
 * User: ban
 * Date: 19. 2. 2015
 * Time: 15:15
 */
@MappedSuperclass
public class CompanyEmployee extends inheritance.Employee {
	private int vacation;

	public int getVacation() {
		return vacation;
	}

	public void setVacation(int vacation) {
		this.vacation = vacation;
	}
}
