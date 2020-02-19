package com.sample.resource.com.sample.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sample.resource.com.sample.model.Role;

public interface RoleRepository extends MongoRepository<Role, String>{
	
	Role findByName(String name);
}
