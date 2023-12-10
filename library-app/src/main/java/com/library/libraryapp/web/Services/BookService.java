package com.library.libraryapp.web.Services;

import com.library.libraryapp.web.Model.Book;
import com.library.libraryapp.web.Model.User;
import com.library.libraryapp.web.Repository.BookCRUDRepository;
import com.library.libraryapp.web.Repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class BookService {
    private final BookCRUDRepository bookCRUDRepository;
    private final BookRepository bookRepository;


    public BookService(BookCRUDRepository bookCRUDRepository, BookRepository bookRepository) {
        this.bookCRUDRepository = bookCRUDRepository;
        this.bookRepository = bookRepository;
    }
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public void importCSV(InputStream csvFileStream)  {
        InputStreamReader inputStreamReader = new InputStreamReader(csvFileStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        bufferedReader.lines()
                .skip(1)
                .map(Book::parse)
                .forEach(bookCRUDRepository::save);

    }
}
