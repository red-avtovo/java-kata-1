package org.echocat.kata.java.part1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = true)
public class Book extends AbstractPrintedProduct {
    private String title;
    private String isbn;
    private List<String> authorEmails;
    private List<Author> authors;
    private String description;
    public static final Function<CSVRecord, Book> fromCsvRecord = (record) ->
            new Book(record.get("\uFEFFtitle"),
                    record.get("isbn"),
                    record.get("authors"),
                    record.get("description")
            );

    public Book(String title, String isbn, String authorEmails, String description) {
        this.title = title;
        this.isbn = isbn;
        this.authorEmails = Arrays.asList(authorEmails.split(","));
        this.description = description;
    }

    public Book resolveAuthors(List<Author> authors) {
        this.authors = authors.stream()
                .filter( author -> authorEmails.contains(author.getEmail()))
                .collect(Collectors.toList());
        return this;
    }

    public void prettyPrint() {
        final String authorsOut = authors.stream().map(Author::prettyOut).collect(Collectors.joining("\n"));
        System.out.println("Book\n" +
                "\ttitle='" + title + "'\n" +
                "\tisbn='" + isbn + "'\n" +
                "\tauthors=\n" + authorsOut + "\n" +
                "\tdescription='" + description);
    }
}
