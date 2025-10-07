package net.franco.supportJoin.service;

import java.util.List;
import java.util.Optional;

import net.franco.supportJoin.model.Project;
import net.franco.supportJoin.repository.ProjectRepository;

public class ProjectService {
	private final ProjectRepository projectRepository;
	
	public ProjectService(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}
	
	public List<Project> getProjects() { return this.projectRepository.findAll(); }

	public Project createProject(String title, String description) {
		Project project = new Project(title, description);
		return this.projectRepository.save(project);
	}
	
	public Optional<Project> getProjectById(Long projectId) {
		return this.projectRepository.findById(projectId);
	}
}
