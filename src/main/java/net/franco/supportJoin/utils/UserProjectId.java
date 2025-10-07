package net.franco.supportJoin.utils;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProjectId implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long userId;
	private Long projectId;
	
	public UserProjectId(Long userId, Long projectId) {
		this.userId = userId;
		this.projectId = projectId;
	}
	
	public UserProjectId() {}
}
