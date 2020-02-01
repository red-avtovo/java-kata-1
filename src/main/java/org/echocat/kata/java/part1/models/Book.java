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
@AllArgsConstructor
public class Book extends WithIsbnAndAuthorEmail implements PrettyPrintable {
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

    public void prettyPrint() {
        final String authorsOut = authors.stream().map(Author::prettyOut).collect(Collectors.joining("\n"));
        System.out.println("Book\n" +
                "\ttitle='" + title + "'\n" +
                "\tisbn='" + isbn + "'\n" +
                "\tauthors=\n" + authorsOut + "\n" +
                "\tdescription='" + description);
    }
}
