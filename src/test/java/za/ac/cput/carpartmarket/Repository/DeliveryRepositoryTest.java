package za.ac.cput.carpartmarket.Repository;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.carpartmarket.Domain.Delivery;
import za.ac.cput.carpartmarket.Factory.DeliveryFactory;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DeliveryRepositoryTest {

    private static Delivery D1 = DeliveryFactory.createDelivery(877788L,56545L, 56545L, "DHL", "TRK123456", "2026-06-30", "In Transit", 15.00);
    private static Delivery D2 = DeliveryFactory.createDelivery(877789L,56546L, 56546L, "Fastway", "TRK654321", "2026-07-15", "In Transit", 20.00);
    private static Delivery D3 = DeliveryFactory.createDelivery(877790L,56547L, 56547L, "", "TRK654321", "2026-07-15", "In Transit", 25.00);

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
        assertNull(D3);
        System.out.println(D3);
    }
}