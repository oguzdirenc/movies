package com.oguzdirenc.movies.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    private UUID commentId;

    private String description;

    private Date createdAt;
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {this.createdAt = new Date();}

    @PreUpdate
    protected void onUpdate() {this.updatedAt = new Date();}


    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    public Comment(UUID commentId, String description, Date createdAt, Date updatedAt, Movie movie) {
        this.commentId = commentId;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.movie = movie;
    }

    public Comment() {
    }
}
