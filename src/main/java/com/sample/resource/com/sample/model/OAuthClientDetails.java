package com.sample.resource.com.sample.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import lombok.Data;

@Document(collection = "oAuthClientDetails")
@Data
public class OAuthClientDetails implements ClientDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String clientId;
	
	private Set<String> resourceIds = new HashSet<String>();

	private boolean secretRequired;

	private String clientSecret;

	private boolean scoped;

	private Set<String> scope = new HashSet<String>();

	private Set<String> authorizedGrantTypes;

	private Set<String> regteredRedirectUri;

	private Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

	private Integer accessTokenValiditySeconds;

	private Integer refreshTokenValiditySeconds;

	private Map<String, Object> additionalInformation = new HashMap<>();
	
	private Set<String> registeredRedirectUri = new HashSet<String>();

	@Override
	public boolean isAutoApprove(String scope) {
		return true;
	}
}
