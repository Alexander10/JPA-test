package org.jpa.test.model;

import javax.persistence.*;

/**
 * User: ban
 * Date: 19. 2. 2015
 * Time: 14:20
 */

@Entity
@Table(name = "employee")
@IdClass(EmployeeId.class)
@SecondaryTables({
		@SecondaryTable(name = "org", pkJoinColumns = {
				@PrimaryKeyJoinColumn(name = "COUNTRY", referencedColumnName = "COUNTRY"),
				@PrimaryKeyJoinColumn(name = "EMP_ID", referencedColumnName = "ID")
		}),
		@SecondaryTable(name = "lob", pkJoinColumns = {
				@PrimaryKeyJoinColumn(name = "COUNTRY", referencedColumnName = "COUNTRY"),
				@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
		})
})
public class EmployeeMultiTable {

	@Id
	private String country;
	@Id
	@Column(name = "ID")
	private int id;


	@Column(table = "org")
	private char[] comments;

	@Column(table = "lob")
	private byte[] lob;


	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public char[] getComments() {
		return comments;
	}

	public void setComments(char[] comments) {
		this.comments = comments;
	}

	public byte[] getLob() {
		return lob;
	}

	public void setLob(byte[] lob) {
		this.lob = lob;
	}
}
