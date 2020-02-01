package org.echocat.kata.java.part1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.csv.CSVRecord;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author red
 * @since 01.02.20
 */

@Data
@AllArgsConstructor
public class Book {
    private String title;
    private String isbn;
    private List<String> authorEmails;
    private List<Author> authors;
    private String description;
    public static final Function<CSVRecord, Book> fromCsvRecord = (record) ->
            new Book(record.get(0),
                    record.get(1),
                    Arrays.asList(record.get(2).split(",")),
                    null,
                    record.get(3)
            );

    public Book resolveAuthors(List<Author> authors) {
        this.authors = authors.stream()
                .filter( author -> authorEmails.contains(author.getEmail()))
                .collect(Collectors.toList());
        return this;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", authors=" + authors +
                ", description='" + description + '\'' +
                '}';
    }
}
