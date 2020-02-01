package org.echocat.kata.java.part1;

import org.echocat.kata.java.part1.models.Book;
import org.echocat.kata.java.part1.models.Magazine;
import org.echocat.kata.java.part1.models.PrettyPrintable;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        final CsvPrintedProducts csvPrintedProducts = new CsvPrintedProducts();
        while (true) {
            System.out.print("========================================\n" +
                    "What would you like to do?\n" +
                    "1. Print all books and magazines\n" +
                    "2. Find a book or magazine by it's ISBN\n" +
                    "3. Find all books magazines by their authors’ email.\n" +
                    "4. Print out all books and magazines with all their details sorted by title\n" +
                    "5. Add a book\n" +
                    "6. Add a magazine\n" +
                    "7. Exit\n" +
                    ": ");
            final int input = new Scanner(System.in).nextInt();
            switch (input) {
                case 1:
                    csvPrintedProducts.getProducts()
                            .forEach(PrettyPrintable::prettyPrint);
                    break;
                case 2:
                    System.out.print("Enter ISBN: ");
                    final String isbn = new Scanner(System.in).nextLine();
                    csvPrintedProducts.findByIsbn(isbn)
                            .forEach(PrettyPrintable::prettyPrint);
                    break;
                case 3:
                    System.out.print("Enter Author email: ");
                    final String email = new Scanner(System.in).nextLine();
                    csvPrintedProducts.findByAuthor(email)
                            .forEach(PrettyPrintable::prettyPrint);
                    break;
                case 4:
                    csvPrintedProducts.getSortedByTitle()
                            .forEach(PrettyPrintable::prettyPrint);
                    break;
                case 5:
                    System.out.println("Adding a new book.");
                    System.out.print("Enter Book title: ");
                    final String bookTitle = new Scanner(System.in).nextLine();
                    System.out.print("Enter Book isbn: ");
                    final String bookIsbn = new Scanner(System.in).nextLine();
                    System.out.print("Enter Book author emails (comma separated): ");
                    final String bookAuthorEmailString = new Scanner(System.in).nextLine();
                    System.out.print("Enter Book description: ");
                    final String bookDescription = new Scanner(System.in).nextLine();
                    csvPrintedProducts.addBook(new Book(
                            bookTitle,
                            bookIsbn,
                            bookAuthorEmailString,
                            bookDescription)
                    );
                    System.out.println("Book was added!");
                    break;
                case 6:
                    System.out.println("Adding a new magazine.");
                    System.out.print("Enter Magazine title: ");
                    final String magazineTitle = new Scanner(System.in).nextLine();
                    System.out.print("Enter Magazine isbn: ");
                    final String magazineIsbn = new Scanner(System.in).nextLine();
                    System.out.print("Enter Magazine author emails (comma separated): ");
                    final String magazineAuthorEmailString = new Scanner(System.in).nextLine();
                    System.out.print("Enter Magazine publish date (format: dd.mm.yyyy): ");
                    final String magazinePublishedAt = new Scanner(System.in).nextLine();
                    csvPrintedProducts.addMagazine(new Magazine(
                            magazineTitle,
                            magazineIsbn,
                            magazineAuthorEmailString,
                            magazinePublishedAt)
                    );
                    System.out.println("Magazine was added!");
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Wrong input!");
            }
        }
    }
}
