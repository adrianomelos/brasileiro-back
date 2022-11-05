package com.heroku.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.heroku.api.entity.User;
import com.heroku.api.repository.UserRepository;


@Service
public class AutenticadorService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		try {
			Optional<User> usuario = userRepository.findByUsername(email);
			return usuario.get();
		} catch (Exception e) {
			throw new UsernameNotFoundException("Dados Inválidos.");	
		}
	}

}
