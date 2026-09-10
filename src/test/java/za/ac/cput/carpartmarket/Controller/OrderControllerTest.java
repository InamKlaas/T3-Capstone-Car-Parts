package za.ac.cput.carpartmarket.Controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.carpartmarket.Domain.Buyer;
import za.ac.cput.carpartmarket.Domain.Name;
import za.ac.cput.carpartmarket.Domain.Order;
import za.ac.cput.carpartmarket.Factory.BuyerFactory;
import za.ac.cput.carpartmarket.Factory.OrderFactory;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
@TestMethodOrder(MethodOrderer.MethodName.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrderControllerTest {

    private static Name name = new Name.Builder()
            .setFirstName("Vera")
            .setLastName("Doja")
            .build();

    private static Buyer buyer = BuyerFactory.createBuyer(
            "1L",
            name,
            "Brake pads"
    );

    private static Order order = OrderFactory.createOrder(
            "2L",
            buyer,
            "Pending",
            LocalDateTime.of(2020, 3, 23, 0, 0),
            1500.00,
            "11L"
    );

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl(){return restTemplate.getRootUri() + "/orders";}

    private String buyerUrl(){return restTemplate.getRootUri() + "/buyers";}

    @BeforeAll
    void setUp() {
        String url = buyerUrl() + "/create";
        System.out.println("URL: " + url);
        ResponseEntity<Buyer> buyerResponse = restTemplate.postForEntity(url, buyer, Buyer.class);
        assertEquals(HttpStatus.OK, buyerResponse.getStatusCode());
        buyer = buyerResponse.getBody();
        System.out.println("Saved buyer: " + buyer);

        order = new Order.Builder().copy(order).setBuyer(buyer).build();
    }

    @Test
    void a_create() {
        String url = baseUrl();
        System.out.println("URL: " + url);
        ResponseEntity<Order> postResponse = restTemplate.postForEntity(url, order, Order.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        Order orderSaved = postResponse.getBody();
        System.out.println("Save data: " + orderSaved);
        order = orderSaved;
    }

    @Test
    void b_read() {
        String url = baseUrl() + "/" + order.getOrderId();
        System.out.println("URL: " + url);
        ResponseEntity<Order> response = restTemplate.getForEntity(url, Order.class);
        assertEquals(order.getOrderId(), response.getBody().getOrderId());
        System.out.println(response.getBody());
    }

    @Test
    void c_update() {
        Order updateOrder = new Order.Builder()
                .copy(order)
                .setStatus("Shipped")
                .build();
        String url = baseUrl();
        System.out.println("URL: " + url);
        restTemplate.put(url, updateOrder);

        String readUrl = baseUrl() + "/" + order.getOrderId();
        ResponseEntity<Order> response = restTemplate.getForEntity(readUrl, Order.class);
        System.out.println(response.getBody());
        order = response.getBody();
        System.out.println("Update data: " + order);
    }

    @Test
    @Disabled
    void d_delete() {
        String url = baseUrl() + "/" + order.getOrderId();
        System.out.println("URL: " + url);
        restTemplate.delete(url);

        ResponseEntity<Order> response = restTemplate.getForEntity(url, Order.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        System.out.println("Delete: true");
    }

}