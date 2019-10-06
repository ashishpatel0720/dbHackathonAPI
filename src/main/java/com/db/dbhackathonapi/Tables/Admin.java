package com.db.dbhackathonapi.Tables;


import javax.persistence.Entity;
import javax.persistence.Id;


@Entity // This tells Hibernate to make a table out of this class
public class Admin {

    @Id
    private String id;
    private String name;
    private String password;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Admin{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}