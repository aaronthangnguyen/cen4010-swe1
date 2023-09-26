package com.team23.geektext.book;

import com.team23.geektext.repository.BookRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getAllBooksByGenre(String genre) {
        return bookRepository.findAllByGenre(genre);
    }

    public List<Book> getTop10Sellers() {
        return bookRepository.findTop10ByOrderByCopiesSoldDesc();
    }
}
