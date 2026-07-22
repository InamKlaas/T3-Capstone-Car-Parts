package za.ac.cput.carpartmarket.Service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.carpartmarket.Domain.Transaction;
import za.ac.cput.carpartmarket.Factory.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class TransactionServiceTest {

    @Autowired
    TransactionService transactionService;

    private static Transaction transaction = TransactionFactory.createTransaction(
            12L,
            LocalDateTime.of(2020,3,22,0,0),
            OrderFactory.createOrder(
                    334L,
                    BuyerFactory.createBuyer(
                            4L,
                            NameFactory.createName("Vera", "Doja"),
                            UserFactory.createUser(
                                    NameFactory.createName("Vera", "Doja"),
                                    "vera@cput.ac.za",
                                    "beberexa",
                                    "0784521012",
                                    "23-02-2020"
                            )
                    ),
                    "pending",
                    LocalDateTime.of(2020,3,23,0,0),
                    2345678.00,
                    2L
            ),
            4445678.00
    );

    @Test
    @Order(1)
    void create() {
        Transaction transaction1= transactionService.create(transaction);
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