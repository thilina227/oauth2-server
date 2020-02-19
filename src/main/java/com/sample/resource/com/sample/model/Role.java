package com.sample.resource.com.sample.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document("role")
@Data
public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7839902711741832317L;
	
	@Id
	private String id;
	private String name;
	@DBRef
	private List<Permission> permissions;
	
	public Role(String name, List<Permission> permissions) {
		this.name = name;
		this.permissions = permissions;
	}
}
