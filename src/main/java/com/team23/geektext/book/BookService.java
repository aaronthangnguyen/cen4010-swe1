package com.team23.geektext.book;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.team23.geektext.repository.BookRepository;

@Service
public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }
}
