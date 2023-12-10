package com.library.libraryapp.web.Repository;

import com.library.libraryapp.web.Model.Borrowed;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BorrowerRepository extends PagingAndSortingRepository<Borrowed,Long> {
}
