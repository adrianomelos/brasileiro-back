package com.heroku.api.repository;

import com.heroku.api.entity.Aluguel;
import com.heroku.api.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Long> {


}
