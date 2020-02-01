package org.echocat.kata.java.part1;

import org.echocat.kata.java.part1.models.AbstractPrintedProduct;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author red
 * @since 01.02.20
 */

public class CsvPrintedProductsTest {
    private CsvPrintedProducts printedProducts = new CsvPrintedProducts();

    @Test
    public void getProducts() {
        assertThat(printedProducts.getProducts()).hasSize(14);
    }

    @Test
    public void findByIsbn() {
        final String isbn = "5454-5587-3210";
        final List<AbstractPrintedProduct> byIsbn = printedProducts.findByIsbn(isbn);
        assertThat(byIsbn).hasSize(1);
        assertThat(byIsbn.get(0).getIsbn()).isEqualTo(isbn);
    }

    @Test
    public void findByAuthor() {
        final String authorEmail = "null-gustafsson@echocat.org";
        final List<AbstractPrintedProduct> byAuthor = printedProducts.findByAuthor(authorEmail);
        assertThat(byAuthor).hasSize(2);
        byAuthor.forEach( product -> {
            assertThat(product.getAuthorEmails()).contains(authorEmail);
        });
    }

    @Test
    public void getSortedByTitle() {
        final List<AbstractPrintedProduct> sortedByTitle = printedProducts.getSortedByTitle();
        assertThat(sortedByTitle).hasSize(14);
        final List<String> titles = sortedByTitle.stream().map(AbstractPrintedProduct::getTitle)
                .collect(Collectors.toList());
        final List<String> sortedTitles = titles.stream().sorted().collect(Collectors.toList());
        assertThat(titles).isEqualTo(sortedTitles);
    }
}