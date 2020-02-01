package org.echocat.kata.java.part1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.csv.CSVRecord;

import java.util.function.Function;

/**
 * @author red
 * @since 01.02.20
 */

@Data
@AllArgsConstructor
public class Author {
    private String email;
    private String firstName;
    private String lastName;
    public static final Function<CSVRecord, Author> fromCsvRecord = (record) ->
            new Author(record.get(0),
                    record.get(1),
                    record.get(2)
            );
}
