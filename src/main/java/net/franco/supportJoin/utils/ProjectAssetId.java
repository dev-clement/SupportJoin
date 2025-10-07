package net.franco.supportJoin.utils;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class ProjectAssetId implements Serializable {
	private Long projectId;
	private Long assetId;
	
	public ProjectAssetId(Long projectId, Long assetId) {
		this.projectId = projectId;
		this.assetId = assetId;
	}
}
