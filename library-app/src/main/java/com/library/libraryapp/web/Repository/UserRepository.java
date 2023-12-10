package com.library.libraryapp.web.Repository;

import com.library.libraryapp.web.Model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends PagingAndSortingRepository<User,Long>{


}
