package org.echocat.kata.java.part1;

import org.echocat.kata.java.part1.models.*;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("UseOfSystemOutOrSystemErr")
public class MainApp {
    private final Scanner in = new Scanner(System.in);
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

    public static void main(String[] args) throws IOException {
        new MainApp().init();
    }

    private void init() {
        while (true) {
            System.out.print("========================================\n" +
                    "What would you like to do?\n" +
                    "1. Print all books and magazines\n" +
                    "2. Find a book or magazine by it's ISBN\n" +
                    "3. Find all books magazines by their authors’ email.\n" +
                    "4. Print out all books and magazines with all their details sorted by title\n" +
                    "5. Exit\n" +
                    ": ");
            final int input = in.nextInt();
            switch (input) {
                case 1:
                    printBooksAndMagazines();
                    break;
                case 2:
                    findByIsbn();
                    break;
                case 3:
                    findByAuthor();
                    break;
                case 4:
                    printSortedByTitle();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Wrong input!");
            }
        }
    }

    private Stream<AbstractPrintedProduct> printedProductStream() {
        return Stream.concat(books.stream(), magazines.stream());
    }

    private void printBooksAndMagazines() {
        printedProductStream()
            .forEach(PrettyPrintable::prettyPrint);
    }

    private void findByIsbn() {
        System.out.print("Enter ISBN: ");
        final String isbn = new Scanner(System.in).nextLine();
        printedProductStream()
                .filter(item -> item.getIsbn().equals(isbn))
                .forEach(PrettyPrintable::prettyPrint);
    }

    private void findByAuthor() {
        System.out.print("Enter Author email: ");
        final String email = new Scanner(System.in).nextLine();
        printedProductStream()
                .filter(item -> item.getAuthorEmails().contains(email))
                .forEach(PrettyPrintable::prettyPrint);
    }

    private void printSortedByTitle() {
        printedProductStream()
                .sorted(Comparator.comparing(AbstractPrintedProduct::getTitle))
                .forEach(PrettyPrintable::prettyPrint);
    }
}
