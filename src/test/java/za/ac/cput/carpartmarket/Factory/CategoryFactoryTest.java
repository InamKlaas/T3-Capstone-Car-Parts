package za.ac.cput.carpartmarket.Factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.carpartmarket.Domain.Category;

import static org.junit.jupiter.api.Assertions.*;

class CategoryFactoryTest {

    @Test
    void createCategory_validData_returnsCategory(){
        Category category = CategoryFactory.createCategory("Engine Parts", "Parts related to engines");

        assertNotNull(category);
        assertNotNull(category.getCategoryId());
        assertEquals("Engine Parts", category.getCategoryName());
        assertEquals("Parts related to engines", category.getDescription());
    }

    @Test
    void createCategory_nullCategoryName_returnsNull(){
        Category category = CategoryFactory.createCategory(null, "Parts related to engines");
        assertNull(category);
    }

    @Test
    void createCategory_emptyCategoryName_returnsNull(){
        Category category = CategoryFactory.createCategory("  ", "Parts related to engines");
        assertNull(category);
    }

    @Test
    void createCategory_nullDescription_returnsNull(){
        Category category = CategoryFactory.createCategory("Engine Parts", null);
        assertNull(category);
    }

    @Test
    void createCategory_emptyDescription_returnsNull(){
        Category category = CategoryFactory.createCategory("Engine Parts", "   ");
        assertNull(category);
    }
}