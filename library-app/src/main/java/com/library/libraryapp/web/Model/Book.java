package com.library.libraryapp.web.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String author;
    private String genre;
    private String publisher;

    @ManyToMany(mappedBy = "bookSet",fetch = FetchType.LAZY)
    private Set<User> userSet;


    public static Book parse(String csvLine) {
        System.out.println(csvLine);
        String[] fields = csvLine.split(";");
        System.out.println("fields:" + fields);
        return new Book(null, fields[0], fields[1], fields[2], fields[3],null);
    }
}
