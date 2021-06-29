package com.oguzdirenc.movies.services.impl;

import com.oguzdirenc.movies.domain.MovieCategory;
import com.oguzdirenc.movies.repositories.MovieCategoryRepository;
import com.oguzdirenc.movies.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final MovieCategoryRepository movieCategoryRepository;

    @Override
    public List<MovieCategory> getAllCategories() {
        return movieCategoryRepository.findAll();
    }

    @Override
    public MovieCategory getCategoryByValue(Integer value) {
        return movieCategoryRepository.findByValue(value);
    }

    public CategoryServiceImpl(MovieCategoryRepository movieCategoryRepository) {
        this.movieCategoryRepository = movieCategoryRepository;
    }
}
