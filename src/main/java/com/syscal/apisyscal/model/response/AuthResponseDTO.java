package com.syscal.apisyscal.model.response;

import java.io.Serializable;

public class AuthResponseDTO implements Serializable {
    
	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;

	public AuthResponseDTO(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}

}
