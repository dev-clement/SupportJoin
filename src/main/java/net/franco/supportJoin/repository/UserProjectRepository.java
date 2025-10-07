package net.franco.supportJoin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.transaction.Transactional;
import net.franco.supportJoin.model.UserProject;
import net.franco.supportJoin.utils.UserProjectId;

public interface UserProjectRepository extends JpaRepository<UserProject, UserProjectId> {
	@Transactional
	void deleteAllByProjectId(Long projectId);
}
