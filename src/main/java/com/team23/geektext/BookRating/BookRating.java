package com.team23.geektext.BookRating;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;


@Entity
public class BookRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private double rating;
    private Long userID;
    private Long bookID;
    private LocalDateTime timestamp;
    public BookRating()
    {}


    public BookRating(int rating, Long userID, Long bookID)
    {
        this.rating = rating;
        this.userID = userID;
        this.bookID = bookID;
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

    public Long getBookID() {
        return bookID;
    }

    public void setBookID(Long bookID) {
        this.bookID = bookID;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "BookRating{" +
                ", rating=" + rating +
                ", userID=" + userID +
                ", bookID=" + bookID +
                ", Timestamp=" + timestamp +
                '}';
    }
}
