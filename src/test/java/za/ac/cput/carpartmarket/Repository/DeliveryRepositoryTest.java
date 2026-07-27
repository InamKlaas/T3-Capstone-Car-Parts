package za.ac.cput.carpartmarket.Repository;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import za.ac.cput.carpartmarket.Domain.Delivery;
import za.ac.cput.carpartmarket.Factory.DeliveryFactory;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryRepositoryTest {

    private static Delivery D1 = DeliveryFactory.createDelivery("Courier Guy", "TRK123456", "2026-06-30", "Pending");
    private static Delivery D2 = DeliveryFactory.createDelivery("Fastway", "TRK654321", "2026-07-15", "In Transit");
    private static Delivery D3 = DeliveryFactory.createDelivery("", "TRK654321", "2026-07-15", "In Transit");

    @Test
    @Order(1)
    public void createDelivery() {
        assertNotNull(D1);
        System.out.println(D1.toString());
    }

    @Test
    @Order(2)
    public void testCreateDeliveryWithAllAttributes() {
        assertNotNull(D2);
        System.out.println(D2.toString());
    }

    @Test
    @Order(3)
    public void testCreateDeliveryThatFails() {
        assertNotNull(D3);
        System.out.println(D3.toString());
    }
}