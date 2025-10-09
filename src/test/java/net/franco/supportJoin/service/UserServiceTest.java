package net.franco.supportJoin.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import net.franco.supportJoin.component.EmailSpecification;
import net.franco.supportJoin.component.EmailValidator;
import net.franco.supportJoin.component.PasswordPolicyValidator;
import net.franco.supportJoin.model.User;
import net.franco.supportJoin.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	@Mock
	private EmailValidator emailValidator;
	
	@Mock
	private EmailSpecification emailSpecification;
	
	@Mock
	private PasswordPolicyValidator passwordPolicyValidator;
	
	@InjectMocks
	private UserService userService;
	
	@BeforeEach
	void setup() {
		// @InjectMocks already wires mock into userService, nothing to do there
	}
	
	@Test
	void shouldReturnAllUsers() {
		User u = new User("Alice", "Smith", "alie@example.com", "pwd");
		when(userRepository.findAll()).thenReturn(List.of(u));
		
		List<User> users = this.userService.getAllUsers();
		
		assertEquals(1, users.size());
		assertEquals(u.getFirstName(), users.get(0).getFirstName());
		assertEquals(u.getLastName(), users.get(0).getLastName());
		assertEquals(u.getEmail(), users.get(0).getEmail());
		verify(this.userRepository.findAll());
	}
	
	@Test
	void shouldCreate_andEncodePassword_andSave() {
		// arrange
		String raw = "Secret 123";
		when(this.passwordEncoder.encode(raw)).thenReturn("encodedSecret");
		
		// Capture saved user
		when(this.userRepository.save(any(User.class))).thenAnswer(i -> i.getArgument(0));
		
		// act
		User created = this.userService.createUser("John", "Doe", raw, "john@example.com");
		
		// Assert the saved password gets encoded
		ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
		verify(this.userRepository).save(captor.capture());
		User saved = captor.getValue();
		assertEquals("encodedSecret", saved.getPassword());
		assertEquals(created.getEmail(), saved.getEmail());
		// And returned user is the same saved object
		assertEquals(saved, created);
	}
	
	@Test
	void changePassword_happyPath_updateAndSave() {
		User user = new User("Alice", "Smith", "alice@example.com", "oldEncoded");
		when(this.userRepository.findById(1L)).thenReturn(Optional.of(user));
		when(this.passwordEncoder.encode("NewPass!23")).thenReturn("newEncoded");
		when(this.userRepository.save(user)).thenReturn(user);
		
		User updated = this.userService.changePassword(1L, "NewPass!23");
		
		assertEquals("newEncoded", updated.getPassword());
		assertEquals(user, updated);
		verify(this.passwordPolicyValidator).validate("NewPass!23");
		verify(this.userRepository).save(user);
	}
	
	@Test
	void changePassword_userNotFound_throws() {
		when(this.userRepository.findById(999L)).thenReturn(Optional.empty());
		assertThrows(RuntimeException.class, () -> this.userService.changePassword(999L, "x"));
	}
	
	@Test
	void updateUserEmail_happyPath_validateAndSaves() {
		User user = new User("Bob", "B", "bob@old.com", "pwd");
		when(this.userRepository.findById(2L)).thenReturn(Optional.of(user));
		when(this.userRepository.save(user)).thenReturn(user);
		
		User result = this.userService.updateUserEmail(2L, "bob@new.com");
		
		assertEquals("bob@new.com", result.getEmail());
		verify(this.emailValidator).validate("bob@new.com");
		verify(this.emailSpecification).check("bob@new.com");
		verify(this.userRepository).save(user);
	}
	
	@Test
	void updateUserEmail_validationFails_throws() {
		User user = new User("Bob", "B", "bob@old.com", "pwd");
		when(this.userRepository.findById(2L)).thenReturn(Optional.of(user));
		
		// Simulate validation throwing
		doThrow(new IllegalArgumentException("invalid")).when(this.emailValidator).validate("bad");
		
		assertThrows(IllegalArgumentException.class, () -> this.userService.updateUserEmail(2L, "bad"));
		verify(this.userRepository, never()).save(any());
	}
	
	@Test
	void deleteUser_callsRepositoryDelete() {
		this.userService.deleteUser(5L);
		verify(this.userRepository).deleteById(5L);
	}
}
