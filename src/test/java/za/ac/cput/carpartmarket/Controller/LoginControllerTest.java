package za.ac.cput.carpartmarket.Controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.carpartmarket.DTO.LoginDTO;
import za.ac.cput.carpartmarket.Domain.Buyer;
import za.ac.cput.carpartmarket.Factory.BuyerFactory;
import za.ac.cput.carpartmarket.Factory.NameFactory;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
@TestMethodOrder(MethodOrderer.MethodName.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LoginControllerTest {

    private static Buyer buyer = BuyerFactory.createBuyer(
            "112L",
            NameFactory.createName("Lulo", "Kolisi"),
            "Brake Pads"
    );

    private static LoginDTO loginDTO;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl() {
        return "http://localhost:" + port + "/login";
    }

    private String buyerUrl() {
        return "http://localhost:" + port + "/buyers";
    }

    @BeforeAll
    void setUp() {
        String url = buyerUrl() + "/create";
        System.out.println("URL: " + url);
        ResponseEntity<Buyer> buyerResponse = restTemplate.postForEntity(url, buyer, Buyer.class);
        assertEquals(HttpStatus.OK, buyerResponse.getStatusCode());
        buyer = buyerResponse.getBody();
        System.out.println("Saved buyer: " + buyer);
    }

    @Test
    void a_create() {
        LoginDTO newLogin = new LoginDTO(
                "201L",
                buyer.getUserid(),
                "lulo@gmail.com",
                "password123",
                LocalDateTime.of(2026, 8, 22, 10, 30),
                "SUCCESS"
        );

        String url = baseUrl() + "/create";
        ResponseEntity<LoginDTO> response = restTemplate.postForEntity(url, newLogin, LoginDTO.class);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        loginDTO = response.getBody();
        System.out.println("Saved Login: " + loginDTO);
    }

    @Test
    void b_read() {
        String url = baseUrl() + "/read/" + loginDTO.getLoginId();
        ResponseEntity<LoginDTO> response = restTemplate.getForEntity(url, LoginDTO.class);

        assertNotNull(response.getBody());
        assertEquals(loginDTO.getLoginId(), response.getBody().getLoginId());
        System.out.println("Read Login: " + response.getBody());
    }

    @Test
    void c_update() {
        LoginDTO updateLogin = new LoginDTO(
                loginDTO.getLoginId(),
                loginDTO.getUserid(),
                loginDTO.getEmail(),
                loginDTO.getPassword(),
                loginDTO.getLoginDate(),
                "FAILED"
        );

        String url = baseUrl() + "/update";
        ResponseEntity<LoginDTO> response = restTemplate.exchange(
                url, org.springframework.http.HttpMethod.PUT,
                new org.springframework.http.HttpEntity<>(updateLogin), LoginDTO.class);

        assertNotNull(response.getBody());
        loginDTO = response.getBody();
        assertEquals("FAILED", loginDTO.getStatus());
        System.out.println("Updated Login: " + loginDTO);
    }

    @Test
    @Disabled
    void d_delete() {
        String url = baseUrl() + "/delete/" + loginDTO.getLoginId();
        restTemplate.delete(url);

        String readUrl = baseUrl() + "/read/" + loginDTO.getLoginId();
        ResponseEntity<LoginDTO> response = restTemplate.getForEntity(readUrl, LoginDTO.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        System.out.println("Login deleted");
    }
}