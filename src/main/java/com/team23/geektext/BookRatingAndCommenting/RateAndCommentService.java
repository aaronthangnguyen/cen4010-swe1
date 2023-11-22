package com.team23.geektext.BookRatingAndCommenting;

import com.team23.geektext.repository.RateAndCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import java.time.LocalDateTime;

@Service
public class RateAndCommentService {
    private final RateAndCommentRepository rateAndCommentRepository;
    @Autowired
    public RateAndCommentService(RateAndCommentRepository rateAndCommentRepository) {
        this.rateAndCommentRepository = rateAndCommentRepository;
    }
    public void save(RateAndComment rateAndComment)
    {
        rateAndCommentRepository.save(rateAndComment);
    }
    public Double getAverageRatingForBook(Long bookID)
    {
        return rateAndCommentRepository.findAverageRatingByBookID(bookID);
    }
    public List<RateAndComment> getRatingsAndCommentsForBook(Long bookID) {
        return rateAndCommentRepository.findByBookID(bookID);
    }
    public void rateBook(RateRequest rateRequest) {
        RateAndComment rateAndComment = new RateAndComment();
        rateAndComment.setRating(rateRequest.getRating());
        rateAndComment.setUserID(rateRequest.getUserID());
        rateAndComment.setBookID(rateRequest.getBookID());
        rateAndComment.setTimestamp(LocalDateTime.now());
        rateAndCommentRepository.save(rateAndComment);

    }
    public RateAndComment commentOnBook(CommentRequest commentRequest) {
        RateAndComment rateAndComment = new RateAndComment();
        rateAndComment.setUserID(commentRequest.getUserID());
        rateAndComment.setBookID(commentRequest.getBookID());
        rateAndComment.setComment(commentRequest.getComment());
        rateAndComment.setTimestamp(LocalDateTime.now());

        return rateAndCommentRepository.save(rateAndComment);
    }
}




