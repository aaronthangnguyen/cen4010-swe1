package com.team23.geektext.BookRating;


import java.time.LocalDateTime;

public class BookRating {
    private Long id;
    private Integer rating;
    private Integer userID;
    private Integer bookID;
    private LocalDateTime timestamp;
    public BookRating()
    {}


    public BookRating(Long id, Integer rating, Integer userID, Integer bookID)
    {
        this.id = id;
        this.rating = rating;
        this.userID = userID;
        this.bookID = bookID;
        this.timestamp = timestamp;
    }
    public BookRating(Integer rating, Integer userID, Integer bookID, LocalDateTime timestamp)
    {
        this.rating = rating;
        this.userID = userID;
        this.bookID = bookID;
        this.timestamp  = timestamp;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Integer getRating() {
        return rating;
    }


    public Integer getUserID() {
        return userID;
    }


    public void setUserID(Integer userID) {
        this.userID = userID;
    }


    public void setRating(Integer rating) {
        this.rating = rating;
    }


    public Integer getBookID() {
        return bookID;
    }


    public void setBookID(Integer bookID) {
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
                "id=" + id +
                ", rating=" + rating +
                ", userID=" + userID +
                ", bookID=" + bookID +
                ", Timestamp=" + timestamp +
                '}';
    }
}