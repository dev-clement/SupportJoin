package org.franco.be.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue
    public Long id;
    public String firstName;
    public String lastName;
    public String email;
    public String password;
}
