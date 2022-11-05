package com.heroku.api.service;

import com.heroku.api.entity.Produto;
import com.heroku.api.entity.User;
import com.heroku.api.exceptions.DatabaseException;
import com.heroku.api.exceptions.RequisicaoInvalidaExceptionBadRequest;
import com.heroku.api.exceptions.ResourceNotFoundException;
import com.heroku.api.repository.ProdutoRepository;
import com.heroku.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public Produto save(Produto produto) {
		return produtoRepository.save(produto);
	}


	public void delete(Long id) {
		try {
			Optional<Produto> u = produtoRepository.findById(id);
			if(u != null) {
				produtoRepository.delete(u.get());
			}
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (Exception e) {
			throw new DatabaseException("");
		}
	}


	public Optional<Produto> findById(Long id) {
		return produtoRepository.findById(id);
	}

	public List<Produto> findAll() {
		return produtoRepository.findAll();
	}
}
