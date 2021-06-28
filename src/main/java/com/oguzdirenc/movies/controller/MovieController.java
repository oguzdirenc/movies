package com.oguzdirenc.movies.controller;

import com.oguzdirenc.movies.command.MovieCommand;
import com.oguzdirenc.movies.services.CategoryService;
import com.oguzdirenc.movies.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/movie")
public class MovieController {

    private final MovieService movieService;
    private final CategoryService categoryService;

    @GetMapping("/addMovie")
    public String addMovie(Model model){
        model.addAttribute("movieCommand",new MovieCommand());
        model.addAttribute("categories",categoryService.getAllCategories());
        return "addMovie";
    }

    public MovieController(MovieService movieService, CategoryService categoryService) {
        this.movieService = movieService;
        this.categoryService = categoryService;
    }
}
