package com.team23.geektext.book;

import com.team23.geektext.exception.AuthorNotFoundException;
import com.team23.geektext.exception.DuplicateIsbnException;
import com.team23.geektext.repository.AuthorRepository;
import com.team23.geektext.repository.BookRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.team23.geektext.repository.RateAndCommentRepository;

@Service
public class BookService {
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    private RateAndCommentRepository rateAndCommentRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, RateAndCommentRepository rateAndCommentRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.rateAndCommentRepository = rateAndCommentRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book createNewBook(Book book) {
        if (!authorRepository.existsById(book.getAuthorId())) {
            throw new AuthorNotFoundException(
                    "The author with ID '" + book.getAuthorId() + "' does not exist.");
        }
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new DuplicateIsbnException(
                    "A book with ISBN '" + book.getIsbn() + "' already exists.");
        }
        return bookRepository.save(book);
    }

    public Optional<Book> getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public List<Book> getBooksByAuthorId(UUID authorId) {
        return bookRepository.findByAuthorId(authorId);
    }

    public List<Book> getAllBooksByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }

    public List<Book> findTop10ByOrderByCopiesSoldDesc() {
        return bookRepository.findTop10ByOrderByCopiesSoldDesc();
    }

    public void updatePricesByPublisher(double discountPercent, String publisher) {
        List<Book> books = bookRepository.findByPublisher(publisher);
        for (Book book : books) {
            double price = book.getPrice();
            double newPrice = price * (1 - discountPercent / 100);
            book.setPrice(newPrice);
            bookRepository.save(book);
        }
    }
  
    public List<Book> getBooksByRatingOrHigher(double rating) {
        List<String> bookIdStrings = rateAndCommentRepository.findBookIdsByRatingOrHigher(rating);
        return bookIdStrings.stream()
                .map(UUID::fromString)
                .map(bookRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .peek(book -> book.setAverageRating(rateAndCommentRepository.findAverageRatingByBookID(book.getId()))
                )
                .collect(Collectors.toList());
    }

}
