package com.team23.geektext.book;

import com.team23.geektext.exception.AuthorNotFoundException;
import com.team23.geektext.exception.DuplicateIsbnException;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> createNewBook(@RequestBody Book book) {
        try {
            Book savedBook = bookService.createNewBook(book);
            String responseMessage = "Book '" + savedBook.getName() + "' successfully created.";
            return ResponseEntity.status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(responseMessage);
        } catch (DuplicateIsbnException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(e.getMessage());
        } catch (AuthorNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("An unexpected error occurred.");
        }
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<?> getBookByIsbn(@PathVariable String isbn) {
        Optional<Book> bookOptional = bookService.getBookByIsbn(isbn);
        if (bookOptional.isEmpty()) {
            String errorMessage = "The requested book with ISBN '" + isbn + "' does not exist.";
            return new ResponseEntity<String>(errorMessage, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Book>(bookOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Book>> getAllBooksByGenre(@PathVariable String genre) {
        List<Book> books = bookService.getAllBooksByGenre(genre);
        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
    }

    @GetMapping("/top-seller")
    public ResponseEntity<List<Book>> getTop10Sellers() {
        List<Book> books = bookService.getTop10Sellers();
        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
    }



}
