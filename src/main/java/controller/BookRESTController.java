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
        ResponseEntity<String> responseEntity = null;
        if (result.hasErrors()) {
            String errors = responseConverter.convertErrorListToJson(result.getAllErrors(),ResponseConverter.ERROR);
            responseEntity = new ResponseEntity<String>(errors,HttpStatus.BAD_REQUEST);
        } else {
            try {
                if (bookRepositoryService.hasEntity(new Long(bookDTO.getIsbn()))) {
                    String message = responseConverter.convertMessageToJson(bookDTO, "Este ISBN já foi cadastrado",
                            ResponseConverter.WARNING);
                    responseEntity = new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
                } else {
                    book = bookFactory.createBook(bookDTO);
                    bookRepositoryService.saveEntity(book);

                    String message = responseConverter.convertMessageToJson(book, "Livro cadastrado com sucesso",
                            ResponseConverter.SUCCESS);
                    responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return responseEntity;
    }

    @CacheEvict(value = {"books", "apibook", "apibooks"}, allEntries = true)
    @RequestMapping(value = "/books", method = RequestMethod.PUT)
    public ResponseEntity<String> update(@RequestBody @Valid BookDTO bookDTO, BindingResult result) {
        Book book = null;
        ResponseEntity<String> responseEntity = null;
        if(result.hasErrors()){
            String errors = responseConverter.convertErrorListToJson(result.getAllErrors(),ResponseConverter.ERROR);
            responseEntity = new ResponseEntity<String>(errors,HttpStatus.BAD_REQUEST);
        }else{
            try {
                book = bookFactory.createBook(bookDTO);
                bookRepositoryService.updateEntity(book);

                String message = responseConverter.convertMessageToJson(book, "Livro atualizado com sucesso",
                        ResponseConverter.SUCCESS);
                responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);
            } catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        return responseEntity;
    }

    @CacheEvict(value = {"books", "apibook", "apibooks"}, allEntries = true)
    @RequestMapping(value = "/delete/{isbn}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("isbn") Long isbn){
        ResponseEntity<String> responseEntity = null;
        try {
            Optional<Book> returnedBook = bookRepositoryService.findEntityById(isbn);
            if(returnedBook.isPresent()){
                Book book = returnedBook.get();
                bookRepositoryService.deleteEntity(book);
                String message = responseConverter.convertMessageToJson(book, "Livro deletado com sucesso",
                        ResponseConverter.SUCCESS);
                responseEntity = new ResponseEntity<String>(message, HttpStatus.OK);
            }else{
                String message = responseConverter.convertMessageToJson(returnedBook, "Livro inexistente. Impossível deletar!",
                        ResponseConverter.ERROR);
                responseEntity = new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
        return responseEntity;
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
