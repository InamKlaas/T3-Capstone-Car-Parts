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
import za.ac.cput.carpartmarket.Domain.Name;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureTestRestTemplate
@TestMethodOrder(MethodOrderer.MethodName.class)
class AdminControllerTest {

    private static Admin admin = new Admin.Builder()
            .setUserid("A112L")
            .setName(new Name.Builder()
                    .setFirstName("Inam")
                    .setLastName("Klaas")
                    .build())
            .setEmail("inam.klaas@carpartmarket.co.za")
            .setPassword("Password123")
            .setPhoneNumber("0821234567")
            .setCreatedAt("2025-01-01")
            .setRole("MODERATOR")
            .setPermissions("READ_ONLY")
            .build();

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
    void c_update() {

        Admin updatedAdmin = new Admin.Builder()
                .copy(admin)
                .setName(new Name.Builder()
                        .setFirstName("Lulo")
                        .setLastName("Mokoena")
                        .build())
                .setRole("SUPER_ADMIN")
                .setPermissions("ALL")
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
                "SUPER_ADMIN",
                admin.getRole()
        );

        assertEquals(
                "ALL",
                admin.getPermissions()
        );
    }

    @Test
    @Disabled
    void d_delete() {

        String url =
                BASE_URL + "/" + admin.getUserid();

        System.out.println("URL: " + url);

        restTemplate.delete(url);

        System.out.println("Delete: true");
    }
}