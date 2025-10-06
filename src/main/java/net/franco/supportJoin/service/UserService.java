package net.franco.supportJoin.service;

import org.springframework.stereotype.Service;

import net.franco.supportJoin.model.Project;
import net.franco.supportJoin.model.User;
import net.franco.supportJoin.model.UserProject;
import net.franco.supportJoin.repository.ProjectRepository;
import net.franco.supportJoin.repository.UserProjectRepository;
import net.franco.supportJoin.repository.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;
	private final ProjectRepository projectRepository;
	private final UserProjectRepository userProjectRepository;
	
	public UserService(UserRepository userRepository
					   , ProjectRepository projectRepository
					   , UserProjectRepository userProjectRepository) {
		this.userRepository = userRepository;
		this.projectRepository = projectRepository;
		this.userProjectRepository = userProjectRepository;
	}
	
	public User createUser(String firstName, String lastName, String password, String email) {
		User user = new User(firstName, lastName, email, password);
		return user;
	}
	
	public Project createProject(String name, String description) {
		Project project = new Project(name, description);
		return project;
	}
	
	public void assignUserToProject(User user, Project project, String role) {
		UserProject userProject = new UserProject(user, project, role);
		this.userProjectRepository.save(userProject);
	}
}
