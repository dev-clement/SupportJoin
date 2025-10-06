package net.franco.supportJoin.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	// One user can also have many user-project links
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = false)
	private Set<UserProject> userProjects = new HashSet<>();
	
	public User(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	private String firstName;
	private String lastName;
	private String email;
	private String password;

	public String getFirstName() { return this.firstName; }
	
	public String getLastName() { return this.lastName; }
	
	public String getEmail() { return this.email; }
	
	public String getPassword() { return this.password; }
	
	public void setFirstName(String name) {
		if (this.firstName.equals(name)) {
			return;
		}
		this.firstName = name;
	}
	
	public void setLastName(String lastName) {
		if (this.lastName.equals(lastName)) {
			return;
		}
		this.lastName = lastName;
	}
	
	public void setEmail(String email) {
		if (this.email.equals(email)) {
			return;
		}
		this.email = email;
	}
	
	public void setPassword(String password) {
		if (this.password.equals(password)) {
			return;
		}
		this.password = password;
	}
}
