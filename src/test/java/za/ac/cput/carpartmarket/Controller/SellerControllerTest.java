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
import za.ac.cput.carpartmarket.Domain.Seller;
import za.ac.cput.carpartmarket.Factory.NameFactory;
import za.ac.cput.carpartmarket.Factory.SellerFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureTestRestTemplate
@TestMethodOrder(MethodOrderer.MethodName.class)
class SellerControllerTest {

    private static Seller seller = SellerFactory.createSeller(
            String.valueOf(113L),
            NameFactory.createName("Lulo", "Mokoena"),
            "Car Parts"
    );

    private final String BASE_URL = "http://localhost:8080/sellers";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void a_create() {

        String url = BASE_URL + "/create";

        ResponseEntity<Seller> response =
                restTemplate.postForEntity(
                        url,
                        seller,
                        Seller.class
                );

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        seller = response.getBody();

        System.out.println("Saved Seller: " + seller);
    }

    @Test
    void b_read() {

        String url =
                BASE_URL + "/read/" + seller.getUserid();

        System.out.println("URL: " + url);

        ResponseEntity<Seller> response =
                restTemplate.getForEntity(
                        url,
                        Seller.class
                );

        assertNotNull(response.getBody());

        assertEquals(
                seller.getUserid(),
                response.getBody().getUserid()
        );

        System.out.println("Read Seller: " + response.getBody());
    }

    @Test
    void c_update() {

        Seller updatedSeller = SellerFactory.createSeller(
                seller.getUserid(),
                NameFactory.createName("Lulo", "Mokoena"),
                "Engine Parts"
        );

        String url = BASE_URL + "/update";

        System.out.println("URL: " + url);

        restTemplate.put(url, updatedSeller);

        String readUrl =
                BASE_URL + "/read/" + seller.getUserid();

        ResponseEntity<Seller> response =
                restTemplate.getForEntity(
                        readUrl,
                        Seller.class
                );

        assertNotNull(response.getBody());

        seller = response.getBody();

        System.out.println("Updated Seller: " + seller);

        assertEquals(
                "Engine Parts",
                seller.getSellingPart()
        );
    }

    @Test
    @Disabled
    void d_delete() {

        String url =
                BASE_URL + "/delete/" + seller.getUserid();

        System.out.println("URL: " + url);

        restTemplate.delete(url);

        System.out.println("Delete: true");
    }
}