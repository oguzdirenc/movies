package com.oguzdirenc.movies.repositories;

import com.oguzdirenc.movies.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MovieRepository extends JpaRepository<Movie, UUID> {
    @Query("SELECT x from Movie x order by x.imdb")
    List<Movie> top5Movie();

    @Query("select x from Movie x order by x.releaseDate desc ")
    List<Movie> newestMovies();

    @Query("select x from Movie x order by x.releaseDate  ")
    List<Movie> oldestMovies();

}
