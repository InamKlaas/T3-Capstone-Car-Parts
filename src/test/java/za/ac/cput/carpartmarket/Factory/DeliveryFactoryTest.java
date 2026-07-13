package za.ac.cput.carpartmarket.Factory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class DeliveryFactoryTest {


    @Test
    void createDelivery() {

        Delivery delivery = DeliveryFactory.createDelivery(
                "Courier Guy",
                "TRK123456",
                "2026-06-30",
                "Pending"
        );

        assertNotNull(delivery);

        System.out.println(delivery);
    }

    @Test
    void createDeliveryWithNullCourierName() {

        Delivery delivery = DeliveryFactory.createDelivery(
                "",
                "TRK123456",
                "2026-06-30",
                "Pending"
        );

        assertNull(delivery);
    }
}
