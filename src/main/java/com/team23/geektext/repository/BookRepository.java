package com.team23.geektext.repository;

import com.team23.geektext.book.Book;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, UUID> {
    List<Book> findAllByGenre(String genre);

    List<Book> findTop10ByOrderByCopiesSoldDesc();
}
