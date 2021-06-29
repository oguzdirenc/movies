package com.oguzdirenc.movies.domain;

import lombok.*;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Entity
@Getter
@Setter
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    private UUID movieId;

    private String movieName;
    private double imdb;
    private Long releaseDate;

    @Lob
    private String thumbnail;

    private Date createdAt;
    private Date updatedAt;


    @PrePersist
    protected void onCreate() {this.createdAt = new Date();}

    @PreUpdate
    protected void onUpdate() {this.updatedAt = new Date();}

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "movie")
    private Set<Comment> commentList = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "movie_actor",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "actor_id")})
    private Set<Actor> actorSet =new HashSet<Actor>();

    @ManyToMany
    @JoinTable(name = "movie_director",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "director_id")})
    private Set<Director> directorSet = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "movie_categories",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private Set<MovieCategory> categorySet = new HashSet<>();

    public Movie(UUID movieId,
                 String movieName,
                 double imdb,
                 Long releaseDate,
                 String thumbnail,
                 Date createdAt,
                 Date updatedAt,
                 Set<Comment> commentList,
                 Set<Actor> actorSet,
                 Set<Director> directorSet,
                 Set<MovieCategory> categorySet) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.imdb = imdb;
        this.releaseDate = releaseDate;
        this.thumbnail = thumbnail;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.commentList = commentList;
        this.actorSet = actorSet;
        this.directorSet = directorSet;
        this.categorySet = categorySet;
    }

    public Movie() {

    }

}
