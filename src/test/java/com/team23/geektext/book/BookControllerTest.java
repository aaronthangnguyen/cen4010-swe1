package com.team23.geektext.book;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
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
    public void testGetAllBooks_WithBooks() throws Exception {

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
                        1000000,
                        4.5));
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
                        500000,
                        5.6));

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
    public void testGetAllBooks_Empty() throws Exception {
        List<Book> emptyList = new ArrayList<>();

        when(bookService.getAllBooks()).thenReturn(emptyList);

        mockMvc.perform(get("/api/books").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isEmpty());
    }
}
