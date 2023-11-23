package com.team23.geektext.Review;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    private UUID bookID;

    @Column private UUID userID;
    private double rating;
    private String comment;

    @CreationTimestamp private LocalDateTime timestamp;

    public Review(double rating, UUID userID, UUID bookID, String comment) {
        this.rating = rating;
        this.userID = userID;
        this.bookID = bookID;
        this.comment = comment;
    }

    public Review() {}

    public double getRating() {
        return rating;
    }

    public UUID getUserID() {
        return userID;
    }

    public UUID getBookID() {
        return bookID;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getComment() {
        return comment;
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
        return "BookRating{"
                + ", rating: "
                + rating
                + ", userID: "
                + userID
                + ", bookID: "
                + bookID
                + ", Timestamp: "
                + timestamp
                + ", comment: "
                + comment
                + '}';
    }
}
