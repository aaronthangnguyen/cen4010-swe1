package com.team23.geektext.book;

import com.team23.geektext.exception.AuthorNotFoundException;
import com.team23.geektext.exception.DuplicateIsbnException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.team23.geektext.exception.PublisherNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getBooks(
            @RequestParam(name = "author_id", required = false) UUID authorId) {
        if (authorId != null) {
            List<Book> booksByAuthor = bookService.getBooksByAuthorId(authorId);
            return new ResponseEntity<>(booksByAuthor, HttpStatus.OK);
        }

        List<Book> allBooks = bookService.getAllBooks();
        return new ResponseEntity<>(allBooks, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createNewBook(@RequestBody Book book) {
        try {
            Book savedBook = bookService.createNewBook(book);
            String responseMessage = "Book '" + savedBook.getName() + "' successfully created.";
            return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
        } catch (DuplicateIsbnException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (AuthorNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred.");
        }
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<?> getBookByIsbn(@PathVariable String isbn) {
        Optional<Book> bookOptional = bookService.getBookByIsbn(isbn);
        if (bookOptional.isEmpty()) {
            String errorMessage = "Book with ISBN '" + isbn + "' not found.";
            return new ResponseEntity<String>(errorMessage, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Book>(bookOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Book>> getAllBooksByGenre(@PathVariable String genre) {
        List<Book> books = bookService.getAllBooksByGenre(genre);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/top-seller")
    public ResponseEntity<List<Book>> findTop10ByOrderByCopiesSoldDesc() {
        List<Book> books = bookService.findTop10ByOrderByCopiesSoldDesc();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PatchMapping("/discount")
    public ResponseEntity<String> discountBooksByPublisher(@RequestBody Discount discount) {
        try {
            double discountPercent = discount.getDiscountPercent();
            String publisher = discount.getPublisher();
            bookService.updatePricesByPublisher(discountPercent, publisher);
            String responseMessage =
                    "All books by publisher '"
                            + publisher
                            + "' discounted by "
                            + discountPercent
                            + "%.";
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } catch (PublisherNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    "An error occurred while updating prices", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/by-rating")
    public ResponseEntity<List<Book>> getBooksByRatingOrHigher(
            @RequestParam(name = "rating") double rating) {
        try {
            List<Book> books = bookService.getBooksByRatingOrHigher(rating);
            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
