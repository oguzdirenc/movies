package com.oguzdirenc.movies.command;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@Setter
public class MovieCommand {

    @NotBlank(message = "Film adı giriniz.")
    private String movieName;

    @Min(value = 0,message = "Imdb puanı 0-10 aralığında olmalı")
    @Max(value = 10,message = "Imdb puanı 0-10 aralığında olmalı")
    private double imdb;

    private String releaseDate;
    private UUID movieId;
    private String description;

    private Integer type;

    @NotBlank(message = "Yönetmen adı giriniz")
    private String directorName;

    @NotBlank(message = "Oyuncu adı giriniz")
    private String actorName;

   // @NotNull(message = "Film görseli giriniz")
    private MultipartFile image;

    public MovieCommand() {
    }
}
