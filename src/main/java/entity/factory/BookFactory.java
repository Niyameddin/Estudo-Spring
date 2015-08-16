package entity.factory;

import entity.Book;
import entity.BookDTO;
import org.springframework.stereotype.Service;

/**
 * Created by Guilherme on 15/08/2015.
 */
@Service
public class BookFactory {
    public Book createBook(BookDTO bookDTO){
        Book newBook = new Book();
            newBook.setIsbn(new Long(bookDTO.getIsbn()));
            newBook.setTitle(bookDTO.getTitle());
            newBook.setAuthor(bookDTO.getAuthor());
            newBook.setEdition(Integer.parseInt(bookDTO.getEdition()));
        return newBook;
    }

    public BookDTO createBookDTO(Book book){
        BookDTO newBookDTO = new BookDTO();
            newBookDTO.setIsbn(Long.toString(book.getIsbn()));
            newBookDTO.setTitle(book.getTitle());
            newBookDTO.setAuthor(book.getAuthor());
            newBookDTO.setEdition(String.valueOf(book.getEdition()));
        return newBookDTO;
    }
}
