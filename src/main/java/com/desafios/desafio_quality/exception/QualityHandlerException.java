package com.desafios.desafio_quality.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class QualityHandlerException extends ResponseEntityExceptionHandler {

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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        return new ResponseEntity<>(QualityExceptionDetails.builder()
                .title("Parâmetros inválidos")
                .status(status.value())
                .message(errors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(";")))
                .timeStamp(LocalDateTime.now())
                .build(), status);
    }

}
