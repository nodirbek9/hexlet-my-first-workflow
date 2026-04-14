package com.epam.training.products;

import com.epam.training.products.domain.Product;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductQueries {

    private final List<Product> products;

    public ProductQueries(List<Product> products) {
        this.products = products;
    }

    /**
     * Returns all unique subcategories of the given category name.
     * A product belongs to a category if its first element of categories[] equals categoryName,
     * and it has a subcategory (second element).
     */
    public List<String> getSubCategoriesOf(String categoryName) {
        return products.stream()
                .filter(p -> p.hasSubCategory())
                .filter(p -> categoryName.equalsIgnoreCase(p.getCategory()))
                .map(Product::getSubCategory)
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * Returns all products that:
     * 1. Belong to the "sweets" category (first element of categories[])
     * 2. Have a price lower than the given parameter
     */
    public List<Product> getSweetsWherePriceIsLowerThan(double price) {
        return products.stream()
                .filter(p -> p.hasCategory())
                .filter(p -> "sweets".equalsIgnoreCase(p.getCategory()))
                .filter(p -> p.getPrice() < price)
                .collect(Collectors.toList());
    }

    /**
     * Returns the product with the highest price.
     * Throws NoSuchElementException if the product list is empty.
     */
    public Product getTheMostExpensiveProduct() {
        return products.stream()
                .max(Comparator.comparingDouble(Product::getPrice))
                .orElseThrow();
    }
}
