package com.oguzdirenc.movies.services.impl;

import com.oguzdirenc.movies.domain.Director;
import com.oguzdirenc.movies.domain.Movie;
import com.oguzdirenc.movies.repositories.DirectorRepository;
import com.oguzdirenc.movies.repositories.MovieRepository;
import com.oguzdirenc.movies.services.DirectorService;
import com.oguzdirenc.movies.services.MovieService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
public class DirectorServiceImpl implements DirectorService {

    private final MovieService movieService;
    private final DirectorRepository directorRepository;
    private final MovieRepository movieRepository;


    @Override
    public void saveDirector(String[] directors, UUID movieId) {
    for (String directorName : directors){
        Movie movie = movieService.getMovieById(movieId);
        if(directorRepository.existsByDirectorName(directorName)){
            Director director = directorRepository.findByDirectorName(directorName);

            directorRepository.save(director);
            movie.getDirectorSet().add(director);
            movieRepository.save(movie);
        }else {
            Director newDirector = Director.builder()
                    .directorName(directorName)
                    .movieSet(new HashSet<>())
                    .build();
            directorRepository.save(newDirector);
            movie.getDirectorSet().add(newDirector);
            movieRepository.save(movie);
        }
    }
    }

    @Override
    public List<Director> getAllDirectorsOrderByName() {
        return directorRepository.getDirectorByName();
    }

    public DirectorServiceImpl(MovieService movieService, DirectorRepository directorRepository, MovieRepository movieRepository) {
        this.movieService = movieService;
        this.directorRepository = directorRepository;
        this.movieRepository = movieRepository;
    }
}
