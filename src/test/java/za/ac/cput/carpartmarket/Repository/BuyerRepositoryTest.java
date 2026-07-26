package za.ac.cput.carpartmarket.Repository;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import za.ac.cput.carpartmarket.Domain.Buyer;
import za.ac.cput.carpartmarket.Factory.NameFactory;
import za.ac.cput.carpartmarket.Factory.UserFactory;
import za.ac.cput.cput.carpartmarket.Factory.BuyerFactory;

import static org.junit.jupiter.api.Assertions.*;

class BuyerRepositoryTest {

    private static Buyer B1 = BuyerFactory.createBuyer(
            112L,
            NameFactory.createName("Vera", "Doja"),
            UserFactory.createUser(
                    101L,
                    NameFactory.createName("Vera", "Doja"),
                    "vera@cput.ac.za",
                    "beberexa",
                    012457896,
                    "23-09-2020"
            )
    );

    private static Buyer B2 = BuyerFactory.createBuyer(
            113L,
            NameFactory.createName("John", "Doe"),
            UserFactory.createUser(
                    102L,
                    NameFactory.createName("John", "Doe"),
                    "john@cput.ac.za",
                    "password123",
                    084567891,
                    "15-01-2021"
            )
    );

    private static Buyer B3 = BuyerFactory.createBuyer(
            114L,
            NameFactory.createName("Sarah", "Smith"),
            UserFactory.createUser(
                    103L,
                    NameFactory.createName("Sarah", "Smith"),
                    "sarah@cput.ac.za",
                    "sarah123",
                    073456789,
                    "10-03-2022"
            )
    );

    @Test
    @Order(1)
    public void createBuyer() {
        assertNotNull(B1);
        System.out.println(B1.toString());
    }

    @Test
    @Order(2)
    public void testCreateBuyerWithAllAttributes() {
        assertNotNull(B2);
        System.out.println(B2.toString());
    }

    @Test
    @Order(3)
    public void testCreateBuyerThatFails() {
        //fail
        assertNotNull(B3);
        System.out.println(B3.toString());
    }
}