package com.senla.carservice.spring.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
/*@Slf4j
@ControllerAdvice*/
class GlobalDefaultExceptionHandler {

 /*   @ExceptionHandler(value = Exception.class)
    public String handle(Exception ex){
        log.error(ex.getLocalizedMessage());
        return "error";
    }*/
}
