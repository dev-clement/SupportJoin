package net.franco.supportJoin.service;

import net.franco.supportJoin.model.User;
import net.franco.supportJoin.repository.UserProjectRepository;
import net.franco.supportJoin.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*; // For all the assertEquals
import static org.mockito.Mockito.*; // For all verify, when, etc...

// Ensure this annotation is present
@ExtendWith(MockitoExtension.class)
class UserserviceTest {
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UserService userService;
	
	private User user;
	
	@BeforeEach
	void setup() {
		this.user = new User("Alice", "Smith", "alice@example.com", "password");
	}
	
	@Test
	void getAllUsers_shouldReturnList() {
		when(this.userRepository.findAll()).thenReturn(List.of(this.user));
		
		var users = this.userService.getAllUsers();
		
		assertEquals(1, users.size());
		verify(this.userRepository).findAll();
	}
}
