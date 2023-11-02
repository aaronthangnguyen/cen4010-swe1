package com.team23.geektext.BookRatingAndCommenting;


import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Entity
public class RateAndComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    // fields

    @Column
    private double rating;
    private Long userID;
    private Long bookID;
    private String comment;

    @CreationTimestamp
    private LocalDateTime timestamp;

    public RateAndComment(
            double rating,
            Long userID,
            Long bookID,
            String comment) {
        this.rating = rating;
        this.userID = userID;
        this.bookID = bookID;
        this.comment = comment;
    }

    public RateAndComment() {

    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public void setBookID(Long bookID) {
        this.bookID = bookID;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    @Override
    public String toString() {
        return "BookRating{" +
                ", rating: " + rating +
                ", userID: " + userID +
                ", bookID: " + bookID +
                ", Timestamp: " + timestamp +
                ", comment: " + comment +
                '}';

         }
    }

