package controller;

import entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import persistence.BookDao;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Created by Guilherme on 14/07/2015.
 */

@Controller
@RequestMapping(value = "/bookcase")
@Transactional
public class BookController {
    @Autowired
    BookDao bookDao;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showForm(Model model, @ModelAttribute("book") Book book) {
        model.addAttribute("book", book);
        return "register";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasFieldErrors()) {
            System.out.println("Houve erros no formulário.");
            return "redirect:register";
        }
        try {
            bookDao.save(book);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("BookDao: Livro salvo com Sucesso! - " + book);
        redirectAttributes.addFlashAttribute("mensagem", "Livro cadastrado com sucesso");
        return "redirect:books";
    }

    @RequestMapping(value = "/edit/{isbn}", method = RequestMethod.GET)
    public String edit(@PathVariable String isbn, Model model, @ModelAttribute("book") Book book,
                       RedirectAttributes redirectAttributes) {
        try {
            Optional<Book> returnedBook = bookDao.findOne(new Long(isbn));
            if (returnedBook.isPresent()) {
                book = returnedBook.get();
            } else {
                redirectAttributes.addFlashAttribute("mensagem", "Livro não encontrado.");
                return "redirect:/bookcase/books";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        model.addAttribute("book", book);
        return "edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Book book, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasFieldErrors()) {
            System.out.println("Houve erros no formulário.");
            return "redirect:edit";
        }
        try {
            bookDao.update(book);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("BookDao: Livro atualizado com Sucesso! - " + book);
        redirectAttributes.addFlashAttribute("mensagem", "Livro atualizado com sucesso");
        return "redirect:books";
    }

    @RequestMapping(value = "/delete/{isbn}", method = RequestMethod.POST)
    public String delete(@PathVariable String isbn, RedirectAttributes redirectAttributes) {
        try {
            Optional<Book> returnedBook = bookDao.findOne(new Long(isbn));
            if (returnedBook.isPresent()) {
                Book book = returnedBook.get();
                bookDao.delete(book);
            } else {
                redirectAttributes.addFlashAttribute("mensagem", "Livro não pode ser deletado.");
                return "redirect:/bookcase/books";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("BookDao: Livro deletado com Sucesso! - " + isbn);
        redirectAttributes.addFlashAttribute("mensagem", "Livro deletado com sucesso");
        return "redirect:/bookcase/books";
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String list(ModelMap model) throws Exception {
        model.addAttribute("books", bookDao.findAll());
        return "bookList";
    }
}
