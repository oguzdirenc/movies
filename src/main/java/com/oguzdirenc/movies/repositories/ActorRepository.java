package com.oguzdirenc.movies.repositories;

import com.oguzdirenc.movies.domain.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ActorRepository extends JpaRepository<Actor, UUID> {
    boolean existsByActorName(String actorName);
    Actor findByActorName(String actorName);
}
