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
import za.ac.cput.carpartmarket.Factory.BuyerFactory;
import za.ac.cput.carpartmarket.Factory.NameFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
@TestMethodOrder(MethodOrderer.MethodName.class)
class BuyerControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static Buyer buyer = BuyerFactory.createBuyer(
            "112eL",
            NameFactory.createName("Lulo", "Kolisi"),
            "Brake Pads"
    );

    private String baseUrl(){return restTemplate.getRootUri() + "/buyers";}

    @Test
    void a_create() {
        String url = baseUrl() + "/create";
        System.out.println("URL: " + url);
        ResponseEntity<Buyer> postResponse = restTemplate.postForEntity(url, buyer, Buyer.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        Buyer buyerSaved = postResponse.getBody();
        System.out.println("Save data: " + buyerSaved);
        buyer = buyerSaved;
    }

    @Test
    void b_read() {
        String url = baseUrl() + "/read/" + buyer.getUserid();
        System.out.println("URL: " + url);
        ResponseEntity<Buyer> response = restTemplate.getForEntity(url, Buyer.class);
        assertEquals(buyer.getUserid(), response.getBody().getUserid());
        System.out.println(response.getBody());
    }

    @Test
    void c_update() {
        Buyer updateBuyer = new Buyer.Builder()
                .copy(buyer)
                .setBuyingPart("Engine Parts")
                .build();
        String url = baseUrl() + "/update";
        System.out.println("URL: " + url);
        restTemplate.put(url, updateBuyer);

        String readUrl = baseUrl() + "/read/" + buyer.getUserid();
        ResponseEntity<Buyer> response = restTemplate.getForEntity(readUrl, Buyer.class);
        assertNotNull(response.getBody());
        buyer = response.getBody();
        System.out.println("Update data: " + buyer);

        assertEquals("Engine Parts", buyer.getBuyingPart());
    }

    @Test
    @Disabled
    void d_delete() {
        String url = baseUrl() + "/delete/" + buyer.getUserid();
        System.out.println("URL: " + url);
        restTemplate.delete(url);

        String readUrl = baseUrl() + "/read/" + buyer.getUserid();
        ResponseEntity<Buyer> response = restTemplate.getForEntity(readUrl, Buyer.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        System.out.println("Delete: true");
    }
}