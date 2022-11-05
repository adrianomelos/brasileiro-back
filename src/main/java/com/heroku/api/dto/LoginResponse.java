package com.heroku.api.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class LoginResponse implements Serializable {
	private static final long serialVersionUID = -8091879091924046844L;
	
	private final String token;
	
}