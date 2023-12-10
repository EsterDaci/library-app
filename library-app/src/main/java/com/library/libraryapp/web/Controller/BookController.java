package com.library.libraryapp.web.Controller;

import com.library.libraryapp.web.Model.Book;
import com.library.libraryapp.web.Model.User;
import com.library.libraryapp.web.Repository.BookCRUDRepository;
import com.library.libraryapp.web.Repository.BookRepository;
import com.library.libraryapp.web.Services.BookService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookCRUDRepository bookCRUDRepository;
    private final BookRepository bookRepository;
    private final BookService bookService;


    public BookController(BookCRUDRepository bookCRUDRepository, BookRepository bookRepository, BookService bookService) {
        this.bookCRUDRepository = bookCRUDRepository;
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }
    @ModelAttribute("books")
    public Page<Book> getBooks(@PageableDefault(size = 10) Pageable page){
        return bookService.findAll(page);
    }
    @PostMapping(params = "action=import")
    public String importCSV(@RequestParam MultipartFile csvFile){
        try{
            bookService.importCSV(csvFile.getInputStream());
        }catch(IOException e){
            e.printStackTrace();
        }

        return "redirect:books";
    }

    @PostMapping
    public String saveUser(@Valid Book book, Errors errors, @RequestParam MultipartFile csvFile){
        if(!errors.hasErrors()){
            bookCRUDRepository.save(book);
            return "redirect:books";
        }
        return "books";
    }

    @GetMapping
    public String getBooks(Model model){

        return "books";
    }
}
