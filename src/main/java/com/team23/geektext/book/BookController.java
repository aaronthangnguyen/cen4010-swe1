package com.team23.geektext.book;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/{isbn}")
    public ResponseEntity<?> getBookById(@PathVariable String isbn) {
        Optional<Book> bookOptional = bookService.getBookByIsbn(isbn);
        if (bookOptional.isEmpty()) {
            return new ResponseEntity<String>("Book not found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Book>(bookOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createNewBook(@RequestBody Book book) {
        if (bookService.createNewBook(book) == null) {
            return new ResponseEntity<>("Failed to create book.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Book successfully created.", HttpStatus.CREATED);
    }
}
