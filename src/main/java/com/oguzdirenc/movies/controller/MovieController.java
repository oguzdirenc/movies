package com.oguzdirenc.movies.controller;

import com.oguzdirenc.movies.command.MovieCommand;
import com.oguzdirenc.movies.services.CategoryService;
import com.oguzdirenc.movies.services.DirectorService;
import com.oguzdirenc.movies.services.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/api/movie")
public class MovieController {

    private final MovieService movieService;
    private final CategoryService categoryService;
    private final DirectorService directorService;

    @RequestMapping("/movies")
    public String getMovies(Model model){
        model.addAttribute("Movies",movieService.getAllMovies());
        model.addAttribute("Director",directorService.getAllDirectorsOrderByName());
        model.addAttribute("Top5List",movieService.top5Movie());
        model.addAttribute("Type",categoryService.getAllCategories());

        return "movies";
    }

    @GetMapping("/addMovie")
    public String addMovie(Model model){
        model.addAttribute("movieCommand",new MovieCommand());
        model.addAttribute("categories",categoryService.getAllCategories());
        return "addMovie";
    }

    @PostMapping("/addMovie")
    public String addMovie(@Valid @ModelAttribute MovieCommand movieCommand,
                           @RequestParam("file") MultipartFile multipartFile) throws IOException {

            movieService.saveMovie(movieCommand,multipartFile);
            return "index";

    }

    public MovieController(MovieService movieService, CategoryService categoryService, DirectorService directorService) {
        this.movieService = movieService;
        this.categoryService = categoryService;
        this.directorService = directorService;
    }
}
