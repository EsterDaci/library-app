package com.library.libraryapp.web.Services;

import com.library.libraryapp.web.Model.Borrowed;
import com.library.libraryapp.web.Repository.BorrowerCRUDRepository;
import com.library.libraryapp.web.Repository.BorrowerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;

@Service
public class BorrowerService {
    private final BorrowerRepository borrowerRepository;
    private final BorrowerCRUDRepository borrowerCRUDRepository;


    public BorrowerService(BorrowerRepository borrowerRepository, BorrowerCRUDRepository borrowerCRUDRepository) {
        this.borrowerRepository = borrowerRepository;
        this.borrowerCRUDRepository = borrowerCRUDRepository;
    }

    public Set<String> findAllUniqueBorrowers(){
        return borrowerCRUDRepository.findAllUniqueBorrowers();

    }

    public Borrowed save(Borrowed borrowed) {
        return borrowerCRUDRepository.save(borrowed);
    }

    public Page<Borrowed> findAll(Pageable pageable) {
        return borrowerRepository.findAll(pageable);
    }

    public void importCSV(InputStream csvFileStream)  {
        InputStreamReader inputStreamReader = new InputStreamReader(csvFileStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        bufferedReader.lines()
                .skip(1)
                .map(Borrowed::parse)
                .forEach(borrowerCRUDRepository::save);

    }
}
