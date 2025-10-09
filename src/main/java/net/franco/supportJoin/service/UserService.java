package net.franco.supportJoin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.franco.supportJoin.component.EmailSpecification;
import net.franco.supportJoin.component.EmailValidator;
import net.franco.supportJoin.model.User;
import net.franco.supportJoin.repository.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final EmailValidator emailValidator;
	private final EmailSpecification emailSpecification;
	
	public UserService(UserRepository userRepository
					   , EmailValidator emailValidator
					   , EmailSpecification emailSpecification) {
		this.userRepository = userRepository;
		this.passwordEncoder = new BCryptPasswordEncoder();
		this.emailValidator = emailValidator;
		this.emailSpecification = emailSpecification;
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
		this.emailValidator.validate(email);
		this.emailSpecification.check(email);
		user.setEmail(email);
		return this.userRepository.save(user);
	}
	
	public void deleteUser(Long userId) {
		this.userRepository.deleteById(userId);
	}
}
