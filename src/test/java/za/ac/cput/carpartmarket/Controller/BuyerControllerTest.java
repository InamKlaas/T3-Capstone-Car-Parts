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
import za.ac.cput.carpartmarket.Domain.Name;
import za.ac.cput.carpartmarket.Factory.BuyerFactory;
import za.ac.cput.carpartmarket.Factory.NameFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureTestRestTemplate
@TestMethodOrder(MethodOrderer.MethodName.class)
class lBuyerControllerTest {

    private static Name name = new Name.Builder()
            .setFirstName("Vera")
            .setLastName("Doja")
            .build();

    private static Buyer buyer = BuyerFactory.createBuyer(
            112L,
            name,
            "Brake pads"
    );

    String BASE_URL = "http://localhost:8080/buyer";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void a_create() {

        String url = BASE_URL;

        ResponseEntity<Buyer> postResponse =
                restTemplate.postForEntity(
                        url,
                        buyer,
                        Buyer.class
                );

        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());

        Buyer buyerSaved = postResponse.getBody();

        System.out.println("Save data: " + buyerSaved);

        buyer = buyerSaved;
    }

    @Test
    void b_read() {

        String url =
                BASE_URL + "/" + buyer.getUserid();

        System.out.println("URL: " + url);

        ResponseEntity<Buyer> response =
                restTemplate.getForEntity(
                        url,
                        Buyer.class
                );

        assertNotNull(response);
        assertNotNull(response.getBody());

        assertEquals(
                buyer.getUserid(),
                response.getBody().getUserid()
        );

        System.out.println(response.getBody());
    }

    @Test
    void c_update() {

        Buyer updateBuyer = BuyerFactory.createBuyer(
                buyer.getUserid(),
                NameFactory.createName(
                        "Vera",
                        "Smith"
                ),
                "Engine Parts"
        );

        String url = BASE_URL;

        System.out.println("URL: " + url);

        restTemplate.put(url, updateBuyer);

        String readUrl =
                BASE_URL + "/" + buyer.getUserid();

        ResponseEntity<Buyer> response =
                restTemplate.getForEntity(
                        readUrl,
                        Buyer.class
                );

        System.out.println(response.getBody());

        buyer = response.getBody();

        System.out.println("Update data: " + buyer);
    }

    @Test
    @Disabled
    void d_delete() {

        String url =
                BASE_URL + "/" + buyer.getUserid();

        System.out.println("URL: " + url);

        restTemplate.delete(url);

        ResponseEntity<Buyer> response =
                restTemplate.getForEntity(
                        url,
                        Buyer.class
                );

        assertEquals(HttpStatus.OK, response.getStatusCode());

        System.out.println("Delete: true");
    }
}

