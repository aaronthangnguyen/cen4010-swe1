package com.team23.geektext.repository;

import com.team23.geektext.book.Book;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface BookRepository extends JpaRepository<Book, UUID> {
    List<Book> findAllByGenre(String genre);

    List<Book> findTop10ByOrderByCopiesSoldDesc();

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
    boolean existsByIsbn(String isbn);

    Optional<Book> findByIsbn(String isbn);

}
