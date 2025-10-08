package net.franco.supportJoin.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.franco.supportJoin.model.Asset;
import net.franco.supportJoin.model.Project;
import net.franco.supportJoin.repository.ProjectRepository;
import net.franco.supportJoin.service.ProjectAssetService;
import net.franco.supportJoin.service.ProjectService;
import net.franco.supportJoin.service.UserProjectService;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {

    private final ProjectRepository projectRepository;
	private final ProjectService projectService;
	private final UserProjectService userProjectService;
	private final ProjectAssetService projectAssetService;
	
	public ProjectController(ProjectService projectService
							, ProjectRepository projectRepository
							, UserProjectService userProjectService
							, ProjectAssetService projectAssetService) {
		this.projectService = projectService;
		this.projectRepository = projectRepository;
		this.userProjectService = userProjectService;
		this.projectAssetService = projectAssetService;
	}
	
	@GetMapping
	public List<Project> getProjects() { return this.projectService.getProjects(); }
	
	@GetMapping("/{id}")
	public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
		return this.projectService.getProjectById(id)
					.map(ResponseEntity::ok)
					.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Project> createProject(@RequestBody Project project) {
		Project createdProject = this.projectService.createProject(project.getName(), project.getDescription());
		return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project project) {
		Project updated = this.projectService.updateProject(id, project);
		return ResponseEntity.ok(updated);
	}

	@PatchMapping("/{id}/name")
	public ResponseEntity<Project> updateName(@PathVariable Long id, @RequestBody String name) {
		Project update = this.projectService.updateProjectName(id, name);
		return ResponseEntity.ok(update);
	}
	
	@PatchMapping("/{id}/description")
	public ResponseEntity<Project> updateDescription(@PathVariable Long id, @RequestBody String description) {
		Project update = this.projectService.updateProjectDescription(id, description);
		return ResponseEntity.ok(update);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
		this.projectService.deleteProject(id);
		return ResponseEntity.noContent().build();
	}
	
	// -----------------------------
    //  User <-> Project links
    // -----------------------------
	@PostMapping("/{projectId}/users/{userId}")
	public ResponseEntity<Void> addUserToProject(@PathVariable Long projectId,
			@PathVariable Long userId,
			@RequestParam(defaultValue = "Contributor") String role) {
		this.userProjectService.assignUserToProject(userId, projectId, role);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@DeleteMapping("/{projectId}/users/{userId}")
	public ResponseEntity<Void> removeUserFromProject(@PathVariable Long projectId
													, @PathVariable Long userId) {
		this.userProjectService.removeUserFromProject(userId, projectId);
		return ResponseEntity.noContent().build();
	}
	
	// -----------------------------
    //  Asset <-> Project links
    // -----------------------------
	@PostMapping("/{projectId}/assets")
	public ResponseEntity<Void> addAssetToProject(@PathVariable Long projectId, @RequestBody Asset asset) {
		this.projectAssetService.linkAssetToProject(projectId, projectId, null);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@DeleteMapping("/{projectId}/assets/{assetId}")
	public ResponseEntity<Void> removeAssetFromProject(@PathVariable Long projectId, @PathVariable Long assetId) {
		this.projectAssetService.unlinkAssetFromProject(projectId, assetId);
		return ResponseEntity.noContent().build();
	}
}
