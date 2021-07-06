package com.oguzdirenc.movies.controller;

import com.oguzdirenc.movies.command.MovieCommand;
import com.oguzdirenc.movies.command.SearchCommand;
import com.oguzdirenc.movies.domain.Movie;
import com.oguzdirenc.movies.services.CategoryService;
import com.oguzdirenc.movies.services.DirectorService;
import com.oguzdirenc.movies.services.MovieService;
import com.oguzdirenc.movies.services.ValidationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.UUID;

@Controller
public class MovieController {

    private final MovieService movieService;
    private final CategoryService categoryService;
    private final DirectorService directorService;
    private final ValidationService validationService;

    @RequestMapping({"/movies", "/index",""})
    public String getMovies(Model model){
        model.addAttribute("Movies",movieService.getAllMovies());
        model.addAttribute("Director",directorService.getAllDirectorsOrderByName());
        model.addAttribute("Top5List",movieService.top5Movie());
        model.addAttribute("Type",categoryService.getNotEmptyCategories());
        model.addAttribute("Search",new SearchCommand());

        return "movies";
    }

    @GetMapping("/newest")
    public String getNewest(Model model){
        model.addAttribute("Movies",movieService.getNewestMovies());
        model.addAttribute("Director",directorService.getAllDirectorsOrderByName());
        model.addAttribute("Top5List",movieService.top5Movie());
        model.addAttribute("Type",categoryService.getNotEmptyCategories());
        model.addAttribute("Search",new SearchCommand());
        return "movies";
    }

    @GetMapping("/oldest")
    public String getOldest(Model model){
        model.addAttribute("Movies",movieService.getOldestMovies());
        model.addAttribute("Director",directorService.getAllDirectorsOrderByName());
        model.addAttribute("Top5List",movieService.top5Movie());
        model.addAttribute("Type",categoryService.getNotEmptyCategories());
        model.addAttribute("Search",new SearchCommand());

        return "movies";
    }


    @PostMapping("/search")
    public String searchMovie(Model model, SearchCommand searchCommand){
        model.addAttribute("Movies",movieService.getSearchResults(searchCommand.getSearch()));
        model.addAttribute("Director",directorService.getAllDirectorsOrderByName());
        model.addAttribute("Top5List",movieService.top5Movie());
        model.addAttribute("Type",categoryService.getNotEmptyCategories());
        model.addAttribute("Search",new SearchCommand());

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
                           BindingResult bindingResult,
                           Model model,
                           @RequestParam("file") MultipartFile multipartFile) throws IOException {
            if(bindingResult.hasErrors()){
                model.addAttribute("errors",validationService.getErrorList(bindingResult));
            return "addMovie";
            }
            movieService.saveMovie(movieCommand,multipartFile);
            return "redirect:/movies";
    }

    @GetMapping("/show/{id}")
    public String showById(@PathVariable UUID id, Model model){
        model.addAttribute("movie",movieService.getMovieById(id));
        model.addAttribute("releaseDate",movieService.getReleaseDateByMovieId(id));
        return "show";
    }

    @RequestMapping("/delete/{id}")
    public String deleteById(@PathVariable UUID id, Model model){
        movieService.deleteById(id);
        return "redirect:/movies";
    }

    @GetMapping("/update/{id}")
    public String updateById(@PathVariable UUID id, Model model){
        model.addAttribute("categories",categoryService.getAllCategories());
        model.addAttribute("movie",movieService.getMovieById(id));
        model.addAttribute("releaseDate",movieService.getReleaseDateByMovieId(id));
        return "update";
    }

    @PostMapping("/update")
    public String updateMovie(@Valid @ModelAttribute Movie updatedMovie){

        movieService.updateMovie(updatedMovie);
        return "redirect:/movies";
    }

    public MovieController(MovieService movieService, CategoryService categoryService, DirectorService directorService, ValidationService validationService) {
        this.movieService = movieService;
        this.categoryService = categoryService;
        this.directorService = directorService;
        this.validationService = validationService;
    }
}
