package com.syscal.apisyscal.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestDTO implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;

	@NotNull
	private String username;

	@NotNull
	private String password;

}
