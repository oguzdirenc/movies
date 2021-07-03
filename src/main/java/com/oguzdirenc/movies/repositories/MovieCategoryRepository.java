package com.oguzdirenc.movies.repositories;

import com.oguzdirenc.movies.domain.MovieCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MovieCategoryRepository extends JpaRepository<MovieCategory, UUID> {

    boolean existsByValue(Integer value);
    MovieCategory findByValue(Integer value);

    @Query("select x from MovieCategory x where x.movieCategorySet is not empty order by x.description")
    List<MovieCategory> getMovieCategoriesOrderByName();
}
