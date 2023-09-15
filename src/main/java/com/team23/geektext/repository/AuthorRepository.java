package com.team23.geektext.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.team23.geektext.author.Author;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
}