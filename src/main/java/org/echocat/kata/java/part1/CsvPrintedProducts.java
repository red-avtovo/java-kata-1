package org.echocat.kata.java.part1;

import org.echocat.kata.java.part1.models.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author red
 * @since 01.02.20
 */

public class CsvPrintedProducts {
    private final Parser parser = new Parser("org/echocat/kata/java/part1/data/", ';');
    private List<Author> authors = parser.parseCsv("authors.csv", Author.fromCsvRecord);

    private List<Book> books = parser.parseCsv("books.csv", Book.fromCsvRecord)
            .stream()
            .map(book -> book.resolveAuthors(authors))
            .collect(Collectors.toList());

    private List<Magazine> magazines = parser.parseCsv("magazines.csv", Magazine.fromCsvRecord)
            .stream()
            .map(magazine -> magazine.resolveAuthors(authors))
            .collect(Collectors.toList());

    private Stream<AbstractPrintedProduct> printedProductStream() {
        return Stream.concat(books.stream(), magazines.stream());
    }

    public List<AbstractPrintedProduct> getProducts() {
        return printedProductStream()
                .collect(Collectors.toList());
    }

    public List<AbstractPrintedProduct> findByIsbn(String isbn) {
        return printedProductStream()
                .filter(item -> item.getIsbn().equals(isbn))
                .collect(Collectors.toList());
    }

    public List<AbstractPrintedProduct> findByAuthor(String authorEmail) {
        return printedProductStream()
                .filter(item -> item.getAuthorEmails().contains(authorEmail))
                .collect(Collectors.toList());
    }

    public List<AbstractPrintedProduct> getSortedByTitle() {
        return printedProductStream()
                .sorted(Comparator.comparing(AbstractPrintedProduct::getTitle))
                .collect(Collectors.toList());
    }
}
