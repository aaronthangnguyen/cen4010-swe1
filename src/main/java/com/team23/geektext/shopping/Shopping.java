package com.team23.geektext.shopping;

import com.team23.geektext.book.Book;
import com.team23.geektext.profile.User;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shopping_carts")
public class Shopping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "cart")
    private User user;

    @ManyToMany
    private List<Book> books = new ArrayList<>();

    public Shopping() {}

    public Shopping(User user) {
        this.user = user;
    }

    public List<Book> getBooks() {
        return books;
    }
}
