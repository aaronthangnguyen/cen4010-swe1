package com.team23.geektext.book;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team23.geektext.exception.AuthorNotFoundException;
import com.team23.geektext.exception.DuplicateIsbnException;
import com.team23.geektext.repository.BookRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(BookController.class)
public class BookControllerTest {
    @Autowired private MockMvc mockMvc;

    @MockBean private BookService bookService;
    @MockBean private BookRepository bookRepository;

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
    public void testGetAllBooks_Empty() throws Exception {
        List<Book> emptyList = new ArrayList<>();

        when(bookService.getAllBooks()).thenReturn(emptyList);

        mockMvc.perform(get("/api/books").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    public void testCreateNewBook_Success() throws Exception {
        Book newBook =
                new Book(
                        "9780439064873",
                        "Harry Potter and the Chamber of Secrets",
                        "The second book in the Harry Potter series.",
                        20.99,
                        UUID.randomUUID(),
                        "Fantasy",
                        "Scholastic",
                        1998,
                        77000000);

        when(bookService.createNewBook(any(Book.class))).thenReturn(newBook);

        ResultActions result =
                mockMvc.perform(
                        post("/api/books")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(newBook)));

        result.andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        content()
                                .string(
                                        "Book 'Harry Potter and the Chamber of Secrets'"
                                                + " successfully created."));
    }

    @Test
    public void testCreateNewBook_AuthorNotFoundException() throws Exception {
        Book newBook =
                new Book(
                        "9780439064873",
                        "Harry Potter and the Chamber of Secrets",
                        "The second book in the Harry Potter series.",
                        20.99,
                        UUID.randomUUID(),
                        "Fantasy",
                        "Scholastic",
                        1998,
                        77000000);

        String errorMessage = "Author not found.";
        when(bookService.createNewBook(any(Book.class)))
                .thenThrow(new AuthorNotFoundException(errorMessage));

        ResultActions result =
                mockMvc.perform(
                        post("/api/books")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(newBook)));

        result.andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(errorMessage));
    }

    @Test
    public void testCreateNewBook_UnhandledException() throws Exception {
        Book newBook =
                new Book(
                        "9780439064873",
                        "Harry Potter and the Chamber of Secrets",
                        "The second book in the Harry Potter series.",
                        20.99,
                        UUID.randomUUID(),
                        "Fantasy",
                        "Scholastic",
                        1998,
                        77000000);

        String errorMessage = "An unexpected error occurred.";
        when(bookService.createNewBook(any(Book.class)))
                .thenThrow(new RuntimeException(errorMessage));

        ResultActions result =
                mockMvc.perform(
                        post("/api/books")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(newBook)));

        result.andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(errorMessage));
    }

    @Test
    public void testCreateNewBook_DuplicateIsbn() throws Exception {
        Book duplicateBook =
                new Book(
                        "9780439064873",
                        "Harry Potter and the Chamber of Secrets",
                        "The second book in the Harry Potter series.",
                        20.99,
                        UUID.fromString("13f350f8-7481-41d3-b716-20c4a7cc2e84"),
                        "Fantasy",
                        "Scholastic",
                        1998,
                        77000000);

        String errorMessage = "A book with ISBN '9780439064873' already exists.";
        when(bookService.createNewBook(any(Book.class)))
                .thenThrow(new DuplicateIsbnException(errorMessage));

        mockMvc.perform(
                        post("/api/books")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(duplicateBook)))
                .andExpect(status().isConflict())
                .andExpect(content().string(errorMessage));
    }

    private String asJsonString(Object obj) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}
