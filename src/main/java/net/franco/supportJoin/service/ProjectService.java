package net.franco.supportJoin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import net.franco.supportJoin.model.Project;
import net.franco.supportJoin.repository.ProjectAssetRepository;
import net.franco.supportJoin.repository.ProjectRepository;
import net.franco.supportJoin.repository.UserProjectRepository;

@Service
public class ProjectService {
	private final ProjectRepository projectRepository;
	private final UserProjectRepository userProjectRepository;
	private final ProjectAssetRepository projectAssetRepository;
	
	public ProjectService(ProjectRepository projectRepository
						  , UserProjectRepository userProjectRepository
						  , ProjectAssetRepository projectAssetRepository) {
		this.projectRepository = projectRepository;
		this.userProjectRepository = userProjectRepository;
		this.projectAssetRepository = projectAssetRepository;
	}
	
	public List<Project> getProjects() { return this.projectRepository.findAll(); }

	public Project createProject(String title, String description) {
		Project project = new Project(title, description);
		return this.projectRepository.save(project);
	}
	
	public Optional<Project> getProjectById(Long projectId) {
		return this.projectRepository.findById(projectId);
	}
	
	public Project updateProjectName(Long id, String name) {
		Project updatedProject = this.projectRepository.findById(id)
									.orElseThrow(() -> new RuntimeException("Unable to find the project"));
		updatedProject.setName(name);
		return updatedProject;
	}
	
	public Project updateProject(Long id, Project project) {
		Project updatedProject = this.projectRepository.findById(project.getId())
									.orElseThrow(() -> new RuntimeException("Project not found !"));
		updatedProject.setName(project.getName());
		updatedProject.setDescription(project.getDescription());
		return updatedProject;
		
	}
	
	public Project updateProjectDescription(Long id, String description) {
		Project updatedProject = this.projectRepository.findById(id)
									.orElseThrow(() -> new RuntimeException("Unable to find the project"));
		updatedProject.setDescription(description);
		return updatedProject;
	}

	@Transactional
	public void deleteProject(Long projectId) {
		// Remove all user links
		this.userProjectRepository.deleteAllByProjectId(projectId);
		
		// Remove all asset links (or assets you do not want)
		this.projectAssetRepository.deleteAllByProjectId(projectId);
		
		// Delete the project itself !
		this.projectRepository.deleteById(projectId);
	}
}
