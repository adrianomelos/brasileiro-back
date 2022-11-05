package com.heroku.api.service;

import com.heroku.api.entity.Aluguel;
import com.heroku.api.entity.Cliente;
import com.heroku.api.exceptions.DatabaseException;
import com.heroku.api.exceptions.ResourceNotFoundException;
import com.heroku.api.repository.AluguelRepository;
import com.heroku.api.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AluguelService {

	@Autowired
	private AluguelRepository aluguelRepository;

	public Aluguel save(Aluguel aluguel) {
		aluguel.setDataPedido(LocalDateTime.now());
		return aluguelRepository.save(aluguel);
	}


	public void delete(Long id) {
		try {
			Optional<Aluguel> u = aluguelRepository.findById(id);
			if(u != null) {
				aluguelRepository.delete(u.get());
			}
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (Exception e) {
			throw new DatabaseException("");
		}
	}

	public Optional<Aluguel> findById(Long id) {
		return aluguelRepository.findById(id);
	}

	public List<Aluguel> findAll() {
		return aluguelRepository.findAll();
	}
}
