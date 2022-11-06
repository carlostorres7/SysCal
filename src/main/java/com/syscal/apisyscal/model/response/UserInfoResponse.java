package com.syscal.apisyscal.model.response;

import com.syscal.apisyscal.model.entity.RolEntity;

import lombok.Data;

@Data
public class UserInfoResponse {
    private Integer id;
	private String username;
	private String email;
	private RolEntity roles;

	public UserInfoResponse(Integer id, String username, String email) {
		this.id = id;
		this.username = username;
		this.email = email;
	}

}
