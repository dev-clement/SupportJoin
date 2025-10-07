package net.franco.supportJoin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.franco.supportJoin.model.Project;
import net.franco.supportJoin.model.User;
import net.franco.supportJoin.repository.ProjectRepository;
import net.franco.supportJoin.repository.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;
	private final ProjectRepository projectRepository;
	private final UserProjectService userProjectService;
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public UserService(UserRepository userRepository
					   , ProjectRepository projectRepository
					   , UserProjectService userProjectService) {
		this.userRepository = userRepository;
		this.projectRepository = projectRepository;
		this.userProjectService = userProjectService;
	}
	
	// -----------------------
    // User CRUD
    // -----------------------

	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}
	
	public Optional<User> getUserById(Long id) {
		return this.userRepository.findById(id);
	}

	public User createUser(String firstName, String lastName, String password, String email) {
		User user = new User(firstName, lastName, email, passwordEncoder.encode(password));
		return user;
	}
	
	public User updateUserEmail(Long userId, String email) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found !"));
		user.setEmail(email);
		return user;
	}
	
	public void deleteUser(Long userId) {
		this.userRepository.deleteById(userId);
	}

	// -----------------------
    // User ↔ Project relationship
    // -----------------------
	
	public void assignUserToProject(Long userId, Long projectId, String role) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found"));
		Project project = this.projectRepository.findById(projectId)
				.orElseThrow(() -> new RuntimeException("Project not found"));
		this.userProjectService.assignUserToProject(user, project, role);
	}
	
	public void removeUserFromProject(Long userId, Long projectId) {
		this.userProjectService.removeUserFromProject(userId, projectId);
	}
		
}
