package net.franco.supportJoin.component;

import org.springframework.stereotype.Component;

@Component
public class EmailValidator {
	public void validate(String email) {
		if (!email.contains("@")) {
			throw new IllegalArgumentException("Invalid email, seems like the at isn't present");
		}
	}
}
