package za.ac.cput.carpartmarket.Service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.carpartmarket.Domain.Category;
import za.ac.cput.carpartmarket.Factory.CategoryFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoryServiceTest {

    @Autowired
    private ICategoryService categoryService;

    private static Category category;

    @Test
    @Order(1)
    void a_create(){
        category = CategoryFactory.createCategory("Engine Parts", "Parts related to engines");
        assertNotNull(category);

        Category created = categoryService.create(category);
        assertNotNull(created);
        assertEquals(category.getCategoryId(), created.getCategoryId());
        category = created;

        System.out.println(created);
    }

    @Test
    @Order(2)
    void b_read(){
        assertNotNull(category);

        Category read = categoryService.read(category.getCategoryId());
        assertNotNull(read);
        assertEquals(category.getCategoryId(), read.getCategoryId());

        System.out.println(read);
    }

    @Test
    @Order(3)
    void c_update(){
        assertNotNull(category);

        Category updated = new Category.Builder()
                .copy(category)
                .setCategoryName("Brake Parts")
                .setDescription("Parts related to braking systems")
                .build();

        Category saved = categoryService.update(updated);
        assertNotNull(saved);
        assertEquals("Brake Parts", saved.getCategoryName());
        assertEquals("Parts related to braking systems", saved.getDescription());
        category = saved;

        System.out.println(saved);
    }

    @Test
    @Order(4)
    void d_getAll(){
        List<Category> categories = categoryService.getAll();
        assertNotNull(categories);
        assertFalse(categories.isEmpty());

        categories.forEach(System.out::println);
    }

    @Test
    @Order(5)
    void e_delete(){
        assertNotNull(category);

        boolean deleted = categoryService.delete(category.getCategoryId());
        assertTrue(deleted);

        Category read = categoryService.read(category.getCategoryId());
        assertNull(read);
    }
}