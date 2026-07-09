package za.ac.cput.carpartmarket.Factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.carpartmarket.Domain.Category;

import static org.junit.jupiter.api.Assertions.*;

class CategoryFactoryTest {

    @Test
    void createCategory() {

        Category category = CategoryFactory.createCategory(
                "Engine Parts",
                "Engine components and accessories"
        );

        assertNotNull(category);

        System.out.println(category);
    }

    @Test
    void createCategoryWithNullName() {

        Category category = CategoryFactory.createCategory(
                "",
                "Engine components"
        );

        assertNull(category);
    }
}