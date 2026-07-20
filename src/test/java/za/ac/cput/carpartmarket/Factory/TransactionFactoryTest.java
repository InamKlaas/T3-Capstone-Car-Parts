package za.ac.cput.carpartmarket.Factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.carpartmarket.Domain.Transaction;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TransactionFactoryTest {

    @Test
    void createTransaction() {


        Transaction transaction = TransactionFactory.createTransaction(
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

        assertNotNull(transaction);

        System.out.println(transaction);
    }

}