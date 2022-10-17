package com.desafios.desafio_quality.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class QualityHandlerException {

    @ExceptionHandler(NoRoomFoundInPropertyException.class)
    public ResponseEntity<QualityExceptionDetails> handlerNotFoundException(NoRoomFoundInPropertyException ex) {
        QualityExceptionDetails exceptionDetails = QualityExceptionDetails.builder()
                .title("Objeto não encontrado")
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PropertyNotFoundException.class)
    public ResponseEntity<QualityExceptionDetails> handlerPropertyNotFoundException(PropertyNotFoundException ex) {
        QualityExceptionDetails exceptionDetails = QualityExceptionDetails.builder()
                .title("Imóvel não encontrado")
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
    }
}
