package com.oguzdirenc.movies.services;

import com.oguzdirenc.movies.command.MovieCommand;
import com.oguzdirenc.movies.domain.Movie;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface MovieService {

    void saveMovie(MovieCommand movieCommand, MultipartFile multipartFile) throws IOException;
    Movie getMovieById(UUID movieId);
    List<Movie> getAllMovies();
    List<Movie> top5Movie();
    List<Movie> getNewestMovies();
    List<Movie> getOldestMovies();
    String getReleaseDateByMovieId(UUID movieId);
    List<Movie> getSearchResults(String search);
    void deleteById(UUID id);
}
