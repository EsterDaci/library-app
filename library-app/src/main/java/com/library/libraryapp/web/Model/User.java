package com.library.libraryapp.web.Model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String firstName;
    private LocalDate memberSince;
    private LocalDate memberFrom;
    private String gender;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "users_books",joinColumns = {
            @JoinColumn(name = "user_id",referencedColumnName = "id")
    },
    inverseJoinColumns = {
            @JoinColumn(name = "book_id",referencedColumnName = "id")
    }
    )
    private Set<Book> bookSet;


    public static User parse(String csvLine) {
        LocalDate memberFromFormatted=null;
        String[] fields = csvLine.split(";");

        LocalDate memberSinceFormatted = LocalDate.parse(fields[2], DateTimeFormatter.ofPattern("M/d/yyyy"));
        if (fields[3]!=null && !fields[3].equals(""))
            memberFromFormatted = LocalDate.parse(fields[3], DateTimeFormatter.ofPattern("M/d/yyyy"));

        return new User(null, fields[0], fields[1], memberSinceFormatted, memberFromFormatted, fields[4],null);
    }
}
