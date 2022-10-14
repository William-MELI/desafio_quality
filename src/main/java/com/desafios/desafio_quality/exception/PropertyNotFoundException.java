package com.desafios.desafio_quality.exception;

import org.springframework.http.HttpStatus;

public class PropertyNotFoundException extends RuntimeException {

    public PropertyNotFoundException(Long id){
        super("O imóvel com id " + id + " não encontrado");
    }
}