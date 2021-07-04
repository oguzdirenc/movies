package com.oguzdirenc.movies.repositories;

import com.oguzdirenc.movies.domain.Director;
import com.oguzdirenc.movies.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DirectorRepository extends JpaRepository<Director, UUID> {
    boolean existsByDirectorName(String directorName);
    Director findByDirectorName(String directorName);

    @Query("select x FROM Director x order by x.directorName")
    List<Director> getDirectorByName();

    @Query("select x from Director x where x.directorName like %:search%")
    List<Director> movieSearchByDirectorName(String search);

}
