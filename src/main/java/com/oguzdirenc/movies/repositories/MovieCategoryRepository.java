package com.oguzdirenc.movies.repositories;

import com.oguzdirenc.movies.domain.MovieCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MovieCategoryRepository extends JpaRepository<MovieCategory, UUID> {

    boolean existsByValue(Integer value);
}
