package com.heroku.api.service;

import com.heroku.api.entity.Cliente;
import com.heroku.api.entity.Produto;
import com.heroku.api.exceptions.DatabaseException;
import com.heroku.api.exceptions.ResourceNotFoundException;
import com.heroku.api.repository.ClienteRepository;
import com.heroku.api.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente save(Cliente cliente) {
		cliente.setDataCadastro(LocalDate.now());
		return clienteRepository.save(cliente);
	}


	public void delete(Long id) {
		try {
			Optional<Cliente> u = clienteRepository.findById(id);
			if(u != null) {
				clienteRepository.delete(u.get());
			}
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (Exception e) {
			throw new DatabaseException("");
		}
	}


	public Optional<Cliente> findById(Long id) {
		return clienteRepository.findById(id);
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
}
