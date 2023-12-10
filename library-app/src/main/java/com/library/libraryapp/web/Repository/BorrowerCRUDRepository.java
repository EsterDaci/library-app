package com.library.libraryapp.web.Repository;

import com.library.libraryapp.web.Model.Borrowed;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface BorrowerCRUDRepository extends CrudRepository<Borrowed,Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM BORROWED")
    public Set<String> findAllUniqueBorrowers();

}
