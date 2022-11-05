package com.heroku.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.heroku.api.entity.User;
import com.heroku.api.exceptions.DatabaseException;
import com.heroku.api.exceptions.RequisicaoInvalidaExceptionBadRequest;
import com.heroku.api.exceptions.ResourceNotFoundException;
import com.heroku.api.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User save(User user) {

		Optional<User> u = userRepository.findByUsername(user.getUsername());
		if (u.isPresent()) {
			throw new RequisicaoInvalidaExceptionBadRequest("email já cadastrado no app");
		}

		String password = criptografarPassword(user.getPassword());
		user.setPassword(password);

		User b = userRepository.save(user);
		return b;
	}

	private String criptografarPassword(String password) {
		String passCriptografado = BCrypt.hashpw(password, BCrypt.gensalt());
		return passCriptografado;
	}

	public void delete(Long id) {
		try {
			Optional<User> u = userRepository.findById(id);
			if(u != null) {
				userRepository.delete(u.get());
			}
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (Exception e) {
			throw new DatabaseException("");
		}
	}

	public Page<User> findAll(Pageable paginacao, String nome) {
		return userRepository.findAll(paginacao);
	}

	public Optional<User> findByEmail(String email) {
		return userRepository.findByUsername(email);
		
	}
}
