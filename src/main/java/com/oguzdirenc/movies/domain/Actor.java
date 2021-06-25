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
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    private UUID actorId;
    private String actorName;

    private Date createdAt;
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {this.createdAt = new Date();}

    @PreUpdate
    protected void onUpdate() {this.updatedAt = new Date();}

    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "actorSet")
    private Set<Movie> movieSet =new HashSet<Movie>();

    public Actor(UUID actorId, String actorName, Date createdAt, Date updatedAt, Set<Movie> movieSet) {
        this.actorId = actorId;
        this.actorName = actorName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.movieSet = movieSet;
    }

    public Actor() {
    }

}
