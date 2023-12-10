package com.library.libraryapp.web.Model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LOCAL_DATE;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    public void canParse(){
        String csvLine = "Odum,Oliver,5/8/1999,1/1/2021,m";
        User user = User.parse(csvLine);
        assertThat(user.getMemberSince()).isEqualTo(LocalDate.of(1999,5,8));
    }

}