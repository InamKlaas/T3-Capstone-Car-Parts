package za.ac.cput.carpartmarket.Controller;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.carpartmarket.Domain.Delivery;
import za.ac.cput.carpartmarket.Factory.DeliveryFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
@TestMethodOrder(MethodOrderer.MethodName.class)
class DeliveryControllerTest {

    private static Delivery delivery = DeliveryFactory.createDelivery(
            9483L,
            373383L,
            9393L,
            "Courier Guy",
            "TRK123456",
            "2026-06-30",
            "In Transit",
            15.00
    );

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl() {
        return restTemplate.getRootUri() + "/delivery";
    }

    @Test
    void a_create() {
        String url = baseUrl() + "/create";
        System.out.println("URL: " + url);
        ResponseEntity<Delivery> postResponse = restTemplate.postForEntity(url, delivery, Delivery.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        Delivery deliverySaved = postResponse.getBody();
        System.out.println("Save data: " + deliverySaved);
        delivery = deliverySaved;
    }

    @Test
    void b_read() {
        String url = baseUrl() + "/read/" + delivery.getDeliveryId();
        System.out.println("URL: " + url);
        ResponseEntity<Delivery> response = restTemplate.getForEntity(url, Delivery.class);
        assertEquals(delivery.getDeliveryId(), response.getBody().getDeliveryId());
        System.out.println(response.getBody());
    }

    @Test
    void c_update() {
        Delivery updateDelivery = new Delivery.Builder()
                .copy(delivery)
                .setDeliveryStatus("Delivered")
                .build();
        String url = baseUrl() + "/update";
        System.out.println("URL: " + url);
        restTemplate.put(url, updateDelivery);

        String readUrl = baseUrl() + "/read/" + delivery.getDeliveryId();
        ResponseEntity<Delivery> response = restTemplate.getForEntity(readUrl, Delivery.class);
        System.out.println(response.getBody());
        delivery = response.getBody();
        System.out.println("Update data: " + delivery);
    }

    @Test
    void d_deleteById() {
        String url = baseUrl() + "/delete/" + delivery.getDeliveryId();
        System.out.println("URL: " + url);
        restTemplate.delete(url);
        System.out.println("Delete: true");
    }
}