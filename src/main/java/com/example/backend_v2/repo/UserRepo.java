package com.example.backend_v2.repo;

import com.example.backend_v2.model.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User, Long> {
	Optional<User> findByEmail(String email);
}