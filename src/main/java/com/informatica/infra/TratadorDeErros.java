package com.informatica.infra;


import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.informatica.dto.ErroTratado;




@ControllerAdvice
public class TratadorDeErros{

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ErroTratado>idNaoEncontrado(){		
		var response = new ErroTratado(HttpStatus.NOT_FOUND,"ID não encontrado");
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?>tratador400(MethodArgumentNotValidException ex){
		var erros = ex.getFieldErrors();
		return ResponseEntity.badRequest().body(erros.stream().map(TratandoErros::new).toList());		
	}
	public record TratandoErros(String campo,String mensagem) {
		public TratandoErros(FieldError erro) {
			this(erro.getField(),erro.getDefaultMessage());
		}
  }
	    
	
}
