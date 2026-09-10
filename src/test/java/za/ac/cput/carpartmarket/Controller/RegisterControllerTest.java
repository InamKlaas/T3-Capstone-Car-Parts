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
import za.ac.cput.carpartmarket.DTO.RegisterDTO;
import za.ac.cput.carpartmarket.Domain.Buyer;
import za.ac.cput.carpartmarket.Domain.Name;
import za.ac.cput.carpartmarket.Factory.BuyerFactory;
import za.ac.cput.carpartmarket.Factory.NameFactory;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
@TestMethodOrder(MethodOrderer.MethodName.class)
class RegisterControllerTest {

    private static Name name = NameFactory.createName("Vera", "Doja");
    private static Buyer buyer = BuyerFactory.createBuyer(
            "N03",
            name,
            "Brake pads"
    );

    private static RegisterDTO registerDTO;

    String BASE_URL = "/registers";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void a_create() {
        ResponseEntity<Buyer> buyerResponse = restTemplate.postForEntity(
                "/buyers/create", buyer, Buyer.class);
        assertEquals(HttpStatus.OK, buyerResponse.getStatusCode());
        buyer = buyerResponse.getBody();
        System.out.println("Saved buyer: " + buyer);

        RegisterDTO newRegister = new RegisterDTO(
                "reg456",
                buyer.getUserid(),
                LocalDate.of(2020, 3, 23),
                "Pending"
        );

        String url = BASE_URL + "/create";
        System.out.println("URL: " + url);
        ResponseEntity<RegisterDTO> postResponse = restTemplate.postForEntity(url, newRegister, RegisterDTO.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        registerDTO = postResponse.getBody();
        System.out.println("Save data: " + registerDTO);
    }

    @Test
    void b_read() {
        String url = BASE_URL + "/read/" + registerDTO.getRegistrationId();
        System.out.println("URL: " + url);
        ResponseEntity<RegisterDTO> response = restTemplate.getForEntity(url, RegisterDTO.class);
        assertNotNull(response.getBody());
        assertEquals(registerDTO.getRegistrationId(), response.getBody().getRegistrationId());
        System.out.println(response.getBody());
    }

    @Test
    void c_update() {
        RegisterDTO updateRegister = new RegisterDTO(
                registerDTO.getRegistrationId(),
                registerDTO.getUserid(),
                registerDTO.getRegistrationDate(),
                "Approved"
        );

        String url = BASE_URL + "/update";
        System.out.println("URL: " + url);
        ResponseEntity<RegisterDTO> response = restTemplate.exchange(
                url, org.springframework.http.HttpMethod.PUT,
                new org.springframework.http.HttpEntity<>(updateRegister), RegisterDTO.class);
        assertNotNull(response.getBody());
        registerDTO = response.getBody();
        System.out.println("Update data: " + registerDTO);
    }

    @Test
    @Disabled
    void d_delete() {
        String url = BASE_URL + "/delete/" + registerDTO.getRegistrationId();
        System.out.println("URL: " + url);
        restTemplate.delete(url);

        String readUrl = BASE_URL + "/read/" + registerDTO.getRegistrationId();
        ResponseEntity<RegisterDTO> response = restTemplate.getForEntity(readUrl, RegisterDTO.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        System.out.println("Delete: true");
    }

    @Test
    void e_getall() {
        String url = BASE_URL + "/getall";
        System.out.println("URL: " + url);
        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        System.out.println("All registers count: " + response.getBody().size());
    }
}