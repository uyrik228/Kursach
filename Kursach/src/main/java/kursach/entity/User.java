package kursach.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Users")
public class User extends AbstractEntity {
	@Column(name = "user_name")
	private String name;
	@Column(name = "email")
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	


	@Override
	public String toString() {
		return "User  - " + id + ": [name=" + name + ", email=" + email + "]";
	}
}

