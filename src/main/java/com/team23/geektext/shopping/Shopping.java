package com.team23.geektext.shopping;
import com.team23.geektext.book.Book;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Shopping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToMany
    private List<Book> books = new ArrayList<>();

    public List<Book> getBooks() {
        return books;
    }
}
