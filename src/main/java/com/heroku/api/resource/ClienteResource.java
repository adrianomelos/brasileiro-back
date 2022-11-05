package com.heroku.api.resource;

import com.heroku.api.entity.Cliente;
import com.heroku.api.entity.Produto;
import com.heroku.api.service.ClienteService;
import com.heroku.api.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "/cliente")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public List<Cliente> findAll() {
		return clienteService.findAll();
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		clienteService.delete(id);
	}

	@PostMapping
	public Cliente save(@RequestBody Cliente cliente){
		return clienteService.save(cliente);
	}
	
	@GetMapping(value = "/{id}")
	public Optional<Cliente> findById(@PathVariable Long id) {
		return clienteService.findById(id);
	}

}