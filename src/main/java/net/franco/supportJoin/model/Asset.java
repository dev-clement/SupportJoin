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

@Entity
@Table(name = "asset")
public class Asset {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String link;
	private String metadata;
	
	@OneToMany(mappedBy = "asset", cascade = CascadeType.ALL, orphanRemoval = false)
	private Set<Project> projectAssets = new HashSet<>();
	
	public Long getId() { return this.id; }
	
	public String getLink() { return this.link; }
	
	public String getMetadata() { return this.metadata; }
}
