package org.echocat.kata.java.part1;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author red
 * @since 01.02.20
 */

public class Parser {
    private final String basePath;
    private CSVFormat csvFormat;

    public Parser(String basePath, Character delimiter) {
        this.basePath = basePath;
        csvFormat = CSVFormat.newFormat(delimiter)
                .withFirstRecordAsHeader()
                .withIgnoreHeaderCase()
                .withTrim();
    }

    public <T> List<T> parseCsv(String fileName, Function<CSVRecord, T> convert) {
        try {
            final URL resource = Parser.class.getClassLoader().getResource(String.format("%s%s", basePath, fileName));
            if (resource == null)
                throw new FileNotFoundException(String.format("No file found with name %s", fileName));
            Reader in = new FileReader(resource.getFile());
            Iterable<CSVRecord> records = csvFormat.parse(in);
            return StreamSupport.stream(records.spliterator(), false)
                    .map(convert)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
