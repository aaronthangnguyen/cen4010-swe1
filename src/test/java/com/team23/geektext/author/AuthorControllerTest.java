package com.team23.geektext.author;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorService;

    @Test
    public void testCreateNewAuthor_ValidData() throws Exception {
        String jsonPayload = "{\"firstName\":\"John\",\"lastName\":\"Doe\",\"biography\":\"Biography\",\"publisher\":\"Publisher\"}";

        when(authorService.createNewAuthor(any(Author.class))).thenReturn(new Author());

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/authors")
                .content(jsonPayload)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("Author successfully created."));
    }

    @Test
    public void testCreateNewAuthor_MissingDataFields() throws Exception {
        String jsonPayload = "{\"firstName\":\"John\",\"lastName\":\"Doe\"}";

        when(authorService.createNewAuthor(any(Author.class))).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/authors")
                .content(jsonPayload)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Failed to create author."));
    }

    @Test
    public void testCreateNewAuthor_InvalidData() throws Exception {
        String jsonPayload = "{\"firstName\":\"\",\"lastName\":\"Doe\",\"biography\":\"Biography\",\"publisher\":\"Publisher\"}";

        when(authorService.createNewAuthor(any(Author.class))).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/authors")
                .content(jsonPayload)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Failed to create author."));
    }
}
