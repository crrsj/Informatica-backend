package com.informatica.dto;

import org.springframework.http.HttpStatus;

public record ErroTratado(
		
		HttpStatus status,
		
		String mensagem) {

}
