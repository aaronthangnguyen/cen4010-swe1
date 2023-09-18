package com.team23.geektext.repository;

import com.team23.geektext.author.Author;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, UUID> {}
