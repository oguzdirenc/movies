package com.oguzdirenc.movies.enums;

import com.oguzdirenc.movies.domain.*;
import lombok.Getter;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

@Getter
public enum Type {

    ACTION(0,"Aksiyon"),
    COMEDY(1,"Komedi"),
    HORROR(2,"Korku"),
    ADVENTURE(3,"Macera"),
    BIOGRAPHY(4,"Biyografi"),
    CRIME(5,"Suç"),
    DOCUMENTARY(6,"Belgesel"),
    DRAMA(7,"Dram"),
    FANTASY(8,"Fantastik"),
    HISTORY(9,"Tarih"),
    MYSTERY(10,"Gizem"),
    ROMANCE(11,"Romantik"),
    SCIENCE(12,"Bilim-Kurgu"),
    ANIMATION(13,"Animasyon");


    private int value;
    private String description;

    Type(int value, String description) {
        this.value = value;
        this.description = description;
    }

    static Map<Integer,MovieCategory> categoryMap = new HashMap<>();

    public static Map<Integer, MovieCategory> get(){
        return categoryMap;
    }

    static {
        for (Type type : EnumSet.allOf(Type.class)){
            MovieCategory cat = MovieCategory.builder()
                    .value(type.getValue())
                    .description(type.getDescription())
                    .build();
            categoryMap.put(type.value, cat);
        }
    }
}
