package com.epam.training.products;

import com.epam.training.products.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductsReaderTest {

    private ProductsReader underTest;

    @BeforeEach
    public void setUp() {
        underTest = new ProductsReader(new File("input/products.json"));
    }

    @Test
    @DisplayName("Read should return non-empty list of products from JSON")
    public void readReturnsProducts() {
        List<Product> products = underTest.read();
        assertNotNull(products);
        assertFalse(products.isEmpty());
    }

    @Test
    @DisplayName("Product fields are correctly mapped (value->price, amount->quantity)")
    public void productFieldsAreMappedCorrectly() {
        List<Product> products = underTest.read();
        Product bread = products.stream()
                .filter(p -> "white bread".equals(p.getName()))
                .findFirst()
                .orElseThrow();

        assertEquals("white bread", bread.getName());
        assertEquals("ABC Bakery", bread.getProducer());
        assertEquals(1, bread.getQuantity());
        assertEquals("loaf", bread.getUnit());
        assertEquals(2.99, bread.getPrice(), 0.001);
        assertArrayEquals(new String[]{"bakery", "bread"}, bread.getCategories());
    }
}
