package com.library.project.controller;

import com.library.project.model.Book;
import com.library.project.model.UserEntity;
import com.library.project.repository.BookRepository;
import com.library.project.repository.UserRepository;
import com.library.project.service.BookService;
import com.library.project.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AppController {

    private UserService userService;
    private UserRepository userRepository;
    private BookService bookService;
    private BookRepository bookRepository;

    public AppController(UserService userService, UserRepository userRepository, BookService bookService, BookRepository bookRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    @GetMapping("")
    public String viewHomePageWithBooksList(Model model) {
        List<Book> listBooks = bookRepository.findAll();
        model.addAttribute("listBooks", listBooks);
        return "index";
    }

    @GetMapping("/registration")
    public String viewRegisterPage(Model model) {
        model.addAttribute("user", new UserEntity());
        return "registration";
    }

    @PostMapping("/process_register")
    public String processRegistration(UserEntity userEntity) {
        userService.save(userEntity);
        return "process_register";
    }

    @GetMapping("/add_book")
    public String addBook(Model model){
        model.addAttribute("book",new Book());
        return "add_book";
    }
    @PostMapping("/process_add_book")
    public String processAddBook(Book book){
        bookService.save(book);
        return "process_add_book";
    }

    @GetMapping("/list_users")
    public String viewUsersList(Model model) {
        List<UserEntity> listUsers = userRepository.findAll();
        model.addAttribute("listUsers", listUsers);
        return "list_users";
    }

    @GetMapping("/console")
    public String viewConsole() {
        return "console";
    }

    @GetMapping("/login")
    public String viewLogin() {
        return "login";
    }

    @GetMapping("/customer")
    public String viewCustomerPage() {
        return "customer";
    }

    @GetMapping("/search_book")
    public String viewSearchBook(){
        return "search_book";
    }

    @PostMapping("/search_result")
    public String viewSearchResult(Model model, @RequestParam String name){
        //List<Book> bookList = bookRepository.getBookByTitle(name);
        List<Book> bookList = bookRepository.getBookByTitleAuthorCategory(name);
        model.addAttribute("bookList", bookList);
        return "search_result";
    }

    @GetMapping("/list_books")
    public String viewBooksList(Model model) {
        List<Book> listBooks = bookRepository.findAll();
        model.addAttribute("listBooks", listBooks);
        return "list_books";
    }

}
