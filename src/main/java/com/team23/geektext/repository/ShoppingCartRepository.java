package com.team23.geektext.repository;

import com.team23.geektext.author.Author;
import com.team23.geektext.book.Book;
import com.team23.geektext.shopping.Shopping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ShoppingCartRepository extends JpaRepository<Shopping, Long> {}

