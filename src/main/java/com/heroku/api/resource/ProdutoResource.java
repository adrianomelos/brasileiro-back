package com.heroku.api.resource;

import com.heroku.api.entity.Produto;
import com.heroku.api.entity.User;
import com.heroku.api.service.ProdutoService;
import com.heroku.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "/produto")
public class ProdutoResource {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping
	public List<Produto> findAll() {
		return produtoService.findAll();
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		produtoService.delete(id);
	}

	@PostMapping
	public Produto save(@RequestBody Produto produto){
		return produtoService.save(produto);
	}
	
	@GetMapping(value = "/{id}")
	public Optional<Produto> findById(@PathVariable Long id) {
		return produtoService.findById(id);
	}

}