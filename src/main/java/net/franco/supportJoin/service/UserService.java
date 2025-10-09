package net.franco.supportJoin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.franco.supportJoin.component.EmailSpecification;
import net.franco.supportJoin.component.EmailValidator;
import net.franco.supportJoin.component.PasswordPolicyValidator;
import net.franco.supportJoin.model.User;
import net.franco.supportJoin.repository.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final EmailValidator emailValidator;
	private final EmailSpecification emailSpecification;
	private final PasswordPolicyValidator passwordValidator;
	
	public UserService(UserRepository userRepository
					   , EmailValidator emailValidator
					   , EmailSpecification emailSpecification
					   , PasswordPolicyValidator passwordValidator
					   , PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.passwordValidator = passwordValidator;
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
		this.emailValidator.validate(email);
		this.emailSpecification.check(email);
		this.passwordValidator.validate(password);
		
		User user = new User(firstName, lastName, email, this.passwordEncoder.encode(password));
		return this.userRepository.save(user);
	}
	
	public User changePassword(Long userId, String password) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found !"));
		this.passwordValidator.validate(password);
		String encodedPassword = this.passwordEncoder.encode(password);
		user.setPassword(encodedPassword);
		return this.userRepository.save(user);
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
