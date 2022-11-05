package com.heroku.api.resource;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.heroku.api.entity.User;
import com.heroku.api.service.UserService;

@CrossOrigin
@RestController
@RequestMapping(value = "/user")
public class UserResource {

	@Autowired
	private UserService userService;

	@GetMapping
	public Page<User> findAll(@RequestParam(required = true, defaultValue = "10") int qtd,
			@RequestParam(required = true, defaultValue = "0") int page,
			@RequestParam(value = "nome", required = false) Optional<String> nome) {
		Pageable paginacao = PageRequest.of(page, qtd);
		return userService.findAll(paginacao, nome.orElse(null));
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		userService.delete(id);
	}

	@PostMapping
	public User save(@RequestBody User user){
		return userService.save(user);
	}
	
	@GetMapping(value = "/email")
	public Optional<User> findByEmail(@RequestParam(required = true) String email) {
		return userService.findByEmail(email);
	}

}