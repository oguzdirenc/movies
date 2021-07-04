package com.oguzdirenc.movies.repositories;

import com.oguzdirenc.movies.domain.Actor;
import com.oguzdirenc.movies.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ActorRepository extends JpaRepository<Actor, UUID> {
    boolean existsByActorName(String actorName);
    Actor findByActorName(String actorName);

    @Query("select x from Actor x where x.actorName like %:search%")
    List<Actor> movieSearchByActorName(String search);
}
