package za.ac.cput.carpartmarket.Repository;

import org.junit.jupiter.api.*;

import za.ac.cput.carpartmarket.Domain.Category;
import za.ac.cput.carpartmarket.Factory.CategoryFactory;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoryRepositoryTest {

    private static final CategoryRepository repository =
            CategoryRepository.getRepository();

    private static final Category category =
            CategoryFactory.createCategory(
                    "Engine Parts",
                    "Engine components and accessories"
            );

    @Test
    @Order(1)
    void create() {

        Category created = repository.create(category);

        assertNotNull(created);

        System.out.println(created);
    }

    @Test
    @Order(2)
    void read() {

        Category read = repository.read(category.getCategoryId());

        assertNotNull(read);

        System.out.println(read);
    }

    @Test
    @Order(3)
    void update() {

        Category updated = new Category.Builder()
                .copy(category)
                .setCategoryName("Electrical Parts")
                .build();

        Category result = repository.update(updated);

        assertNotNull(result);

        System.out.println(result);
    }

    @Test
    @Order(4)
    void getAll() {

        System.out.println(repository.getAll());

        assertFalse(repository.getAll().isEmpty());
    }

    @Test
    @Order(5)
    void delete() {

        boolean deleted = repository.delete(category.getCategoryId());

        assertTrue(deleted);
    }
}