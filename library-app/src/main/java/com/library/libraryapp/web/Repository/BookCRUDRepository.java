package com.library.libraryapp.web.Repository;

import com.library.libraryapp.web.Model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookCRUDRepository extends CrudRepository<Book,Long> {
}
