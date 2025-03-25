package com.learning.book_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessageBody> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // Aqui estamos pegando somente fieldErrors, podem haver outros tipos (classe superior a essa sendo ObjectError) mas n nesse caso
        // portanto não se faz necessário uma conversão para este tipo de erro
        // assim podemos só separar a lista deles que viem em uma stream mapear como quiser e então transformar para uma lista.
        List<String> errorMessages = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();
        ErrorMessageBody errorBody = new ErrorMessageBody(HttpStatus.BAD_REQUEST, String.join(", ", errorMessages));
        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessageBody> handleEntityNotFound(EntityNotFoundException ex) {
        ErrorMessageBody errorBody = new ErrorMessageBody(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<ErrorMessageBody> handleEntityAlreadyExists(EntityAlreadyExistsException ex) {
        ErrorMessageBody errorBody = new ErrorMessageBody(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessageBody> handleGenericException(Exception ex) {
        ErrorMessageBody errorBody = new ErrorMessageBody(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred: " + ex.getMessage());
        return new ResponseEntity<>(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
