package com.team23.geektext.repository;

import com.team23.geektext.BookRating.BookRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRatingRepository extends JpaRepository<BookRating, Long> {
}
