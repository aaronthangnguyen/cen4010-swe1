
package com.team23.geektext.BookRating;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookRatingService {
    public List<BookRating> postBookRating() {
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

