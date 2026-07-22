package za.ac.cput.carpartmarket.Service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.carpartmarket.Domain.Order;
import za.ac.cput.carpartmarket.Factory.BuyerFactory;
import za.ac.cput.carpartmarket.Factory.NameFactory;
import za.ac.cput.carpartmarket.Factory.OrderFactory;
import za.ac.cput.carpartmarket.Factory.UserFactory;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderServiceTest {

    @Autowired
   private OrderService orderService;

    private static Order order = OrderFactory.createOrder(
            501L,
            BuyerFactory.createBuyer(
                    112L,
                    NameFactory.createName("Vera", "Doja"),
                    UserFactory.createUser(
                            NameFactory.createName("Vera", "Doja"),
                            "vera@cput.ac.za",
                            "beberexa",
                            "012457896",
                            "23-09-2020"
                    )
            ),
            "pending",
            LocalDateTime.of(2020, 9, 23, 0, 0),
            2345678.00,
            2L
    );

    @Test
    void create() {
        Order order1 = orderService.create(order);
        assertNotNull(order1);
        System.out.println(order1);
    }

    @Test
    void read() {
        Order order1= orderService.read(order.getOrderId());
        assertNotNull(order1);
        System.out.println(order1);
    }

    @Test
    void update() {
        Order order1 = orderService.read(order.getOrderId());
        assertNotNull(order1);
        System.out.println(order1);
    }

    @Test
    void delete() {
        orderService.delete(order.getOrderId());

    }
}