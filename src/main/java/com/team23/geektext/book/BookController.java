package com.team23.geektext.book;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
