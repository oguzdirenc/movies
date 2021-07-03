package com.oguzdirenc.movies.services.impl;

import com.oguzdirenc.movies.services.ValidationService;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Service
public class ValidationServiceImpl implements ValidationService {
    @Override
    public List<String> getErrorList(BindingResult bindingResult) {

        List<String> errors = new ArrayList<>();

        for (Object object : bindingResult.getFieldErrors()){
            FieldError fieldError = (FieldError)object;
            errors.add(fieldError.getDefaultMessage());
        }
        return errors;
    }
}
