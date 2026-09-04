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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
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

//    private final String BASE_URL = "http://localhost:8080/buyers";
    private String baseUrl(){return restTemplate.getRootUri() + "/buyerss";}

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
                .setPassword("passwords")
                .build();
        String url = baseUrl() + "/update";
        System.out.println("URL: " + url);
        restTemplate.put(url, updateBuyer);

        String readUrl = baseUrl() + "/read/" + buyer.getUserid();
        ResponseEntity<Buyer> response = restTemplate.getForEntity(readUrl, Buyer.class);
        System.out.println(response.getBody());
        buyer = response.getBody();
        System.out.println("Update data: " + buyer);
    }

    @Test
    void d_delete() {
        String url = baseUrl() + "/delete/" + buyer.getUserid();
        System.out.println("URL: " + url);
        restTemplate.delete(url);
        System.out.println("Delete: true");
    }

//    @Test
//    void a_create() {
//
//        String url = BASE_URL + "/create";
//
//        ResponseEntity<Buyer> response =
//                restTemplate.postForEntity(
//                        url,
//                        buyer,
//                        Buyer.class
//                );
//
//        assertNotNull(response);
//        assertNotNull(response.getBody());
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//        buyer = response.getBody();
//
//        System.out.println("Saved Buyer: " + buyer);
//    }

//    @Test
//    void b_read() {
//
//        String url =
//                BASE_URL + "/read/" + buyer.getUserid();
//
//        System.out.println("URL: " + url);
//
//        ResponseEntity<Buyer> response =
//                restTemplate.getForEntity(
//                        url,
//                        Buyer.class
//                );
//
//        assertNotNull(response.getBody());
//
//        assertEquals(
//                buyer.getUserid(),
//                response.getBody().getUserid()
//        );
//
//        System.out.println("Read Buyer: " + response.getBody());
//    }

//    @Test
//    void c_update() {
//
//        Buyer updatedBuyer = new Buyer.Builder()
//                .setUserid(buyer.getUserid())
//                .setBuyerName(
//                        NameFactory.createName("Lulo", "Mokoena")
//                )
//                .setBuyingPart("Engine Parts")
//                .build();
//
//        String url = BASE_URL + "/update";
//
//        System.out.println("URL: " + url);
//
//        restTemplate.put(url, updatedBuyer);
//
//        String readUrl =
//                BASE_URL + "/read/" + buyer.getUserid();
//
//        ResponseEntity<Buyer> response =
//                restTemplate.getForEntity(
//                        readUrl,
//                        Buyer.class
//                );
//
//        assertNotNull(response.getBody());
//
//        buyer = response.getBody();
//
//        System.out.println("Updated Buyer: " + buyer);
//
//        assertEquals(
//                "Engine Parts",
//                buyer.getBuyingPart()
//        );
//    }

//    @Test
//    @Disabled
//    void d_delete() {
//
//        String url =
//                BASE_URL + "/delete/" + buyer.getUserid();
//
//        System.out.println("URL: " + url);
//
//        restTemplate.delete(url);
//
//        System.out.println("Delete: true");
//    }
}
