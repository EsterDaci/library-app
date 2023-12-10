package com.library.libraryapp.web.Services;

import com.library.libraryapp.web.Model.User;
import com.library.libraryapp.web.Repository.UserCRUDRepository;
import com.library.libraryapp.web.Repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserCRUDRepository userCRUDRepository;


    public UserService(UserRepository userRepository, UserCRUDRepository userCRUDRepository) {
        this.userRepository = userRepository;
        this.userCRUDRepository = userCRUDRepository;
    }

    public User save(User user) {
        return userCRUDRepository.save(user);
    }

    public  Iterable<User> saveAll(Iterable<User> entities) {
        return userCRUDRepository.saveAll(entities);
    }

    public Set<String> findAllTest(Iterable<Long> ids) {
        return userCRUDRepository.findAllTest(ids);
    }

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public void importCSV(InputStream csvFileStream)  {
        InputStreamReader inputStreamReader = new InputStreamReader(csvFileStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        bufferedReader.lines()
                .skip(1)
                .map(User::parse)
                .forEach(userCRUDRepository::save);

    }
}
