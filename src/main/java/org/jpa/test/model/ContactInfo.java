package org.jpa.test.model;

import javax.persistence.*;
import java.util.Map;

/**
 * User: ban
 * Date: 16. 2. 2015
 * Time: 15:37
 */

@Embeddable
@Access(AccessType.FIELD)
public class ContactInfo {

	@Embedded
	private Address residence;

	@ManyToOne
	@JoinColumn(name = "PRI_NUM")
	private Phone primaryPhone;

	@ManyToMany
	@MapKey(name="type")
	@JoinTable(name = "EMP_PHONES")
	private Map<String, Phone> phones;


	public Address getResidence() {
		return residence;
	}

	public void setResidence(Address residence) {
		this.residence = residence;
	}

	public Phone getPrimaryPhone() {
		return primaryPhone;
	}

	public void setPrimaryPhone(Phone primaryPhone) {
		this.primaryPhone = primaryPhone;
	}

	public Map<String, Phone> getPhones() {
		return phones;
	}

	public void setPhones(Map<String, Phone> phones) {
		this.phones = phones;
	}
}
