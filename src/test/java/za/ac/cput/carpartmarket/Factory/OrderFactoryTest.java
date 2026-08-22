package za.ac.cput.carpartmarket.Factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.carpartmarket.Domain.Order;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class OrderFactoryTest {

    @Test
    void createOrder() {
        Order order = OrderFactory.createOrder(
                "501L",
                BuyerFactory.createBuyer(
                        "112L",
                        NameFactory.createName("Vera", "Doja"),
                        "Car Parts"
                ),
                "pending",
                LocalDateTime.of(2020, 9, 23, 0, 0),
                2345678.00,
                "2L"
        );

        assertNotNull(order);

        System.out.println(order);
    }
}