package net.franco.supportJoin.service;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import net.franco.supportJoin.model.Project;
import net.franco.supportJoin.model.User;
import net.franco.supportJoin.model.UserProject;
import net.franco.supportJoin.repository.UserProjectRepository;
import net.franco.supportJoin.utils.UserProjectId;

@Service
public class UserProjectService {
	private final UserProjectRepository userProjectRepository;
	
	public UserProjectService(UserProjectRepository userProjectRepository) {
		this.userProjectRepository = userProjectRepository;
	}
	
	@Transactional
	public void assignUserToProject(User user, Project project, String role) {
		UserProject userProject = new UserProject(user, project, role);
		this.userProjectRepository.save(userProject);
	}
	
	@Transactional
	public void removeUserFromProject(Long userId, Long projectId) {
		this.userProjectRepository.deleteById(new UserProjectId(userId, projectId));
	}
}
