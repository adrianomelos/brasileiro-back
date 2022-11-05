package com.heroku.api.resource;

import com.heroku.api.entity.Aluguel;
import com.heroku.api.entity.Cliente;
import com.heroku.api.service.AluguelService;
import com.heroku.api.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "/aluguel")
public class AluguelResource {

	@Autowired
	private AluguelService aluguelService;

	@GetMapping
	public List<Aluguel> findAll() {
		return aluguelService.findAll();
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		aluguelService.delete(id);
	}

	@PostMapping
	public Aluguel save(@RequestBody Aluguel aluguel){
		return aluguelService.save(aluguel);
	}
	
	@GetMapping(value = "/{id}")
	public Optional<Aluguel> findById(@PathVariable Long id) {
		return aluguelService.findById(id);
	}

}