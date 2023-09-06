package com.team23.geektext.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.team23.geektext.book.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}