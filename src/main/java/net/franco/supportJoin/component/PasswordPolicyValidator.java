package net.franco.supportJoin.component;

import org.springframework.stereotype.Component;

@Component
public class PasswordPolicyValidator {
	public void validate(String rawPassword) {
		if (rawPassword == null || rawPassword.length() < 8) {
			throw new IllegalArgumentException("Your password must be at least 8 character long !");
		}
		if (!rawPassword.matches(".*[A-Z].*")) {
			throw new IllegalArgumentException("Password character should contains at least one uppercase !");
		}
		if (!rawPassword.matches(".*[0-9].*")) {
			throw new IllegalArgumentException("Password must contains at least one digit !");
		}
	}
}
