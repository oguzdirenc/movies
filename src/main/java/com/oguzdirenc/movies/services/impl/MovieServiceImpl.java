package com.oguzdirenc.movies.services.impl;

import com.oguzdirenc.movies.command.MovieCommand;
import com.oguzdirenc.movies.domain.Movie;
import com.oguzdirenc.movies.domain.MovieCategory;
import com.oguzdirenc.movies.repositories.MovieRepository;
import com.oguzdirenc.movies.services.ActorService;
import com.oguzdirenc.movies.services.CategoryService;
import com.oguzdirenc.movies.services.DirectorService;
import com.oguzdirenc.movies.services.MovieService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MovieServiceImpl implements MovieService {

    private final CategoryService categoryService;
    private final MovieRepository movieRepository;
    private final ActorService actorService;
    private final DirectorService directorService;


    @Override
    public void saveMovie(MovieCommand movieCommand, MultipartFile multipartFile) throws IOException {
        Movie newMovie = Movie.builder()
                .movieName(movieCommand.getMovieName())
                .imdb(movieCommand.getImdb())
                .actorSet(new HashSet<>())
                .categorySet(new HashSet<>())
                .directorSet(new HashSet<>())
                .build();

        SimpleDateFormat date = new SimpleDateFormat("yyyy-mm-dd");
        try {
            Date d = date.parse(movieCommand.getReleaseDate());
            long releaseDate = d.getTime();
            newMovie.setReleaseDate(releaseDate);
        }catch (ParseException ex){
            ex.printStackTrace();
        }

        MovieCategory category = categoryService.getCategoryByValue(movieCommand.getType());
        newMovie.getCategorySet().add(category);

        movieCommand.setImage(multipartFile);
        String stringImage = Base64.getEncoder().encodeToString(movieCommand.getImage().getBytes());
        newMovie.setThumbnail(stringImage);


        movieRepository.save(newMovie);

        String[] actors = movieCommand.getActorName().trim().split(",");
        actorService.saveActor(actors,newMovie.getMovieId());

        String[] directors = movieCommand.getDirectorName().trim().split(",");
        directorService.saveDirector(directors,newMovie.getMovieId());

    }

    @Override
    public Movie getMovieById(UUID movieId) {
        return movieRepository.getById(movieId);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> top5Movie() {
        List<Movie> topMovieList =new ArrayList<>();
        List<Movie> top5Movie = movieRepository.top5Movie();

        if (top5Movie.size() >=5){
            for (int i=0;i<5;i++){
                topMovieList.add(top5Movie.get(i));
            }
            return topMovieList;
        }

        return top5Movie;

    }

    public MovieServiceImpl(CategoryService categoryService, MovieRepository movieRepository,@Lazy ActorService actorService,@Lazy DirectorService directorService) {
        this.categoryService = categoryService;
        this.movieRepository = movieRepository;
        this.actorService = actorService;
        this.directorService = directorService;
    }
}
