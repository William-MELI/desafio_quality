package com.desafios.desafio_quality.exception;
/**
 * This Exception is used when a Room can not be found given Property reference
 */
public class NoRoomFoundInPropertyException extends RuntimeException {

    public NoRoomFoundInPropertyException(String message) {
        super(message);
    }

}
