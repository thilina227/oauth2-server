package com.sample.resource.com.sample.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sample.resource.com.sample.model.User;


public interface UserRepository extends MongoRepository<User, String>{

	Optional<User> findByEmail(String email);
}
