package com.team23.geektext.repository;

import com.team23.geektext.BookRatingAndCommenting.RateAndComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.UUID;

public interface RateAndCommentRepository extends JpaRepository<RateAndComment, UUID> {

    List<RateAndComment> findByBookId(UUID bookId);
    @Query("SELECT AVG(r.rating) FROM RateAndComment r WHERE r.bookId = ?1")
    Double findAverageRatingByBookID(UUID bookId);
}
