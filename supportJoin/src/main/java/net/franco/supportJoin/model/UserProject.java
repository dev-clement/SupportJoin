package net.franco.supportJoin.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;

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
	
	// Extra fields
	private String role;
}
