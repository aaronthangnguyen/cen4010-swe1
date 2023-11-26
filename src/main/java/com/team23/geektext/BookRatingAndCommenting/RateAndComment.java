package com.team23.geektext.BookRatingAndCommenting;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
public class RateAndComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    // fields
    private UUID id;

    private double rating;
    private Long userID;
    private UUID bookID;
    private LocalDateTime timestamp;
    private String comment;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public UUID getBookID() {
        return bookID;
    }

    public void setBookID(UUID bookID) {
        this.bookID = bookID;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getComment() {
        return comment;
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

