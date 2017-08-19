package it.discovery.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author isegodin
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(value = Exception.class)
//    protected ResponseEntity<Object> handleMyException(Exception exception, WebRequest request) {
//        return null;
//    }
}
