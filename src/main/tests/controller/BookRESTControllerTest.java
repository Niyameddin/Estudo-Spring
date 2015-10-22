package controller;

import config.WebMvcConfiguration;
import config.DataSourceConfigurationTest;
import config.JPAConfiguration;
import entity.Book;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import persistence.service.BookRepositoryService;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Created by guilherme on 14/09/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebMvcConfiguration.class, JPAConfiguration.class, DataSourceConfigurationTest.class})
@ActiveProfiles("test")
public class BookRESTControllerTest {

    @Autowired
    BookRepositoryService bookRepositoryService;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    @Transactional
    public void testList() throws Exception {
        Assert.assertTrue(true);
    }

    @Test
    public void testGetBookById() throws Exception {
        Optional<Book> book = bookRepositoryService.findEntityById(new Long("1111111111111"));
        Assert.assertTrue(book.isPresent());
    }
}