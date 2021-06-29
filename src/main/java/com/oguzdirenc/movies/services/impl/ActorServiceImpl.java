package com.oguzdirenc.movies.services.impl;

import com.oguzdirenc.movies.domain.Actor;
import com.oguzdirenc.movies.domain.Movie;
import com.oguzdirenc.movies.repositories.ActorRepository;
import com.oguzdirenc.movies.repositories.MovieRepository;
import com.oguzdirenc.movies.services.ActorService;
import com.oguzdirenc.movies.services.MovieService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.UUID;

@Service
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;
    private final MovieService movieService;
    private final MovieRepository movieRepository;


    @Override
    public void saveActor(String[] actorArray, UUID movieId) {
        for(String actorName : actorArray) {
            Movie movie = movieService.getMovieById(movieId);
            if (actorRepository.existsByActorName(actorName)) {
           Actor actor = actorRepository.findByActorName(actorName);
                actorRepository.save(actor);
           movie.getActorSet().add(actor);
           movieRepository.save(movie);


            }else {
                Actor newActor = Actor.builder()
                        .actorName(actorName)
                        .movieSet(new HashSet<>())
                        .build();
                actorRepository.save(newActor);
                movie.getActorSet().add(newActor);
                movieRepository.save(movie);

            }
        }
    }


    public ActorServiceImpl(ActorRepository actorRepository, MovieService movieService, MovieRepository movieRepository) {
        this.actorRepository = actorRepository;
        this.movieService = movieService;
        this.movieRepository = movieRepository;
    }
}
