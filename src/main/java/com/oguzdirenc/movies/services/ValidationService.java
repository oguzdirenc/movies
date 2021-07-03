package com.oguzdirenc.movies.services;

import org.springframework.validation.BindingResult;

import java.util.List;

public interface ValidationService {

    List<String> getErrorList(BindingResult bindingResult);

}
