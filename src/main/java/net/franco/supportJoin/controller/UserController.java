package net.franco.supportJoin.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.franco.supportJoin.model.User;
import net.franco.supportJoin.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	public List<User> getAllUsers() {
		return this.userService.getAllUsers();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		return this.userService.getUserById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User userCreated = this.userService.createUser(user.getFirstName(), user.getLastName()
						, user.getPassword(), user.getEmail());
		return ResponseEntity.ok(userCreated);
	}
	
	@PutMapping("/{id}/email/")
	public ResponseEntity<User> updateEmail(@PathVariable Long id, @RequestBody String email) {
		User updated = this.userService.updateUserEmail(id, email);
		return ResponseEntity.ok(updated);
	}
	
	@DeleteMapping("/{id}/")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		this.userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
	
/*	@PostMapping("/{userId}/projects/{projectId}/")
	public ResponseEntity<Void> assignUserToProject(
			@PathVariable Long userId
			, @PathVariable Long projectId
			, @RequestBody String role) {
		this.userService.assignUserToProject(userId, projectId, role);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{userId}/projects/{orojectId}")
	public ResponseEntity<Void> removeUserFromProject(
					@PathVariable Long userId,
					@PathVariable Long projectId) {
		this.userService.removeUserFromProject(userId, projectId);
		return ResponseEntity.noContent().build();
	} */
}
