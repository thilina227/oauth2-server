package com.sample.resource.com.sample.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.sample.resource.com.sample.model.OAuthClientDetails;

public interface OAuthClientDetailsRepository extends MongoRepository<OAuthClientDetails, String>{

}
