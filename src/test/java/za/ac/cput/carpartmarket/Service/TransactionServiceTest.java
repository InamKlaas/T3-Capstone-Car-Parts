package za.ac.cput.carpartmarket.Service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.carpartmarket.Domain.Buyer;
import za.ac.cput.carpartmarket.Domain.Transaction;
import za.ac.cput.carpartmarket.Factory.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class TransactionServiceTest {

    @Autowired
    TransactionService transactionService;

    private static Transaction transaction;

    @BeforeAll
    static void setUp(@Autowired BuyerService buyerService, @Autowired OrderService orderService){
        Buyer buyer = BuyerFactory.createBuyer(
                "4L",
                NameFactory.createName("Vera","Doja"),
                "Car pads"
        );

        Buyer savedBuyer = buyerService.create(buyer);

        za.ac.cput.carpartmarket.Domain.Order order = OrderFactory.createOrder(
                "334L",
                savedBuyer,
                "pending",
                LocalDateTime.of(2020, 3, 23, 0, 0),
                2345678.00,
                "2L"
        );

        za.ac.cput.carpartmarket.Domain.Order savedOrder = orderService.create(order);

        transaction = TransactionFactory.createTransaction(
                "12L",
                LocalDateTime.of(2020, 3, 22, 0, 0),
                savedOrder,
                4445678.00
        );
    }




    @Test
    @Order(1)
    void create() {
        Transaction transaction1 = transactionService.create(transaction);
        assertNotNull(transaction1);
        System.out.println(transaction1);
    }

    @Test
    @Order(2)
    void read() {
        Transaction transaction1 = transactionService.read(transaction.getTransactionId());
        assertNotNull(transaction1);
        System.out.println(transaction1);
    }

    @Test
    @Order(3)
    void update() {
        Transaction transaction1 = transactionService.update(transaction);
        assertNotNull(transaction1);
        System.out.println(transaction1);
    }

    @Test
    @Order(4)
    void delete() {
        transactionService.delete(transaction.getTransactionId());
    }
}