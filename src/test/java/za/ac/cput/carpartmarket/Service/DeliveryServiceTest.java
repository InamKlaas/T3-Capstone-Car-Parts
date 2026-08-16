package za.ac.cput.carpartmarket.Service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.carpartmarket.Domain.Delivery;
import za.ac.cput.carpartmarket.Factory.DeliveryFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DeliveryServiceTest {

    @Autowired
    private DeliveryService deliveryService;

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

    @Test
    @Order(1)
    void create() {
        Delivery delivery1 = deliveryService.create(delivery);
        assertNotNull(delivery1);
        System.out.println(delivery1);
    }

    @Test
    @Order(2)
    void read() {
        Delivery delivery1 = deliveryService.read(delivery.getDeliveryId());
        assertNotNull(delivery1);
        System.out.println(delivery1);
    }

    @Test
    @Order(3)
    void update() {
        Delivery delivery1 = deliveryService.read(delivery.getDeliveryId());
        assertNotNull(delivery1);
        System.out.println(delivery1);
    }

    @Test
    @Order(4)
    void delete() {
        deliveryService.delete(delivery.getDeliveryId());
    }
}