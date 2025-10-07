package net.franco.supportJoin.model;

import java.time.LocalDate;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;
import net.franco.supportJoin.utils.UserProjectId;

@Entity
@Data
@Table(name = "m2m_user_project")
public class UserProject {

	@EmbeddedId
	private UserProjectId id = new UserProjectId();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("userId") // Maps with to the primary_key column in UserProjectId
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("projectId")
	@JoinColumn(name = "project_id")
	private Project project;

	private String role;
	
	private LocalDate assignedDate;

	public UserProject(User user, Project project, String role) {
		this.user = user;
		this.project = project;
		this.role = role;
		this.assignedDate = LocalDate.now();
		this.setId(new UserProjectId(user.getId(), project.getId()));
	}
		
	public String getRole() { return this.role; }
	
	public LocalDate getDate() { return this.assignedDate; }

	public void setRole(String role) { 
		if (this.role.equals(role)) {
			return ;
		}
		this.role = role; 
	}
	
	public void setId(UserProjectId id) {
		if (this.id.equals(id)) {
			return ;
		}
		this.id = id;
	}
	
}
