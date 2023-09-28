package com.team23.geektext.BookRating;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping(path = "/api/bookRating")
public class BookRatingController {
    private final BookRatingService bookRatingService;

    @Autowired
    public BookRatingController(BookRatingService bookRatingService) {

       this.bookRatingService = bookRatingService;
    }


    @GetMapping
    public List<BookRating> getBookRating() {

        return bookRatingService.postBookRating();


    }
}
