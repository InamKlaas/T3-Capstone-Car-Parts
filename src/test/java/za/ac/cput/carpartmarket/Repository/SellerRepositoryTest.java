package za.ac.cput.carpartmarket.Repository;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import za.ac.cput.carpartmarket.Domain.Seller;
import za.ac.cput.carpartmarket.Factory.NameFactory;
import za.ac.cput.carpartmarket.Factory.SellerFactory;
import za.ac.cput.carpartmarket.Factory.UserFactory;

import static org.junit.jupiter.api.Assertions.*;

class SellerRepositoryTest {

    private static Seller S1 = SellerFactory.createSeller(
            113L,
            NameFactory.createName("Mike", "Johnson"),
            UserFactory.createUser(
                    104L,
                    NameFactory.createName("Mike", "Johnson"),
                    "mike@cput.ac.za",
                    "mike123",
                    082345678,
                    "20-05-2021"
            ),
            "ADMIN,SELLER"
    );

    private static Seller S2 = SellerFactory.createSeller(
            114L,
            NameFactory.createName("Lisa", "Brown"),
            UserFactory.createUser(
                    105L,
                    NameFactory.createName("Lisa", "Brown"),
                    "lisa@cput.ac.za",
                    "lisa456",
                    083456789,
                    "12-07-2022"
            ),
            "SELLER"
    );

    private static Seller S3 = SellerFactory.createSeller(
            115L,
            NameFactory.createName("David", "Wilson"),
            UserFactory.createUser(
                    106L,
                    NameFactory.createName("David", "Wilson"),
                    "david@cput.ac.za",
                    "david789",
                    084567890,
                    "30-11-2023"
            ),
            "ADMIN"
    );

    @Test
    @Order(1)
    public void createSeller() {
        assertNotNull(S1);
        System.out.println(S1.toString());
    }

    @Test
    @Order(2)
    public void testCreateSellerWithAllAttributes() {
        assertNotNull(S2);
        System.out.println(S2.toString());
    }

    @Test
    @Order(3)
    public void testCreateSellerThatFails() {
        //fail
        assertNotNull(S3);
        System.out.println(S3.toString());
    }
}