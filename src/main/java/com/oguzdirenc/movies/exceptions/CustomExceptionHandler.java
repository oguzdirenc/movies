package com.oguzdirenc.movies.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ControllerAdvice
@RestController
public class CustomExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final String handleMethodArgumentNotValid(MethodArgumentNotValidException ex, Model model){

        var errors = new HashMap<String,String>();

        ex.getBindingResult().getFieldErrors()
                .forEach(fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage()));
        model.addAttribute("errors",errors);
        return "addMovie";
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final String handleConstraintViolation(ConstraintViolationException ex,Model model){

        var errors = new ArrayList<>();

        ex.getConstraintViolations()
                .forEach(violation -> errors.add(violation.getMessage()));

        model.addAttribute("errors",errors);

        return "addMovie";
    }

}
