package controller;

import entity.Book;
import entity.BookDTO;
import entity.factory.BookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import persistence.service.BookRepositoryService;

import java.util.List;
import java.util.Optional;

/**
 * Created by Guilherme on 22/08/2015.
 */
@RestController
@RequestMapping(value = "/bookcase/api")
public class BookRESTController {

    @Autowired
    BookFactory bookFactory;

    @Autowired
    BookRepositoryService bookRepositoryService;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public List<BookDTO> list() throws Exception {
        return bookRepositoryService.findAllEntities();
    }

    @RequestMapping(value = "/books/{isbn}", method = RequestMethod.GET)
    public ResponseEntity<BookDTO> getBookById(@PathVariable("isbn") Long isbn) throws Exception {
        try {
            Optional<Book> returnedBook = bookRepositoryService.findEntityById(isbn);
            if (returnedBook.isPresent()) {
                BookDTO bookDTO = bookFactory.createBookDTO(returnedBook.get());
                return new ResponseEntity<BookDTO>(bookDTO, HttpStatus.OK);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<BookDTO>(HttpStatus.NOT_FOUND);
    }
}
