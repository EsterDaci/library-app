package com.library.libraryapp.web.Controller;

import com.library.libraryapp.web.Model.User;
import com.library.libraryapp.web.Repository.UserCRUDRepository;
import com.library.libraryapp.web.Repository.UserRepository;
import com.library.libraryapp.web.Services.UserService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/users")
@Log4j2
public class UserController {

    private final UserRepository userRepository;
    private final UserCRUDRepository userCRUDRepository;
    private final UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, UserCRUDRepository userCRUDRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userCRUDRepository = userCRUDRepository;
        this.userService = userService;
    }

    @ModelAttribute("users")
        public Page<User> getUsers(@PageableDefault(size = 5) Pageable page){
        return userService.findAll(page);

    }

    @PostMapping(params = "action=import")
    public String importCSV(@RequestParam MultipartFile csvFile){
        try{
            userService.importCSV(csvFile.getInputStream());
        }catch(IOException e){
            e.printStackTrace();
        }

        return "redirect:users";
    }

    @PostMapping
    public String saveUser(@Valid User user, Errors errors,@RequestParam MultipartFile csvFile){
        if(!errors.hasErrors()){
            userCRUDRepository.save(user);
            return "redirect:users";
        }
        return "users";
    }

    @GetMapping
    public String getUsers(Model model){
//        List<User> users = List.of(new User(1L, "Ester", "DC", LocalDate.of(2023, 1, 1), "f"));
//        model.addAttribute("users",users);
        return "users";
    }
}
