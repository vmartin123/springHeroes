package com.vm.heroes.config;

import com.vm.heroes.dto.ApiError;
import com.vm.heroes.utils.CustomStatusCodes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger log = Logger.getLogger(RestResponseEntityExceptionHandler.class.getName());

    // handles all the exceptions
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleControllerException(HttpServletRequest req, Throwable ex) {

        log.severe(ex.getMessage());
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), CustomStatusCodes.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // handles HTTP codes exceptions for the API
    @ExceptionHandler(StatusCodeException.class)
    protected ResponseEntity<Object> handleStatusCode(StatusCodeException ex, WebRequest request) {

        log.severe(ex.getMessage());
        ApiError apiError = new ApiError(ex.getRawStatusCode() + " " + ex.getStatusCode().getReasonPhrase(), ex.getStatusText());

        return handleExceptionInternal(ex, apiError, new HttpHeaders(), ex.getStatusCode(), request);
    }
}
