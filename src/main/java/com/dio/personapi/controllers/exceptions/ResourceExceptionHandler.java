package com.dio.personapi.controllers.exceptions;

import com.dio.personapi.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError();
        err.setData(Instant.now());
        err.setStatus(status.value());
        err.setErro("Recurso não encontrado!");
        err.setMensagem(e.getMessage());
        err.setUrl(request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // cod http 422
        ValidationError err = new ValidationError();
        err.setData(Instant.now());
        err.setStatus(status.value());
        err.setErro("Erro de Validação");
        err.setMensagem(e.getMessage());
        err.setUrl(request.getRequestURI());

        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            err.addError(f.getField(), f.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<StandardError> sqlException(SQLException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError();
        err.setData(Instant.now());
        err.setStatus(status.value());
        err.setErro("Erro de chave primária");
        err.setMensagem("Recurso já cadastrado!");
        err.setUrl(request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> illegalArgument(IllegalArgumentException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError();
        err.setData(Instant.now());
        err.setStatus(status.value());
        err.setErro("Argumento ilegal");
        err.setMensagem(e.getMessage());
        err.setUrl(request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
