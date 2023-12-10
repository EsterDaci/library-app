package com.library.libraryapp.web.Repository;

import com.library.libraryapp.web.Model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserCRUDRepository extends CrudRepository<User,Long> {

    @Query(nativeQuery = true, value = "select FIRST_NAME from USERS where ID in :ids")
    public Set<String> findAllTest(@Param("ids") Iterable<Long> ids);


}
