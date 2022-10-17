package com.desafios.desafio_quality.exception;


/**
 * This Exception is used when a Property can not be found in required operation
 */
public class PropertyNotFoundException extends RuntimeException {

    public PropertyNotFoundException(Long id){
        super("O imóvel com id " + id + " não encontrado");
    }
}
