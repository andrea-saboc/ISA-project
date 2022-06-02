package com.example.isa.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.isa.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);


}
