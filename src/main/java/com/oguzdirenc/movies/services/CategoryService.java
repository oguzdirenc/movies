package com.oguzdirenc.movies.services;

import com.oguzdirenc.movies.domain.MovieCategory;

import java.util.List;

public interface CategoryService {

    List<MovieCategory> getAllCategories();
    MovieCategory getCategoryByValue(Integer value);

}
