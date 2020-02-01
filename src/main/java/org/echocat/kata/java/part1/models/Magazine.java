package org.echocat.kata.java.part1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.csv.CSVRecord;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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
public class Magazine extends AbstractPrintedProduct implements PrettyPrintable {
    private String title;
    private String isbn;
    private List<String> authorEmails;
    private List<Author> authors;
    private Date publishedAt;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    public static final Function<CSVRecord, Magazine> fromCsvRecord = (record) ->
            new Magazine(record.get(0),
                    record.get(1),
                    Arrays.asList(record.get(2).split(",")),
                    null,
                    parseDate(record.get(3))
            );

    private static Date parseDate(String record) {
        try {
            return dateFormat.parse(record);
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public Magazine resolveAuthors(List<Author> authors) {
        this.authors = authors.stream()
                .filter( author -> authorEmails.contains(author.getEmail()))
                .collect(Collectors.toList());
        return this;
    }

    public void prettyPrint() {
        final String authorsOut = authors.stream().map(Author::prettyOut).collect(Collectors.joining("\n"));
        System.out.println("Magazine\n" +
                "\ttitle='" + title + "'\n" +
                "\tisbn='" + isbn + "'\n" +
                "\tauthors=\n" + authorsOut + "\n" +
                "\tpublishedAt='" + publishedAt);
    }
}
