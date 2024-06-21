package com.informatica.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.informatica.dto.ProdutoDto;
import com.informatica.model.Produto;
import com.informatica.repository.ProdutoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoService {
	private final ProdutoRepository repository;
	
	public Produto cadastrarProduto(ProdutoDto produto) {
		var cadastrar = new Produto(produto);
		return repository.save(cadastrar);
	}

	public List<Produto>listarProdutos(){
		return repository.findAll();
	}
	
	public Produto buscarPorId(Long id) {
		Optional<Produto>buscar = repository.findById(id);
		return buscar.orElseThrow(NoSuchElementException::new);
	}
	public Produto atualizarProduto(ProdutoDto produto) {
		var atualizar = new Produto(produto);
		return repository.save(atualizar);
	}
	public void excluir(Long id) {
		repository.deleteById(id);
	}
}
