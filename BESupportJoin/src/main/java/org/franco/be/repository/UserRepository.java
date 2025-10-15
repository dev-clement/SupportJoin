package org.franco.be.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.franco.be.entity.UserEntity;

@ApplicationScoped
public class UserRepository implements PanacheRepository<UserEntity> {
}
