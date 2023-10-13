package com.team23.geektext.BookRating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/bookRating")
public class BookRatingController {
    private final BookRatingService bookRatingService;


    @Autowired
    public BookRatingController(BookRatingService bookRatingService) {

        this.bookRatingService = bookRatingService;
    }

    @PostMapping
    public ResponseEntity<Void> rating(@RequestBody BookRatingRequest request) {
        BookRating bookRating = new BookRating();
        bookRating.setRating(request.getRating());
        bookRating.setUserID(request.getUserID());
        bookRating.setBookID(request.getBookID());
        bookRating.setTimestamp(request.getTimeStamp);

        bookRatingService.save(bookRating);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/average/{bookID}")
    public ResponseEntity<Double> getAverageRating(@PathVariable Long bookID)
    {
        Double averageRating = bookRatingService.getAverageRatingForBook(bookID);
        return ResponseEntity.ok(averageRating);
    }
    public static class BookRatingRequest
    {

        public LocalDateTime getTimeStamp;
        private Long userID;
        private Long bookID;
        private double rating;


        public Long getBookID() {
            return bookID;
        }


        public void setBookID(Long bookID) {
            this.bookID = bookID;
        }


        public Long getUserID() {
            return userID;
        }


        public void setUserID(Long userID) {
            this.userID = userID;
        }


        public double getRating() {
            return rating;
        }


        public void setRating(double rating) {
            this.rating = rating;
        }


        public LocalDateTime getGetTimeStamp() {
            return getTimeStamp;
        }


        public void setGetTimeStamp(LocalDateTime getTimeStamp) {
            this.getTimeStamp = getTimeStamp;
        }
    }
}

