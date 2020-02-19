package com.sample.resource.com.sample.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document("permission")
@Data
public class Permission implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8975302676318224596L;
	
	@Id
    private String id;
    private String name;
    
    public Permission(String name) {
    	this.name = name;
    }
}