package com.sample.resource.com.sample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
public class AuthServerConfigurations extends WebSecurityConfigurerAdapter implements AuthorizationServerConfigurer {

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	@Qualifier("clientDetailsServiceImpl")
	private ClientDetailsService clientDetailsService;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private TokenStore mongoTokenStore;

	
	@Override
	public void configure(ClientDetailsServiceConfigurer clientDetailsServiceConfigurer) throws Exception {
		clientDetailsServiceConfigurer.withClientDetails(clientDetailsService);
//		clientDetailsServiceConfigurer.inMemory().withClient("abc").secret(bcryptEncoder.encode("123"))
//		.scopes("all")
//		.authorizedGrantTypes("password", "authorization_code");
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer securityConfigurer) throws Exception {
		securityConfigurer.checkTokenAccess("isAuthenticated()").tokenKeyAccess("permitAll()");
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpointsConfigurer) throws Exception {
		endpointsConfigurer.authenticationManager(authenticationManager).tokenStore(mongoTokenStore);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bcryptEncoder);
	}
	
	
	@Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
	
	@Bean
    public AuthenticationManager getAuthenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
