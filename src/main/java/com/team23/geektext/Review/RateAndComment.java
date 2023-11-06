package com.team23.geektext.Review;

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
    private UUID bookID;

    @Column
    private UUID userID;
    private double rating;
    private String comment;

    @CreationTimestamp
    private LocalDateTime timestamp;

    public RateAndComment(
            double rating,
            UUID userID,
            UUID bookID,
            String comment) {
        this.rating = rating;
        this.userID = userID;
        this.bookID = bookID;
        this.comment = comment;
    }

    public RateAndComment() {

    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public void setBookID(UUID bookID) {
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

