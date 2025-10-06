package net.franco.supportJoin.service;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import net.franco.supportJoin.repository.UserProjectRepository;

@Service
public class UserProjectService {
	private final UserProjectRepository userProjectRepository;
	
	public UserProjectService(UserProjectRepository userProjectRepository) {
		this.userProjectRepository = userProjectRepository;
	}
	
	@Transactional
	public void removeUserFromProject(Long userId, Long projectId) {
		this.userProjectRepository.deleteByUserIdAndProjectId(userId, projectId);
	}
}
