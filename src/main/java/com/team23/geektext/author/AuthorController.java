package com.team23.geektext.author;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<String> createNewAuthor(@RequestBody Author author) {
        if (authorService.createNewAuthor(author) == null) {
            return new ResponseEntity<>("Failed to create author.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Author successfully created.", HttpStatus.CREATED);
    }
}
