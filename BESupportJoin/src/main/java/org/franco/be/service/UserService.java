package org.franco.be.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.franco.be.entity.UserEntity;
import org.franco.be.repository.UserRepository;

import java.util.List;

@ApplicationScoped
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> listAll() {
        return this.userRepository.listAll();
    }

    @Transactional
    public void addUser(UserEntity user) {
        this.userRepository.persist(user);
    }

    @Transactional
    public boolean deleteUser(Long id) {
        return this.userRepository.deleteById(id);
    }

    @Transactional
    public void deleteAll() {
        this.userRepository.deleteAll();
    }

    @Transactional
    public void updateUser(Long id, UserEntity user) {
        UserEntity entity = this.userRepository.findById(id);
        if (entity == null || user == null) {
            return;
        }
        entity.email = user.email;
        entity.firstName = user.firstName;
        entity.lastName = user.lastName;
        entity.password = user.password;
    }
}
