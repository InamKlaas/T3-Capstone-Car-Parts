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
import za.ac.cput.carpartmarket.Domain.Buyer;
import za.ac.cput.carpartmarket.Domain.Login;
import za.ac.cput.carpartmarket.Domain.Name;
import za.ac.cput.carpartmarket.Factory.BuyerFactory;
import za.ac.cput.carpartmarket.Factory.LoginFactory;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureTestRestTemplate
@TestMethodOrder(MethodOrderer.MethodName.class)
class LoginControllerTest {

    private static Name name = new Name.Builder()
            .setFirstName("Lulo")
            .setLastName("Kolisi")
            .build();

    private static Buyer buyer = BuyerFactory.createBuyer(
            String.valueOf(112L),
            name,
            "Brake Pads"
    );

    private static Login login = LoginFactory.createLogin(
            "LOGIN001",
            buyer,
            "lulo@gmail.com",
            "password123",
            LocalDateTime.of(2026, 8, 24, 10, 30),
            "Successful"
    );

    String BASE_URL = "http://localhost:8080/logins";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void a_create() {

        ResponseEntity<Buyer> buyerResponse = restTemplate.postForEntity(
                "http://localhost:8080/buyer/create",
                buyer,
                Buyer.class
        );

        assertEquals(HttpStatus.OK, buyerResponse.getStatusCode());

        buyer = buyerResponse.getBody();

        System.out.println("Saved buyer: " + buyer);

        Login loginToSave = new Login.Builder()
                .copy(login)
                .setUser(buyer)
                .build();

        String url = BASE_URL;

        ResponseEntity<Login> postResponse =
                restTemplate.postForEntity(
                        url,
                        loginToSave,
                        Login.class
                );

        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());

        Login loginSaved = postResponse.getBody();

        System.out.println("Save data: " + loginSaved);

        login = loginSaved;
    }

    @Test
    void b_read() {

        String url = BASE_URL + "/" + login.getLoginId();

        System.out.println("URL: " + url);

        ResponseEntity<Login> response =
                restTemplate.getForEntity(
                        url,
                        Login.class
                );

        assertNotNull(response);
        assertNotNull(response.getBody());

        assertEquals(
                login.getLoginId(),
                response.getBody().getLoginId()
        );

        System.out.println(response.getBody());
    }

    @Test
    void c_update() {

        Login updateLogin = new Login.Builder()
                .copy(login)
                .setStatus("Failed")
                .build();

        String url = BASE_URL;

        System.out.println("URL: " + url);

        restTemplate.put(url, updateLogin);

        String readUrl =
                BASE_URL + "/" + login.getLoginId();

        ResponseEntity<Login> response =
                restTemplate.getForEntity(
                        readUrl,
                        Login.class
                );

        System.out.println(response.getBody());

        assertNotNull(response.getBody());

        login = response.getBody();

        System.out.println("Update data: " + login);
    }

    @Test
    @Disabled
    void d_delete() {

        String url =
                BASE_URL + "/" + login.getLoginId();

        System.out.println("URL: " + url);

        restTemplate.delete(url);

        ResponseEntity<Login> response =
                restTemplate.getForEntity(
                        url,
                        Login.class
                );

        assertEquals(HttpStatus.OK, response.getStatusCode());

        System.out.println("Delete: true");
    }
}