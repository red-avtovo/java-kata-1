package org.echocat.kata.java.part1;

import org.echocat.kata.java.part1.models.Author;
import org.echocat.kata.java.part1.models.Book;
import org.echocat.kata.java.part1.models.Magazine;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("UseOfSystemOutOrSystemErr")
public class MainApp {

    public static void main(String[] args) throws IOException {
        final Parser parser = new Parser("org/echocat/kata/java/part1/data/", ';');
        List<Author> authors = parser.parseCsv("authors.csv", Author.fromCsvRecord);
        List<Book> books = parser.parseCsv("books.csv", Book.fromCsvRecord).stream()
                .map( book -> book.resolveAuthors(authors))
                .collect(Collectors.toList());
        List<Magazine> magazines = parser.parseCsv("magazines.csv", Magazine.fromCsvRecord).stream()
                .map( magazine -> magazine.resolveAuthors(authors))
                .collect(Collectors.toList());;

        System.out.println(authors);
        System.out.println(books);
        System.out.println(magazines);
    }

    protected static String getHelloWorldText() {
        return "Hello world!";
    }



}
