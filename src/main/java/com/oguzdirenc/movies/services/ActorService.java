package com.oguzdirenc.movies.services;

import com.oguzdirenc.movies.domain.Actor;
import com.oguzdirenc.movies.domain.Movie;

import java.util.List;
import java.util.UUID;

public interface ActorService {

    void saveActor(String[] actorArray, UUID movieId);
    List<Movie> getActorsMovies(String search);
}
