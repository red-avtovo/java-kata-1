package org.echocat.kata.java.part1.models;

import java.util.List;

/**
 * @author red
 * @since 01.02.20
 */

abstract public class WithIsbnAndAuthorEmail {
    private String isbn;
    private List<String> authorEmails;

    public String getIsbn() {
        return isbn;
    }

    public List<String> getAuthorEmails() {
        return authorEmails;
    }
}
