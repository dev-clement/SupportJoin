package net.franco.supportJoin.service;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import net.franco.supportJoin.model.Project;
import net.franco.supportJoin.model.User;
import net.franco.supportJoin.model.UserProject;
import net.franco.supportJoin.repository.ProjectRepository;
import net.franco.supportJoin.repository.UserProjectRepository;
import net.franco.supportJoin.repository.UserRepository;
import net.franco.supportJoin.utils.UserProjectId;

@Service
public class UserProjectService {
	private final UserProjectRepository userProjectRepository;
	private final UserRepository userRepository;
	private final ProjectRepository projectRepository;
	
	public UserProjectService(UserProjectRepository userProjectRepository
							, UserRepository userRepository
							, ProjectRepository projectRepository) {
		this.userProjectRepository = userProjectRepository;
		this.userRepository = userRepository;
		this.projectRepository = projectRepository;
	}
	
	@Transactional
	public void assignUserToProject(User user, Project project, String role) {
		UserProject userProject = new UserProject(user, project, role);
		this.userProjectRepository.save(userProject);
	}
	
	@Transactional
	public void assignUserToProject(Long userId, Long projectId, String role) {
		User user = this.userRepository.findById(userId)
						.orElseThrow(() -> new RuntimeException("User cannot be found !"));
		Project project = this.projectRepository.findById(projectId)
						.orElseThrow(() -> new RuntimeException("Project cannot be found !"));
		assignUserToProject(user.getId(), project.getId(), role);
	}
	
	@Transactional
	public void removeUserFromProject(User user, Project project) {
		Long userId = user.getId();
		Long projectId = project.getId();
		this.removeUserFromProject(userId, projectId);
	}
	
	@Transactional
	public void removeUserFromProject(Long userId, Long projectId) {
		this.userProjectRepository.deleteById(new UserProjectId(userId, projectId));
	}
}
