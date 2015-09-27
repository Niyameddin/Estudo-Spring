package controller;

import entity.Book;
import entity.BookDTO;
import entity.factory.BookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import persistence.service.BookRepositoryService;
import util.ResponseConverter;

import javax.validation.UnexpectedTypeException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by Guilherme on 22/08/2015.
 */
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "/bookcase/api")
public class BookRESTController {

    @Autowired
    BookFactory bookFactory;

    @Autowired
    BookRepositoryService bookRepositoryService;

    @Autowired
    ResponseConverter responseConverter;

    @CacheEvict(value = {"books", "apibook", "apibooks"}, allEntries = true)
    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public ResponseEntity<String> create(@RequestBody @Valid BookDTO bookDTO, BindingResult result) {
        Book book = null;
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors().toString());
            String errors = responseConverter.convertResponseMessage(result.getAllErrors());
            return new ResponseEntity<String>(errors,HttpStatus.ACCEPTED);
        } else try {
            book = bookFactory.createBook(bookDTO);
//            bookRepositoryService.saveEntity(book);
        } catch (UnexpectedTypeException ute) {
            System.err.println(ute.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            return new ResponseEntity<String>("{\"message\":\"ok\"}",HttpStatus.OK);
        }
    }

    @Cacheable(value = "apibook")
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public List<BookDTO> list() throws Exception {
        return bookRepositoryService.findAllEntities();
    }

    @Cacheable(value = "apibooks")
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
