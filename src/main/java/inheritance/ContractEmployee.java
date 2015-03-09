package inheritance;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * User: ban
 * Date: 19. 2. 2015
 * Time: 15:15
 */

@Entity
public class ContractEmployee extends inheritance.Employee {

	@Column(name = "d_rate")
	private int dailyRate;

	public int getDailyRate() {
		return dailyRate;
	}

	public void setDailyRate(int dailyRate) {
		this.dailyRate = dailyRate;
	}
}
