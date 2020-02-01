package org.echocat.kata.java.part1;

import lombok.NonNull;
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
    private String authorsFile;
    private String booksFile;
    private String magazinesFile;
    private List<Author> authors;
    private List<Book> books;
    private List<Magazine> magazines;

    public CsvPrintedProducts() {
        authorsFile = "authors.csv";
        booksFile = "books.csv";
        magazinesFile = "magazines.csv";
        loadDataFomFiles();
    }

    public CsvPrintedProducts(@NonNull String authorsFile,
                              @NonNull String booksFile,
                              @NonNull String magazinesFile) {
        this.authorsFile = authorsFile;
        this.booksFile = booksFile;
        this.magazinesFile = magazinesFile;
        loadDataFomFiles();
    }

    public void loadDataFomFiles() {
        authors = parser.parseCsv(authorsFile, Author.fromCsvRecord);
        books = parser.parseCsv(booksFile, Book.fromCsvRecord)
                .stream()
                .map(book -> book.resolveAuthors(authors))
                .collect(Collectors.toList());
        magazines = parser.parseCsv(magazinesFile, Magazine.fromCsvRecord)
                .stream()
                .map(magazine -> magazine.resolveAuthors(authors))
                .collect(Collectors.toList());
    }

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

    public void addBook(@NonNull Book book) {
        books.add(book.resolveAuthors(authors));
        persistNewData();
    }

    public void addMagazine(@NonNull Magazine magazine) {
        magazines.add(magazine.resolveAuthors(authors));
        persistNewData();
    }

    private void persistNewData() {
        // TODO: save in a new Csv file
    }
}
