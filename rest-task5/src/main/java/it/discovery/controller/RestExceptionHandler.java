package it.discovery.controller;

import it.discovery.data.ServiceLayerException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

/**
 * @author isegodin
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ServiceLayerException.class)
    public ResponseEntity<Object> handleException(ServiceLayerException exception, WebRequest request) {
        request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, exception, WebRequest.SCOPE_REQUEST);
        return new ResponseEntity<>(exception.getMessage(), new HttpHeaders(), HttpStatus.valueOf(exception.getHttpCode()));
    }
}
