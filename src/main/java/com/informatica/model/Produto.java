package com.informatica.model;

import com.informatica.dto.ProdutoDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "produtos")
@Data
@NoArgsConstructor
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String imagem;
	private double valor;
	private int qtd;
	

	public Produto(ProdutoDto produto) {
		this.id = produto.id();
		this.nome = produto.nome();
		this.imagem = produto.imagem();
		this.valor = produto.valor();
		this.qtd = produto.qtd();
	}
}
