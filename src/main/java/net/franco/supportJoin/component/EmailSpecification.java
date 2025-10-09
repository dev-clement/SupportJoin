package net.franco.supportJoin.component;

import org.springframework.stereotype.Component;

import net.franco.supportJoin.repository.UserRepository;

@Component
public class EmailSpecification {
	private final UserRepository userRepository;
	
	public EmailSpecification(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public void check(String email) {
		if (this.userRepository.existsByEmail(email)) {
			throw new IllegalArgumentException("Email already taken");
		}
	}
}
