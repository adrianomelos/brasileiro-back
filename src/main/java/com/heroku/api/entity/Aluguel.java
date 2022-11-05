package com.heroku.api.entity;

import com.heroku.api.enuns.FormaPagamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_aluguel")
public class Aluguel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @ManyToMany
    private List<Produto> produtos;

    private Double valorPedido;

    private FormaPagamento formaPagamento;

    private LocalDateTime dataPedido;

    private LocalDateTime dataHoraSaida;

    private LocalDateTime dataHoraChegada;

    private String Observacao;
}
