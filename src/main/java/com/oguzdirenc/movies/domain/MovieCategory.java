package com.oguzdirenc.movies.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
public class MovieCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    private UUID movieCategoryId;

    private Integer value;
    private String description;

    private Date createdAt;
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {this.createdAt = new Date();}

    @PreUpdate
    protected void onUpdate() {this.updatedAt = new Date();}

    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "categorySet")
    private Set<Movie> movieCategorySet = new HashSet<>();

    public MovieCategory(UUID movieCategoryId,
                         Integer value,
                         String description,
                         Date createdAt,
                         Date updatedAt,
                         Set<Movie> movieCategorySet) {
        this.movieCategoryId = movieCategoryId;
        this.value = value;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.movieCategorySet = movieCategorySet;
    }

    public MovieCategory() {
    }
}
