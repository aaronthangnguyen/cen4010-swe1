package com.team23.geektext.repository;

import com.team23.geektext.BookRating.BookRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface BookRatingRepository extends JpaRepository<BookRating, Long> {

    List<BookRating> findByBookID(Long bookID); //gets ratings for one book

    @Query("SELECT AVG(r.rating) FROM BookRating r WHERE r.bookID = ?1")
    Double findAverageRatingByBookID(Long bookID);
}
