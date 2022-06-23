package com.microservices.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	String resourceName;
	String resourceField;
	long fieldValue;
	public ResourceNotFoundException(String resourceName, String resourceField, long fieldValue) {
		super(String.format("%s not found with %s : %l",resourceName,resourceField,fieldValue));
		this.resourceName = resourceName;
		this.resourceField = resourceField;
		this.fieldValue = fieldValue;
	}
	
	
	
	
	
}
