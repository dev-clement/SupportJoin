package net.franco.supportJoin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.franco.supportJoin.model.User;

public interface UserRepository extends JpaRepository<User, Long> { 
	public boolean existsByEmail(String email);
}
