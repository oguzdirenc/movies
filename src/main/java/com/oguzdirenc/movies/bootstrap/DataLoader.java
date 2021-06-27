package com.oguzdirenc.movies.bootstrap;

import com.oguzdirenc.movies.domain.MovieCategory;
import com.oguzdirenc.movies.enums.Type;
import com.oguzdirenc.movies.repositories.MovieCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final MovieCategoryRepository movieCategoryRepository;

    @Override
    public void run(String... args) throws Exception {
        Map<Integer, MovieCategory> typeMap = Type.get();
        if( movieCategoryRepository.count() != typeMap.size() ) loadData(typeMap);
    }

public void loadData( Map<Integer,MovieCategory> typeMap) throws IOException{

        for(Integer key : typeMap.keySet()){
            if( !movieCategoryRepository.existsByValue(key)){
               movieCategoryRepository.save(typeMap.get(key));
            }
        }

}
}
