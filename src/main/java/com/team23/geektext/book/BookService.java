package com.team23.geektext.book;

import com.team23.geektext.exception.AuthorNotFoundException;
import com.team23.geektext.exception.DuplicateIsbnException;
import com.team23.geektext.repository.AuthorRepository;
import com.team23.geektext.repository.BookRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
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

    public void updatePricesByPublisher(double discountPercent, String publisher) {
        if (discountPercent < 0) {
            throw new IllegalArgumentException("Discount percent must be non-negative");
        }
        if (publisher == null || publisher.trim().isEmpty()) {
            throw new IllegalArgumentException("Publisher cannot be null or blank");
        }

        List<Book> books = bookRepository.findByPublisher(publisher);

        if (books.isEmpty()) {
            throw new PublisherNotFoundException("Publisher not found");
        }

        double discountRate = 1 - (discountPercent / 100);
        for (Book book : books) {
            double newPrice = book.getPrice() * discountRate;
            book.setPrice(newPrice);
            bookRepository.save(book);
        }
    }

    public Optional<Book> getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public List<Book> getAllBooksByGenre(String genre) {
        return bookRepository.findAllByGenre(genre);
    }

    public List<Book> findTop10ByOrderByCopiesSoldDesc() {
        return bookRepository.findTop10ByOrderByCopiesSoldDesc();
    }
}
