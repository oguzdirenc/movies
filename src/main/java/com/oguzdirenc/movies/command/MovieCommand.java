package com.oguzdirenc.movies.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
public class MovieCommand {

    @NotBlank(message = "Film adı giriniz.")
    private String movieName;

    @Min(value = 0,message = "Imdb puanı 0-10 aralığında olmalı")
    @Max(value = 10,message = "Imdb puanı 0-10 aralığında olmalı")
    private double imdb;

    private LocalDate releaseDate;
    private Integer type;

    @NotBlank(message = "Yönetmen adı giriniz")
    private String directorName;

    @NotBlank(message = "Oyuncu adı giriniz")
    private String actorName;

    private MultipartFile image;

    public MovieCommand() {
    }
}
