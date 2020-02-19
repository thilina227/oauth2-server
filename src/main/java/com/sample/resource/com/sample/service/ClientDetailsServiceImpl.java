package com.sample.resource.com.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import com.sample.resource.com.sample.dao.OAuthClientDetailsRepository;

@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {

	@Autowired
	private OAuthClientDetailsRepository oAuthClientDetailsRepository;
	
	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		ClientDetails clientDetails =  oAuthClientDetailsRepository.findById(clientId).get();
		if (clientDetails == null) {
			throw new ClientRegistrationException("Client details not found for the id " + clientId);
		}
		return clientDetails;
	}
}
