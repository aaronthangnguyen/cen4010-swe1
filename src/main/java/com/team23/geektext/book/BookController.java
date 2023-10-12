package com.team23.geektext.book;

import com.team23.geektext.exception.AuthorNotFoundException;
import com.team23.geektext.exception.DuplicateIsbnException;
import java.util.List;
import java.util.Optional;
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
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
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
            String errorMessage = "The requested book with ISBN '" + isbn + "' does not exist.";
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookOptional.get(), HttpStatus.OK);
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
    public ResponseEntity<String> discountBooksByPublisher(@RequestBody DiscountDTO discountDto) {
        try {
            double discountPercent = discountDto.getDiscountPercent();
            String publisher = discountDto.getPublisher();
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
}
