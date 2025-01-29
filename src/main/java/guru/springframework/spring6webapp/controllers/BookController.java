package guru.springframework.spring6webapp.controllers;



import guru.springframework.spring6webapp.services.AuthorService;
import guru.springframework.spring6webapp.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @RequestMapping("/books")
    public String getBooks(Model model) {
        model.addAttribute("books",bookService.findAll());
        return "books";
    }
    @RequestMapping("/authors")
    public String getAuthor(Model model) {
        model.addAttribute("authors",authorService.findAll());
        return "authors";
    }
}
