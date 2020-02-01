package org.echocat.kata.java.part1;

import java.io.IOException;
import java.util.Scanner;

@SuppressWarnings("UseOfSystemOutOrSystemErr")
public class MainApp {
    public static void main(String[] args) throws IOException {
        final CsvPrintedProducts csvPrintedProducts = new CsvPrintedProducts();
        while (true) {
            System.out.print("========================================\n" +
                    "What would you like to do?\n" +
                    "1. Print all books and magazines\n" +
                    "2. Find a book or magazine by it's ISBN\n" +
                    "3. Find all books magazines by their authors’ email.\n" +
                    "4. Print out all books and magazines with all their details sorted by title\n" +
                    "5. Exit\n" +
                    ": ");
            final int input = new Scanner(System.in).nextInt();
            switch (input) {
                case 1:
                    csvPrintedProducts.printProducts();
                    break;
                case 2:
                    System.out.print("Enter ISBN: ");
                    final String isbn = new Scanner(System.in).nextLine();
                    csvPrintedProducts.findByIsbn(isbn);
                    break;
                case 3:
                    System.out.print("Enter Author email: ");
                    final String email = new Scanner(System.in).nextLine();
                    csvPrintedProducts.printByAuthor(email);
                    break;
                case 4:
                    csvPrintedProducts.printSortedByTitle();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Wrong input!");
            }
        }
    }
}
