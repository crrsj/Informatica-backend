package com.informatica.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.informatica.dto.ProdutoDto;
import com.informatica.service.ProdutoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("produto")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProdutoController {
	
	private final ProdutoService service;
	
	@PostMapping
	@Operation(summary = "Rota responsável pelo cadastro de produtos") 
    @ApiResponse(responseCode = "201",description = "usuário cadastrado com sucesso",content = {
   		@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })       
	public ResponseEntity<ProdutoDto>cadastrarProduto(@RequestBody @Valid ProdutoDto produto){
		var cadastro = service.cadastrarProduto(produto);
		var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
				.buildAndExpand(cadastro.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProdutoDto(cadastro));
	}
 
	@GetMapping
	@Operation(summary = "Rota responsável pela busca de todos os produtos")
	@ApiResponse(responseCode = "200",description = " sucesso",content = {
	    		@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
	   })           
	public ResponseEntity<List<ProdutoDto>>listarProdutos(){
		var listar = service.listarProdutos().stream().map(ProdutoDto::new).toList();
		return ResponseEntity.ok(listar);
		
	}
	
	@GetMapping("{id}")
	@Operation(summary = "Rota responsável pela busca do produto pelo id")
	@ApiResponse(responseCode = "200",description = " sucesso",content = {
	    		@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
	   })           
	public ResponseEntity<ProdutoDto>buscarPorId(@PathVariable Long id){
		var buscar = service.buscarPorId(id);
		return ResponseEntity.ok().body(new ProdutoDto(buscar));
	}
	
	@PutMapping 
	@Operation(summary = "Rota responsável por atualizar o produto")
	@ApiResponse(responseCode = "200",description = " sucesso",content = {
	    		@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
	   })           
	public ResponseEntity<ProdutoDto>atualizarProduto(@RequestBody @Valid ProdutoDto produto){
		var atualizar = service.atualizarProduto(produto);
		return ResponseEntity.ok().body(new ProdutoDto(atualizar));
	}
	
	@DeleteMapping("{id}")
	@Operation(summary = "Rota responsável por deletar um produto pelo id")
	@ApiResponse(responseCode = "204",description = " sem conteúdo",content = {
	    		@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
	    })        
	public ResponseEntity<Void>excluir(@PathVariable Long id){
		service.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
