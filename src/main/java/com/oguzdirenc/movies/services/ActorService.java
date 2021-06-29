package com.oguzdirenc.movies.services;

import com.oguzdirenc.movies.domain.Actor;

import java.util.UUID;

public interface ActorService {

    void saveActor(String[] actorArray, UUID movieId);
}
