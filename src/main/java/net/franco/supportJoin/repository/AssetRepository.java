package net.franco.supportJoin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.franco.supportJoin.model.Asset;

public interface AssetRepository extends JpaRepository<Asset, Long> {

}