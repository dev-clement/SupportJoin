package net.franco.supportJoin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.transaction.Transactional;
import net.franco.supportJoin.model.ProjectAsset;
import net.franco.supportJoin.utils.ProjectAssetId;

public interface ProjectAssetRepository extends JpaRepository<ProjectAsset, ProjectAssetId> {
	@Transactional
	void deleteByProjectIdAndAssetId(Long projectId, Long assetId);
	@Transactional
	void deleteAllByProjectId(Long projectId);
}
