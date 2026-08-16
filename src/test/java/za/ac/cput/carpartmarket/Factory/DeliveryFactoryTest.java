package za.ac.cput.carpartmarket.Factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.carpartmarket.Domain.Delivery;

import static org.junit.jupiter.api.Assertions.*;


class DeliveryFactoryTest {


    @Test
    void createDelivery() {

        Delivery delivery = DeliveryFactory.createDelivery(
                9483L,
                373383L,
                9393L,
                "Pending",
                "TRK123456",
                "2026-06-30",
                "In Transit",
                15.00
        );

        assertNotNull(delivery);

        System.out.println(delivery);
    }

    @Test
    void createDeliveryWithNullCourierName() {

        Delivery delivery = DeliveryFactory.createDelivery(
                9483L,
                373383L,
                9393L,
                "Courier Guy",
                "TRK123456",
                "2026-06-30",
                "In Transit",
                15.00
        );

        assertNull(delivery);
    }
}
