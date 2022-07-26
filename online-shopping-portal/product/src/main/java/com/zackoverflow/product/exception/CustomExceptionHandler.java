package com.zackoverflow.product.exception;

import com.zackoverflow.product.dto.APIError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice // Annotation use for handling exception globally
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(OfferNotValidException.class)
    ResponseEntity<?> offerNotValidHandler(Exception exc, ServletWebRequest request) {

        APIError apiError = new APIError();

        apiError.setStatus(HttpStatus.BAD_REQUEST);
        apiError.setErrors(Arrays.asList(exc.getMessage()));
        apiError.setTimeStamp(LocalDateTime.now());
        apiError.setPathUri(request.getDescription(true));

        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

  /*  This override method from ResponseEntityExceptionHandler will allow us to response with error messages in the
    validation denoted by the @ annotation e.g  @NotNull(message = "Product name cannot be Null!") in Product class
    or the default message provided by the @ annotation */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid
            (MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        List<String> errors = fieldErrors.stream().map(error -> error.getField() + " : " + error.getDefaultMessage())
                .collect(Collectors.toList());

        APIError apiError = new APIError();

        apiError.setStatus(HttpStatus.BAD_REQUEST);
        apiError.setErrors(errors);
        apiError.setTimeStamp(LocalDateTime.now());
        apiError.setPathUri(request.getDescription(false));

        return new ResponseEntity<>(apiError, headers, apiError.getStatus());
    }
}
