package za.ac.cput.carpartmarket.Controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.carpartmarket.Domain.Category;
import za.ac.cput.carpartmarket.Factory.CategoryFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureTestRestTemplate
@TestMethodOrder(MethodOrderer.MethodName.class)
class CategoryControllerTest {

    private static Category category = CategoryFactory.createCategory(
            "Brake Parts",
            "Brake pads, discs, calipers, and related components"
    );

    private final String BASE_URL = "http://localhost:8080/api/categories";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void a_create() {

        String url = BASE_URL;

        ResponseEntity<Category> response =
                restTemplate.postForEntity(
                        url,
                        category,
                        Category.class
                );

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        category = response.getBody();

        System.out.println("Saved Category: " + category);
    }

    @Test
    void b_read() {

        String url =
                BASE_URL + "/" + category.getCategoryId();

        System.out.println("URL: " + url);

        ResponseEntity<Category> response =
                restTemplate.getForEntity(
                        url,
                        Category.class
                );

        assertNotNull(response.getBody());

        assertEquals(
                category.getCategoryId(),
                response.getBody().getCategoryId()
        );

        System.out.println("Read Category: " + response.getBody());
    }

    @Test
    void c_getAll() {

        String url = BASE_URL;

        System.out.println("URL: " + url);

        ResponseEntity<Category[]> response =
                restTemplate.getForEntity(
                        url,
                        Category[].class
                );

        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);

        System.out.println("All Categories: " + response.getBody().length);
    }

    @Test
    void d_update() {

        Category updatedCategory = new Category.Builder()
                .copy(category)
                .setCategoryName("Engine Parts")
                .setDescription("Pistons, gaskets, belts, and related components")
                .build();

        String url = BASE_URL + "/" + category.getCategoryId();

        System.out.println("URL: " + url);

        restTemplate.put(url, updatedCategory);

        String readUrl =
                BASE_URL + "/" + category.getCategoryId();

        ResponseEntity<Category> response =
                restTemplate.getForEntity(
                        readUrl,
                        Category.class
                );

        assertNotNull(response.getBody());

        category = response.getBody();

        System.out.println("Updated Category: " + category);

        assertEquals(
                "Engine Parts",
                category.getCategoryName()
        );
    }

    @Test
    @Disabled
    void e_delete() {

        String url =
                BASE_URL + "/" + category.getCategoryId();

        System.out.println("URL: " + url);

        restTemplate.delete(url);

        System.out.println("Delete: true");
    }
}