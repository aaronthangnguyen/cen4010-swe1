package com.team23.geektext.BookRating;




import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;


@RestController
@RequestMapping(path = "api/bookRating")
public class BookRatingController {
    private final BookRatingService bookRatingServce;


    public BookRatingController(BookRatingService bookRatingServce) {
        this.bookRatingServce = bookRatingServce;
    }


    @GetMapping
    public List<BookRating> getBookRating() {
        return List.of(
                new BookRating(
                        1L,
                        3,
                        7685,
                        38293


                )
        );
    }
}
