package net.franco.supportJoin.model;

import java.time.LocalDate;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import net.franco.supportJoin.utils.ProjectAssetId;

@Entity
@Table(name = "m2m_project_asset")
public class ProjectAsset {
	@EmbeddedId
	private ProjectAssetId id;
	
	@ManyToOne
	@MapsId("projectId")
	@JoinColumn(name = "project_id")
	private Project project;
	
	@ManyToOne
	@MapsId("assetId")
	@JoinColumn(name = "asset_id")
	private Asset asset;
	
	private String metadata;
	private LocalDate addedDate;

	public ProjectAsset(Project project, Asset asset, String metadata) {
		this.id = new ProjectAssetId(project.getId(), asset.getId());
		this.asset = asset;
		this.project = project;
		this.metadata = metadata;
		this.addedDate = LocalDate.now();
	}
	
	public Project getProject() { return this.project; }
	
	public Asset getAsset() { return this.asset; }
	
	public String getMetadata() { return this.metadata; }
	
	public LocalDate getDate() { return this.addedDate; }
}
