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
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    private UUID directorId;

    private String directorName;

    private Date createdAt;
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {this.createdAt = new Date();}

    @PreUpdate
    protected void onUpdate() {this.updatedAt = new Date();}

    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "directorSet")
    private Set<Movie> movieSet =new HashSet<Movie>();

    public Director(UUID directorId, String directorName, Date createdAt, Date updatedAt, Set<Movie> movieSet) {
        this.directorId = directorId;
        this.directorName = directorName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.movieSet = movieSet;
    }

    public Director() {
    }
}
