package com.library.libraryapp.web.Repository;

import com.library.libraryapp.web.Model.Book;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepository extends PagingAndSortingRepository<Book,Long> {
}
