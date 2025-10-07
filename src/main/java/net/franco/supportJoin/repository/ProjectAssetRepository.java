package net.franco.supportJoin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.franco.supportJoin.model.ProjectAsset;
import net.franco.supportJoin.utils.ProjectAssetId;

public interface ProjectAssetRepository extends JpaRepository<ProjectAsset, ProjectAssetId> {
	void deleteProjectIdAndAssetId(Long projectId, Long assetId);
}
