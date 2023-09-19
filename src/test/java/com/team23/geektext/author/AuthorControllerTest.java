package com.team23.geektext.author;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {
    @Autowired private MockMvc mockMvc;

    @MockBean private AuthorService authorService;

    @Test
    public void testCreateNewAuthor_ValidData() throws Exception {
        String jsonPayload =
                "{\"firstName\":\"John\",\"lastName\":\"Doe\",\"biography\":\"Biography\",\"publisher\":\"Publisher\"}";

        when(authorService.createNewAuthor(any(Author.class))).thenReturn(new Author());

        mockMvc.perform(
                        post("/api/authors")
                                .content(jsonPayload)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string("Author successfully created."));
    }

    @Test
    public void testCreateNewAuthor_MissingDataFields() throws Exception {
        String jsonPayload = "{\"firstName\":\"John\",\"lastName\":\"Doe\"}";

        when(authorService.createNewAuthor(any(Author.class))).thenReturn(null);

        mockMvc.perform(
                        post("/api/authors")
                                .content(jsonPayload)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Failed to create author."));
    }

    @Test
    public void testCreateNewAuthor_InvalidData() throws Exception {
        String jsonPayload =
                "{\"firstName\":\"\",\"lastName\":\"Doe\",\"biography\":\"Biography\",\"publisher\":\"Publisher\"}";

        when(authorService.createNewAuthor(any(Author.class))).thenReturn(null);

        mockMvc.perform(
                        post("/api/authors")
                                .content(jsonPayload)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Failed to create author."));
    }

    @Test
    public void testGetAllAuthors_ValidData() throws Exception {
        List<Author> authors = new ArrayList<>();
        authors.add(
                new Author(
                        "J.K.",
                        "Rowling",
                        "Author of the Harry Potter series.",
                        "Bloomsbury Publishing"));
        authors.add(
                new Author(
                        "George",
                        "Orwell",
                        "Famous for '1984' and 'Animal Farm.'",
                        "Secker & Warburg"));
        authors.add(new Author("Agatha", "Christie", "Queen of Mystery Novels.", "HarperCollins"));

        when(authorService.getAllAuthors()).thenReturn(authors);

        mockMvc.perform(get("/api/authors").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].firstName").value("J.K."))
                .andExpect(jsonPath("$[0].lastName").value("Rowling"))
                .andExpect(jsonPath("$[0].biography").value("Author of the Harry Potter series."))
                .andExpect(jsonPath("$[0].publisher").value("Bloomsbury Publishing"))
                .andExpect(jsonPath("$[1].firstName").value("George"))
                .andExpect(jsonPath("$[1].lastName").value("Orwell"))
                .andExpect(jsonPath("$[1].biography").value("Famous for '1984' and 'Animal Farm.'"))
                .andExpect(jsonPath("$[1].publisher").value("Secker & Warburg"))
                .andExpect(jsonPath("$[2].firstName").value("Agatha"))
                .andExpect(jsonPath("$[2].lastName").value("Christie"))
                .andExpect(jsonPath("$[2].biography").value("Queen of Mystery Novels."))
                .andExpect(jsonPath("$[2].publisher").value("HarperCollins"));
    }

    @Test
    public void testGetAllAuthors_NoAuthorsFound() throws Exception {
        when(authorService.getAllAuthors()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/api/authors").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }
}
