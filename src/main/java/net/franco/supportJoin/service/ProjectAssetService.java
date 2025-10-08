package net.franco.supportJoin.service;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import net.franco.supportJoin.model.Asset;
import net.franco.supportJoin.model.Project;
import net.franco.supportJoin.model.ProjectAsset;
import net.franco.supportJoin.repository.AssetRepository;
import net.franco.supportJoin.repository.ProjectAssetRepository;
import net.franco.supportJoin.repository.ProjectRepository;

@Service
public class ProjectAssetService {
	private final ProjectAssetRepository projectAssetRepository;
	private final ProjectRepository projectRepository;
	private final AssetRepository assetRepository;
	
	public ProjectAssetService(ProjectAssetRepository projectAssetRepository
							, ProjectRepository projectRepository
							, AssetRepository assetRepository) {
		this.projectAssetRepository = projectAssetRepository;
		this.projectRepository = projectRepository;
		this.assetRepository = assetRepository;
	}
	
	@Transactional
	public void linkAssetToProject(Project project, Asset asset, String metadata) {
		ProjectAsset pa = new ProjectAsset(project, asset, metadata);
		this.projectAssetRepository.save(pa);
	}
	
	@Transactional
	public void linkAssetToProject(Long projectId, Long assetId, String metadata) {
		Project p = this.projectRepository.findById(projectId)
										.orElseThrow(() -> new RuntimeException("No project with the id"));
		Asset a = this.assetRepository.findById(assetId)
									.orElseThrow(() -> new RuntimeException("No asset with the id"));
		this.linkAssetToProject(p, a, metadata);
	}
	
	@Transactional
	public void unlinkAssetFromProject(Project p, Asset a) {
		this.unlinkAssetFromProject(p.getId(), a.getId());
	}

	@Transactional
	public void unlinkAssetFromProject(Long projectId, Long assetId) {
		this.projectAssetRepository.deleteByProjectIdAndAssetId(projectId, assetId);
	}
}
