package za.ac.cput.carpartmarket.Service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.carpartmarket.Domain.Buyer;
import za.ac.cput.carpartmarket.Domain.Order;
import za.ac.cput.carpartmarket.Factory.BuyerFactory;
import za.ac.cput.carpartmarket.Factory.NameFactory;
import za.ac.cput.carpartmarket.Factory.OrderFactory;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    private static Order order;

    @BeforeAll
    static void setUp(@Autowired BuyerService buyerService){
        Buyer buyer = BuyerFactory.createBuyer(
                112L,
                NameFactory.createName("Vera", "Doja"),
                "Car pad"
        );

        Buyer savedBuyer = buyerService.create(buyer);

        order = OrderFactory.createOrder(
                501L,
                savedBuyer, "pending",
                LocalDateTime.of(2020, 9, 23, 0, 0),
                2345678.00,
                2L
        );
    }



    @Test
    void a_create() {
        Order order1 = orderService.create(order);
        assertNotNull(order1);
        System.out.println(order1);
    }

    @Test

    void b_read() {
        Order order1 = orderService.read(order.getOrderId());
        assertNotNull(order1);
        System.out.println(order1);
    }

    @Test
    void c_update() {
        Order order1 = orderService.read(order.getOrderId());
        assertNotNull(order1);
        System.out.println(order1);
    }

    @Test
    @Disabled
    void d_delete() {
        orderService.delete(order.getOrderId());
    }
}