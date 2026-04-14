package com.epam.training.products;

import com.epam.training.products.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductQueriesTest {

    private ProductQueries underTest;

    @BeforeEach
    public void setUp() {
        List<Product> products = new ProductsReader(new File("input/products.json")).read();
        underTest = new ProductQueries(products);
    }

    @Test
    @DisplayName("getSubCategoriesOf('drinks') should return 'soft drinks' and 'juices'")
    public void getSubCategoriesOfDrinks() {
        List<String> result = underTest.getSubCategoriesOf("drinks");
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains("soft drinks"));
        assertTrue(result.contains("juices"));
    }

    @Test
    @DisplayName("getSweetsWherePriceIsLowerThan(3.0) should return 'milk chocolate' and 'dark chocolate'")
    public void getSweetsLowerThan3() {
        List<Product> result = underTest.getSweetsWherePriceIsLowerThan(3.0);
        assertNotNull(result);
        assertEquals(3, result.size());
        List<String> names = result.stream().map(Product::getName).toList();
        assertTrue(names.contains("milk chocolate"));
        assertTrue(names.contains("dark chocolate"));
    }

    @Test
    @DisplayName("getTheMostExpensiveProduct should return 'premium steak'")
    public void getMostExpensiveProduct() {
        Product result = underTest.getTheMostExpensiveProduct();
        assertNotNull(result);
        assertEquals("premium steak", result.getName());
        assertEquals(24.99, result.getPrice(), 0.001);
    }
}
