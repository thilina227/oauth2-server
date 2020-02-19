package com.sample.resource.com.sample.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sample.resource.com.sample.model.Permission;

public interface PermissionRepository extends MongoRepository<Permission, String>{
	
	Permission findByName(String name);
}
