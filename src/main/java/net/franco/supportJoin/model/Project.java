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
@Table(name = "project")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// One project can then have many user-project links
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<UserProject> userProjects = new HashSet<>();
	
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ProjectAsset> projectAssets = new HashSet<>();
	
	// This constructor is used by the JPA when creating an instance of it !
	public Project() {}
	
	public Project(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	private String name;
	private String description;
	
	public Long getId() { return this.id; }

	public String getName() { return this.name; }
	
	public String getDescription() { return this.description; }
	
	public void setName(String name) {
		if (this.name.equals(name)) {
			return ;
		}
		this.name = name;
	}
	
	public void setDescription(String description) {
		if (this.description.equals(description)) {
			return ;
		}
		this.description = description;
	}
}
