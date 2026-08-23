package za.ac.cput.carpartmarket.Controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.carpartmarket.Domain.Buyer;
import za.ac.cput.carpartmarket.Domain.Login;
import za.ac.cput.carpartmarket.Factory.BuyerFactory;
import za.ac.cput.carpartmarket.Factory.LoginFactory;
import za.ac.cput.carpartmarket.Factory.NameFactory;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
@AutoConfigureTestRestTemplate
@TestMethodOrder(MethodOrderer.MethodName.class)
class LoginControllerTest {

    private static final Buyer buyer = BuyerFactory.createBuyer(
            "112L",
            NameFactory.createName("Lulo", "Kolisi"),
            "Brake Pads"
    );

    private static Login login = LoginFactory.createLogin(
            201L,
            buyer,
            "lulo@gmail.com",
            "password123",
            LocalDateTime.of(2026, 8, 22, 10, 30),
            "SUCCESS"
    );

    private final String BASE_URL =
            "http://localhost:8080/login";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void a_create() {

        String url = BASE_URL + "/create";

        ResponseEntity<Login> response =
                restTemplate.postForEntity(
                        url,
                        login,
                        Login.class
                );

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        login = response.getBody();

        System.out.println("Saved Login: " + login);
    }

    @Test
    void b_read() {

        String url =
                BASE_URL + "/read/" + login.getLoginId();

        ResponseEntity<Login> response =
                restTemplate.getForEntity(
                        url,
                        Login.class
                );

        assertNotNull(response.getBody());
        assertEquals(
                login.getLoginId(),
                response.getBody().getLoginId()
        );

        System.out.println("Read Login: " + response.getBody());
    }

    @Test
    void c_update() {

        Login updatedLogin = new Login.Builder()
                .copy(login)
                .setStatus("FAILED")
                .build();

        String url = BASE_URL + "/update";

        restTemplate.put(url, updatedLogin);

        String readUrl =
                BASE_URL + "/read/" + login.getLoginId();

        ResponseEntity<Login> response =
                restTemplate.getForEntity(
                        readUrl,
                        Login.class
                );

        assertNotNull(response.getBody());

        login = response.getBody();

        assertEquals("FAILED", login.getStatus());

        System.out.println("Updated Login: " + login);
    }

    @Test
    @Disabled
    void d_delete() {

        String url =
                BASE_URL + "/delete/" + login.getLoginId();

        restTemplate.delete(url);

        System.out.println("Login deleted");
    }
}
