package com.team23.geektext.BookRating;

import com.team23.geektext.repository.BookRatingRepository;
import org.springframework.stereotype.Service;

@Service
public class BookRatingService {
    private final BookRatingRepository bookRatingRepository;

    public BookRatingService(BookRatingRepository bookRatingRepository) {
        this.bookRatingRepository = bookRatingRepository;
    }
    public void save(BookRating bookRating)
    {
        bookRatingRepository.save(bookRating);
    }

}




