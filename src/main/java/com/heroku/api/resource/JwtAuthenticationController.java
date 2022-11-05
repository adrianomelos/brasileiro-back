package com.heroku.api.resource;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heroku.api.dto.LoginRequest;
import com.heroku.api.dto.LoginResponse;
import com.heroku.api.entity.User;
import com.heroku.api.exceptions.RequisicaoInvalidaExceptionBadRequest;
import com.heroku.api.exceptions.RequisicaoInvalidaExceptionUnautorized;
import com.heroku.api.repository.UserRepository;
import com.heroku.api.service.JwtTokenUtil;
import com.heroku.api.service.JwtUserDetailsService;

@RestController
@CrossOrigin
@RequestMapping(value = "/login")
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	
	@PostMapping
	public LoginResponse createAuthenticationUser(@RequestBody LoginRequest authenticationRequest) throws Exception {

		Optional<User> user = userRepository.findByUsername(authenticationRequest.getEmail());
		if (!user.isPresent()) {
			throw new RequisicaoInvalidaExceptionBadRequest("Usuario não encontrado");
		}

		authenticate(authenticationRequest.getEmail(), authenticationRequest.getSenha());
		UserDetails userDetails = userDetailsService.loadUserByUsername(user.get().getUsername());

		String token = jwtTokenUtil.generateToken(userDetails);
		return new LoginResponse(token);
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new RequisicaoInvalidaExceptionUnautorized("Senha inválida");
		}
	}
}