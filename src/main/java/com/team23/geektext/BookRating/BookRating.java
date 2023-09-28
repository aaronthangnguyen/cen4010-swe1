package com.team23.geektext.BookRating;


public class BookRating {
    private Long id;
    private Integer rating;
    private Integer userID;
    private Integer bookID;


    public BookRating()
    {}


    public BookRating(Long id, Integer rating, Integer userID, Integer bookID)
    {
        this.id = id;
        this.rating = rating;
        this.userID = userID;
        this.bookID = bookID;
    }
    public BookRating(Integer rating, Integer userID, Integer bookID)
    {
        this.rating = rating;
        this.userID = userID;
        this.bookID = bookID;
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


    @Override
    public String toString() {
        return "BookRating{" +
                "id=" + id +
                ", rating=" + rating +
                ", userID=" + userID +
                ", bookID=" + bookID +
                '}';
    }
}