package com.team23.geektext.book;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(BookController.class)
public class BookControllerTest {
    @Autowired private MockMvc mockMvc;

    @MockBean private BookService bookService;

    @Test
    public void testGetAllBooks() throws Exception {
        List<Book> books = new ArrayList<>();
        UUID authorId = UUID.randomUUID();
        books.add(
                new Book(
                        "978-0545790352",
                        "Harry Potter and the Sorcerer's Stone",
                        "Fantasy novel",
                        19.99,
                        authorId,
                        "Fantasy",
                        "Bloomsbury Publishing",
                        1997,
                        1000000));
        books.add(
                new Book(
                        "978-1982105402",
                        "1984",
                        "Dystopian novel",
                        15.99,
                        authorId,
                        "Dystopian",
                        "Secker & Warburg",
                        1949,
                        500000));

        when(bookService.getAllBooks()).thenReturn(books);

        mockMvc.perform(get("/api/books").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].isbn").value("978-0545790352"))
                .andExpect(jsonPath("$[0].name").value("Harry Potter and the Sorcerer's Stone"))
                .andExpect(jsonPath("$[0].authorId").value(authorId.toString()))
                .andExpect(jsonPath("$[1].isbn").value("978-1982105402"))
                .andExpect(jsonPath("$[1].name").value("1984"))
                .andExpect(jsonPath("$[1].authorId").value(authorId.toString()));
    }

    @Test
    public void testGetBookByIsbn() throws Exception {
        String isbn = "978-0545790352";
        UUID authorId = UUID.randomUUID();
        Book book =
                new Book(
                        isbn,
                        "Harry Potter and the Sorcerer's Stone",
                        "Fantasy novel",
                        19.99,
                        authorId,
                        "Fantasy",
                        "Bloomsbury Publishing",
                        1997,
                        1000000);

        when(bookService.getBookByIsbn(isbn)).thenReturn(Optional.of(book));

        mockMvc.perform(get("/api/books/{isbn}", isbn).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.isbn").value(isbn))
                .andExpect(jsonPath("$.name").value("Harry Potter and the Sorcerer's Stone"))
                .andExpect(jsonPath("$.authorId").value(authorId.toString()));
    }

    @Test
    public void testCreateNewBook_ValidData() throws Exception {
        String jsonPayload =
                "{\"isbn\":\"978-1234567890\",\"name\":\"Test Book\",\"description\":\"Test"
                    + " Description\",\"price\":29.99,\"authorId\":\"1ebfda72-7f91-4f0a-86c5-7e69e04479c3\",\"genre\":\"Test"
                    + " Genre\",\"publisher\":\"Test"
                    + " Publisher\",\"yearPublished\":2022,\"copiesSold\":100}";

        when(bookService.createNewBook(any(Book.class))).thenReturn(new Book());

        mockMvc.perform(
                        post("/api/books")
                                .content(jsonPayload)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string("Book successfully created."));
    }

    @Test
    public void testCreateNewBook_InvalidData() throws Exception {
        String jsonPayload = "{\"isbn\":\"978-1234567890\",\"name\":\"Test Book\",\"price\":29.99}";

        when(bookService.createNewBook(any(Book.class))).thenReturn(null);

        mockMvc.perform(
                        post("/api/books")
                                .content(jsonPayload)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Failed to create book."));
    }
}
