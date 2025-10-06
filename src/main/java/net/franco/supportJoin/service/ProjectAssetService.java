package net.franco.supportJoin.service;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import net.franco.supportJoin.model.Asset;
import net.franco.supportJoin.model.Project;
import net.franco.supportJoin.model.ProjectAsset;
import net.franco.supportJoin.repository.ProjectAssetRepository;

@Service
public class ProjectAssetService {
	private final ProjectAssetRepository projectAssetRepository;
	
	public ProjectAssetService(ProjectAssetRepository projectAssetRepository) {
		this.projectAssetRepository = projectAssetRepository;
	}
	
	@Transactional
	public void linkAssetToProject(Project project, Asset asset, String metadata) {
		ProjectAsset pa = new ProjectAsset(project, asset, metadata);
		this.projectAssetRepository.save(pa);
	}
	
	@Transactional
	public void unlinkAssetFromProject(Long projectId, Long assetId) {
		projectAssetRepository.deleteProjectIdAndAssetId(projectId, assetId);
	}
}
