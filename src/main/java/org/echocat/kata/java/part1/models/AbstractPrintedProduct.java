package org.echocat.kata.java.part1.models;

import lombok.Getter;

import java.util.List;

/**
 * @author red
 * @since 01.02.20
 */

abstract public class AbstractPrintedProduct implements PrettyPrintable {
    @Getter
    private String title;
    @Getter
    private String isbn;
    @Getter
    private List<String> authorEmails;
}
