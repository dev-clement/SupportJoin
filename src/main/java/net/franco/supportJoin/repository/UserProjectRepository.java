package net.franco.supportJoin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.franco.supportJoin.model.UserProject;
import net.franco.supportJoin.utils.UserProjectId;

public interface UserProjectRepository extends JpaRepository<UserProject, UserProjectId> {
}
