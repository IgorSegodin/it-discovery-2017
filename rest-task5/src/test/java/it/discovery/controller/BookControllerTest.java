package it.discovery.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.discovery.bootstrap.RestApplication;
import it.discovery.data.model.Book;
import it.discovery.repository.BookRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

/**
 * @author isegodin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = RestApplication.class)
public class BookControllerTest {

    @Autowired
    private WebApplicationContext applicationContext;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private BookRepository bookRepository;

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
    }

    @Test
    public void test_add() throws Exception {
        Book book = new Book();
        book.setId(0);

        Mockito.when(bookRepository.save(Mockito.any())).thenAnswer((invocationOnMock) -> {
            ((Book) invocationOnMock.getArguments()[0]).setId(1);
            return invocationOnMock.getArguments()[0];
        });

        mockMvc.perform(MockMvcRequestBuilders.post("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(book)))
                .andExpect(MockMvcResultMatchers.content().json("1"));
    }

    @Test
//    @WithMockUser(username = "admin", password = "admin")
    public void test_get() throws Exception {
        Book bookResponse = new Book();
        bookResponse.setId(1);
        bookResponse.setName("Book 1");

        Mockito.when(bookRepository.findById(1)).thenReturn(bookResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/book/1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.equalTo(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.equalTo("Book 1")));
    }

    @Test
    public void test_put() throws Exception {
        Book book = new Book(2, "Book 2");

        Mockito.when(bookRepository.save(Mockito.any())).thenAnswer((invocationOnMock) -> invocationOnMock.getArguments()[0]);

        mockMvc.perform(MockMvcRequestBuilders.put("/book/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(book)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.equalTo(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.equalTo("Book 2")));
    }

    @Test
    public void test_delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/book/2"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void test_list() throws Exception {
        Mockito.when(bookRepository.findAll()).thenReturn(Arrays.asList(
                new Book(1, "Book 1"),
                new Book(2, "Book 2")
        ));

        mockMvc.perform(MockMvcRequestBuilders.get("/book"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.books", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.books[0].id", Matchers.equalTo(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.books[0].name", Matchers.equalTo("Book 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.books[1].id", Matchers.equalTo(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.books[1].name", Matchers.equalTo("Book 2")));
    }
}
