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
import za.ac.cput.carpartmarket.Domain.Admin;
import za.ac.cput.carpartmarket.Factory.AdminFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureTestRestTemplate
@TestMethodOrder(MethodOrderer.MethodName.class)
class AdminControllerTest {

    private static Admin admin = AdminFactory.createAdmin(
            "201A",
            "Super Admin",
            "READ_WRITE_DELETE"
    );

    private final String BASE_URL = "http://localhost:8080/api/admins";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void a_create() {

        String url = BASE_URL;

        ResponseEntity<Admin> response =
                restTemplate.postForEntity(
                        url,
                        admin,
                        Admin.class
                );

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        admin = response.getBody();

        System.out.println("Saved Admin: " + admin);
    }

    @Test
    void b_read() {

        String url =
                BASE_URL + "/" + admin.getUserid();

        System.out.println("URL: " + url);

        ResponseEntity<Admin> response =
                restTemplate.getForEntity(
                        url,
                        Admin.class
                );

        assertNotNull(response.getBody());

        assertEquals(
                admin.getUserid(),
                response.getBody().getUserid()
        );

        System.out.println("Read Admin: " + response.getBody());
    }

    @Test
    void c_getAll() {

        String url = BASE_URL;

        System.out.println("URL: " + url);

        ResponseEntity<Admin[]> response =
                restTemplate.getForEntity(
                        url,
                        Admin[].class
                );

        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);

        System.out.println("All Admins: " + response.getBody().length);
    }

    @Test
    void d_update() {

        Admin updatedAdmin = new Admin.Builder()
                .copy(admin)
                .setRole("Moderators")
                .setPermissions("READ_ONLY")
                .build();

        String url = BASE_URL + "/" + admin.getUserid();

        System.out.println("URL: " + url);

        restTemplate.put(url, updatedAdmin);

        String readUrl =
                BASE_URL + "/" + admin.getUserid();

        ResponseEntity<Admin> response =
                restTemplate.getForEntity(
                        readUrl,
                        Admin.class
                );

        assertNotNull(response.getBody());

        admin = response.getBody();

        System.out.println("Updated Admin: " + admin);

        assertEquals(
                "Moderator",
                admin.getRole()
        );
    }

    @Test
    @Disabled
    void e_delete() {

        String url =
                BASE_URL + "/" + admin.getUserid();

        System.out.println("URL: " + url);

        restTemplate.delete(url);

        System.out.println("Delete: true");
    }
}