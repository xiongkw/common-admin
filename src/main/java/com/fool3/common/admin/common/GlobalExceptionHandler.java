package com.fool3.common.admin.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public Object onNotFoundException(NotFoundException e, HttpServletRequest request) {
        log.warn("Request: [{}] Not found!", request.getRequestURI(), e);
        return Response.fail(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ExceptionHandler(CodedException.class)
    public Object onCodedException(CodedException e, HttpServletRequest request) {
        log.warn("Request: [{}] failed!", request.getRequestURI(), e);
        return Response.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Object onCodedException(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        log.warn("Request: [{}] failed!", request.getRequestURI(), e);
        return Response.fail(HttpStatus.METHOD_NOT_ALLOWED.value(), HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object onNotFoundException(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.warn("Bad Request: [{}]", request.getRequestURI(), e);
        StringBuilder sb = new StringBuilder("[");
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        for (ObjectError error : allErrors) {
            sb.append(error.getDefaultMessage()).append(",");
        }
        sb.deleteCharAt(sb.length() - 1).append("]");
        return Response.fail(HttpStatus.BAD_REQUEST.value(), sb.toString());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object onException(Exception e, HttpServletRequest request) {
        log.error("Internal server error, request: [{}]!", request.getRequestURI(), e);
        return Response.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error");
    }

}
