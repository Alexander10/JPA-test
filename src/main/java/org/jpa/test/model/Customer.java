package org.jpa.test.model;

import javax.persistence.*;

/**
 * User: ban
 * Date: 19. 2. 2015
 * Time: 14:06
 */

@Entity
@Table(name = "Customer")
@SecondaryTable(name = "EMP_ADDRESS", pkJoinColumns = @PrimaryKeyJoinColumn(name = "EMP_ID"))
public class Customer {

	@Id
	@Column(name = "EMP_ID", table = "Customer")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name", table = "Customer")
	private String name;

	@Column(name = "street", table = "EMP_ADDRESS")
	private String street;

	@Column(name = "number", table = "EMP_ADDRESS")
	private String number;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}
