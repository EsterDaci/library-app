package com.library.libraryapp.web.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "borrowed")
public class Borrowed {
    @Id
    @GeneratedValue
    private Long id;

    private String borrower;
    private String firstName;
    private String book;
    private LocalDate borrowedFrom;
    private LocalDate borrowedTo;


    public static Borrowed parse(String csvLine) {

        String[] fields = csvLine.split(";");
        LocalDate borrowedFromFormatted = LocalDate.parse(fields[3], DateTimeFormatter.ofPattern("M/d/yyyy"));
        LocalDate  borrowedToFormatted = LocalDate.parse(fields[4], DateTimeFormatter.ofPattern("M/d/yyyy"));

        return new Borrowed(null, fields[0], fields[1], fields[2] , borrowedFromFormatted, borrowedToFormatted);
    }
}
