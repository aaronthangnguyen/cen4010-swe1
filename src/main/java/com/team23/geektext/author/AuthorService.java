package com.team23.geektext.author;

import org.springframework.stereotype.Service;

import com.team23.geektext.repository.AuthorRepository;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author createNewAuthor(Author author) {
        return authorRepository.save(author);
    }
}
