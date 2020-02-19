
package com.sample.resource.com.sample.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

/**
 * 
 */
@Document(collection = "user")
@Data
public class User implements UserDetails, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1585316015965061252L;
	
	@Id
	private String id;
	private String email;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	private boolean enabled = true;
	private boolean accountNonLocked = true;
	private boolean accountNonExpired = true;
	private boolean credentialsNonExpired = true;
	@DBRef
	private List<Role> roles;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		
		this.getRoles().forEach(role -> {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
			role.getPermissions().forEach(permission -> {
				grantedAuthorities.add(new SimpleGrantedAuthority(permission.getName()));
			});
		});
		
		return grantedAuthorities;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

}
