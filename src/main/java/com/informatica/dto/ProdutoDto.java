package com.informatica.dto;

import com.informatica.model.Produto;

import jakarta.validation.constraints.NotBlank;

public record ProdutoDto(
		
		Long id,
		@NotBlank(message = "Não pode ser vazio")
		String nome,
		@NotBlank(message = "Não pode ser vazio")
		String imagem,
		
		double valor,
		
		int qtd) {

	public ProdutoDto(Produto cadastro) {
		this(
				cadastro.getId(),
				cadastro.getNome(),
				cadastro.getImagem(),
				cadastro.getValor(),
				cadastro.getQtd()); 
	}

}
