package com.sample.resource.com.sample.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.sample.resource.com.sample.dao.OAuthClientDetailsRepository;
import com.sample.resource.com.sample.dao.PermissionRepository;
import com.sample.resource.com.sample.dao.RoleRepository;
import com.sample.resource.com.sample.dao.UserRepository;
import com.sample.resource.com.sample.model.OAuthClientDetails;
import com.sample.resource.com.sample.model.Permission;
import com.sample.resource.com.sample.model.Role;
import com.sample.resource.com.sample.model.User;

@Component
public class InitDataSeedConfig {
	
	@Autowired
	private PermissionRepository permissionRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OAuthClientDetailsRepository authClientDetailsRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostConstruct
	private void init() {
		//seed default data
		
		if (permissionRepository.count() == 0) {
			permissionRepository.save(new Permission("Manage Users"));
		}
		
		if (roleRepository.count() == 0) {
			roleRepository.save(new Role("admin", permissionRepository.findAll()));
		}
		
		if (userRepository.count() == 0) {
			User user = new User();
			user.setEmail("admin");
			//default password: admin
			//please change it
			user.setPassword(bCryptPasswordEncoder.encode("admin"));
			List<Role> roles = new ArrayList<Role>();
			roles.add(roleRepository.findByName("admin"));
			user.setRoles(roles);
			userRepository.save(user);
		}
		
		if (authClientDetailsRepository.count() == 0) {
			OAuthClientDetails authClientDetails = new OAuthClientDetails();
			authClientDetails.setAccessTokenValiditySeconds(3600);
			authClientDetails.setClientId("abc");
			authClientDetails.setClientSecret(bCryptPasswordEncoder.encode("123"));
			authClientDetails.setScoped(true);
			Set<String> scopes = new HashSet<>();
			scopes.add("all");
			authClientDetails.setScope(scopes);
			Set<String> grantTypes = new HashSet<String>();
			grantTypes.add("password");
			grantTypes.add("authorization_code");
			authClientDetails.setAuthorizedGrantTypes(grantTypes);
			authClientDetails.setSecretRequired(true);
			authClientDetailsRepository.save(authClientDetails);
		}
	}
}
