package inheritance;

import javax.persistence.*;
import java.util.Date;

/**
 * User: ban
 * Date: 19. 2. 2015
 * Time: 15:15
 */
@Entity
public class Employee  {

	@Id
	private int id;

	private String name;

	@Temporal(TemporalType.DATE)
	@Column(name = "S_DATE")
	private Date date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
