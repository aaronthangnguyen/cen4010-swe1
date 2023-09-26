package com.team23.geektext.repository;

import com.team23.geektext.book.Book;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, UUID> {}
