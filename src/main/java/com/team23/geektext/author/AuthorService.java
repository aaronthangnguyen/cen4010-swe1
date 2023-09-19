package com.team23.geektext.author;

import com.team23.geektext.repository.AuthorRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author createNewAuthor(Author author) {
        return authorRepository.save(author);
    }
}
