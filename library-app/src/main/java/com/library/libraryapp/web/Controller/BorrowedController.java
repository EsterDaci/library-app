package com.library.libraryapp.web.Controller;

import com.library.libraryapp.web.Model.Book;
import com.library.libraryapp.web.Model.Borrowed;
import com.library.libraryapp.web.Repository.BorrowerCRUDRepository;
import com.library.libraryapp.web.Repository.BorrowerRepository;
import com.library.libraryapp.web.Services.BorrowerService;
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
import java.util.Set;

@Controller
@RequestMapping("/borrowed")
public class BorrowedController {
    private final BorrowerRepository borrowerRepository;
    private final BorrowerCRUDRepository borrowerCRUDRepository;
    private final BorrowerService borrowerService;


    public BorrowedController(BorrowerRepository borrowerRepository, BorrowerCRUDRepository borrowerCRUDRepository, BorrowerService borrowerService) {
        this.borrowerRepository = borrowerRepository;
        this.borrowerCRUDRepository = borrowerCRUDRepository;
        this.borrowerService = borrowerService;
    }

    @ModelAttribute("borrowed")
    public Page<Borrowed> getAllBorrowers(@PageableDefault(size = 10) Pageable page){
        return borrowerService.findAll(page);
    }
    @PostMapping(params = "action=import")
    public String importCSV(@RequestParam MultipartFile csvFile){
        try{
            borrowerService.importCSV(csvFile.getInputStream());
        }catch(IOException e){
            e.printStackTrace();
        }

        return "redirect:borrowed";
    }

    @PostMapping
    public String saveUser(@Valid Borrowed borrowed, Errors errors, @RequestParam MultipartFile csvFile){
        if(!errors.hasErrors()){
            borrowerCRUDRepository.save(borrowed);
            return "redirect:borrowed";
        }
        return "borrowed";
    }

    @GetMapping
    public String getBooks(Model model){
        return "borrowed";
    }

    @GetMapping("/users")
    public String getBorrowers(Model model){
        model.addAttribute("users", borrowerService.findAllUniqueBorrowers());
        return "/borrowed/borrowers";
    }
}
