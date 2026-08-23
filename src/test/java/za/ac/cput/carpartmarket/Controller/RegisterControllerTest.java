package za.ac.cput.carpartmarket.Controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.carpartmarket.Domain.Buyer;
import za.ac.cput.carpartmarket.Domain.Register;
import za.ac.cput.carpartmarket.Factory.BuyerFactory;
import za.ac.cput.carpartmarket.Factory.NameFactory;
import za.ac.cput.carpartmarket.Factory.RegisterFactory;
import za.ac.cput.carpartmarket.Repository.IBuyerRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
@TestMethodOrder(MethodOrderer.MethodName.class)
class RegisterControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private IBuyerRepository buyerRepository;

    private String baseUrl() {
        return restTemplate.getRootUri() + "/registers";
    }

    private static Buyer buyer;
    private static Register register;

    @BeforeAll
    public static void setUp() {
        // Only create the buyer object - don't save to DB yet
        buyer = BuyerFactory.createBuyer("buyer123", NameFactory.createName("Vera", "Doja"), "car pad");
        register = null;
    }

    @Test
    void a_create() {
        buyer = buyerRepository.save(buyer);
        assertNotNull(buyer, "Buyer should be saved successfully");
        System.out.println("Buyer saved: " + buyer.getUserid());
        register = RegisterFactory.createRegister("reg456", buyer, LocalDate.of(2026, 9, 1), "Active");
        assertNotNull(register, "Register should be created");
        System.out.println("Register created: " + register.getRegistrationId());
        String url = baseUrl() + "/create";
        System.out.println("URL: " + url);

        ResponseEntity<Register> postResponse = restTemplate.postForEntity(url, register, Register.class);
        assertNotNull(postResponse, "Response should not be null");
        assertNotNull(postResponse.getBody(), "Response body should not be null");
        assertEquals(HttpStatus.OK, postResponse.getStatusCode(), "Status should be 200 OK");
        Register registerSaved = postResponse.getBody();
        System.out.println("Saved register: " + registerSaved);

        register = registerSaved;
    }

    @Test
    void b_read() {
        assertNotNull(register, "Register must be created first (a_create should run first)");

        String url = baseUrl() + "/read/" + register.getRegistrationId();
        System.out.println("URL: " + url);

        ResponseEntity<Register> response = restTemplate.getForEntity(url, Register.class);

        assertNotNull(response.getBody(), "Response body should not be null");
        assertEquals(register.getRegistrationId(), response.getBody().getRegistrationId(), "Registration ID should match");
        System.out.println("Read register: " + response.getBody());
    }

    @Test
    void c_update() {
        assertNotNull(register, "Register must be created first");

        Register updateRegister = new Register.Builder()
                .copy(register)
                .setStatus("INACTIVE")
                .build();

        String url = baseUrl() + "/update";
        System.out.println("URL: " + url);

        restTemplate.put(url, updateRegister);

        String readUrl = baseUrl() + "/read/" + register.getRegistrationId();
        ResponseEntity<Register> response = restTemplate.getForEntity(readUrl, Register.class);

        assertNotNull(response.getBody(), "Response body should not be null");
        register = response.getBody();
        assertEquals("INACTIVE", register.getStatus(), "Status should be updated to INACTIVE");
        System.out.println("Updated register: " + register);
    }

    @Test
    @Disabled
    void d_deleteById() {
        assertNotNull(register, "Register must be created first");

        String url = baseUrl() + "/delete/" + register.getRegistrationId();
        System.out.println("URL: " + url);

        restTemplate.delete(url);
        System.out.println("Deleted register ID: " + register.getRegistrationId());

        String readUrl = baseUrl() + "/read/" + register.getRegistrationId();
        ResponseEntity<Register> response = restTemplate.getForEntity(readUrl, Register.class);
        assertNull(response.getBody(), "Register should be null after deletion");
    }

    @Test
    void e_getall() {
        String url = baseUrl() + "/getall";
        System.out.println("URL: " + url);

        ResponseEntity<Register[]> response = restTemplate.getForEntity(url, Register[].class);
        assertNotNull(response.getBody(), "Response body should not be null");
        System.out.println("All registers count: " + response.getBody().length);
        for (Register r : response.getBody()) {
            System.out.println("Register: " + r);
        }
    }
}