package com.sample.resource.com.sample.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;

public interface OAuth2AccessTokeRepository extends MongoRepository<DefaultOAuth2AccessToken, String> {

}
