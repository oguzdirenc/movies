package com.oguzdirenc.movies.services;

import com.oguzdirenc.movies.domain.Director;

import java.util.List;
import java.util.UUID;

public interface DirectorService {
    void saveDirector(String[] directors, UUID movieId);
    List<Director> getAllDirectorsOrderByName();
}
