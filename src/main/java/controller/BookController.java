package controller;

import entity.Book;
import entity.BookDTO;
import entity.factory.BookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import persistence.service.BookRepositoryService;

import javax.validation.UnexpectedTypeException;
import javax.validation.Valid;
import java.util.Optional;

/**
 * Created by Guilherme on 14/07/2015.
 */

@Controller
@RequestMapping(value = "/bookcase")
public class BookController {
    @Autowired
    BookFactory bookFactory;

    @Autowired
    BookRepositoryService bookRepositoryService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showForm(Model model, @ModelAttribute("book") BookDTO bookDTO) {
        model.addAttribute("bookDTO", bookDTO);
        return "register";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, name = "saveBook")
    public String save(@Valid BookDTO bookDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "register";
        } else {
            try {
                Book book = bookFactory.createBook(bookDTO);
                bookRepositoryService.saveEntity(book);
                System.out.println("BookDao: Livro salvo com Sucesso! - " + book);
                redirectAttributes.addFlashAttribute("mensagem", "Livro cadastrado com sucesso");
                redirectAttributes.addFlashAttribute("cssStyle", "alert alert-success");
            } catch (UnexpectedTypeException ute) {
                System.err.println(ute.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return "redirect:books";
        }
    }

    @RequestMapping(value = "/edit/{isbn}", method = RequestMethod.GET)
    public String edit(@PathVariable String isbn, Model model, BookDTO bookDTO,
                       RedirectAttributes redirectAttributes) {
        try {
            Optional<Book> returnedBook = bookRepositoryService.findEntityById(new Long(isbn));
            if (returnedBook.isPresent()) {
                bookDTO = bookFactory.createBookDTO(returnedBook.get());
            } else {
                redirectAttributes.addFlashAttribute("mensagem", "Livro não encontrado.");
                redirectAttributes.addFlashAttribute("cssStyle", "alert alert-warning");
                return "redirect:/bookcase/books";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            model.addAttribute(bookDTO);
        }
        return "edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, name = "updateBook")
    public String update(@Valid BookDTO bookDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "edit";
        }else{
            try {
                Book book = bookFactory.createBook(bookDTO);
                bookRepositoryService.updateEntity(book);
                System.out.println("BookDao: Livro atualizado com Sucesso! - " + book);
                redirectAttributes.addFlashAttribute("mensagem", "Livro atualizado com sucesso");
                redirectAttributes.addFlashAttribute("cssStyle", "alert alert-success");
            } catch (UnexpectedTypeException ute) {
                System.err.println(ute.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return "redirect:books";
        }
    }

    @RequestMapping(value = "/delete/{isbn}", method = RequestMethod.POST)
    public String delete(@PathVariable String isbn, RedirectAttributes redirectAttributes) {
        try {
            Optional<Book> returnedBook = bookRepositoryService.findEntityById(new Long(isbn));
            if (returnedBook.isPresent()) {
                Book book = returnedBook.get();
                bookRepositoryService.deleteEntity(book);
                System.out.println("BookDao: Livro deletado com Sucesso! - " + isbn);
                redirectAttributes.addFlashAttribute("mensagem", "Livro deletado com sucesso");
                redirectAttributes.addFlashAttribute("cssStyle", "alert alert-success");
            } else {
                redirectAttributes.addFlashAttribute("mensagem", "Livro não pode ser deletado.");
                redirectAttributes.addFlashAttribute("cssStyle", "alert alert-warning");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/bookcase/books";
    }
    @CacheEvict(value = "books", allEntries=true)
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String list(ModelMap model) throws Exception {
        model.addAttribute("books", bookRepositoryService.findAllEntities());
        return "bookList";
    }
}
