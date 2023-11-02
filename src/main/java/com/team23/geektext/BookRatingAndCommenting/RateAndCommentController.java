package com.team23.geektext.BookRatingAndCommenting;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/rateAndComment")
public class RateAndCommentController {
    private final RateAndCommentService rateAndCommentService;

    public RateAndCommentController(RateAndCommentService rateAndCommentService) {

        this.rateAndCommentService = rateAndCommentService;}

    @PostMapping("/rate")
    public ResponseEntity<String> rateBook(@RequestBody RateAndComment rateAndComment) {
        try {
            RateAndComment savedRating = rateAndCommentService.rateBook(rateAndComment);
            String responseMessage = "successfully created rating.";
            return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred while rating the book.");
        }
    }

    @PostMapping("/comment")
    public ResponseEntity<String> commentOnBook(@RequestBody RateAndComment rateAndComment) {
        try {
            RateAndComment commentedBook = rateAndCommentService.commentOnBook(rateAndComment);
            String responseMessage = "successfully created comment.";
            return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred while commenting on the book.");
        }
    }

    @GetMapping("/average/{bookID}")
    public ResponseEntity<?> getAverageRating(@PathVariable Long bookID) {
        try {
            Double averageRating = rateAndCommentService.getAverageRatingForBook(bookID);
            if (averageRating == null) {
                String errorMessage = "No ratings found for book with ID '" + bookID + "'.";
                return new ResponseEntity<String>(errorMessage, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Double>(averageRating, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred while fetching average rating.");
        }
    }
    @GetMapping("/ratings/{bookID}")
    public ResponseEntity<?> getRatingsAndCommentsForBook(@PathVariable Long bookID) {
        try {
            List<RateAndComment> ratingsAndComments = rateAndCommentService.getRatingsAndCommentsForBook(bookID);
            if (ratingsAndComments.isEmpty()) {
                String errorMessage = "No ratings or comments found for book with ID '" + bookID + "'.";
                return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(ratingsAndComments, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred while fetching ratings and comments for the book.");
        }
    }
}


