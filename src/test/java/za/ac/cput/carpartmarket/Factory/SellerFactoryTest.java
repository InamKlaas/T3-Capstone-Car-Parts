package za.ac.cput.carpartmarket.Factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.carpartmarket.Domain.Seller;

import static org.junit.jupiter.api.Assertions.*;

class SellerFactoryTest {

    @Test
    void createSeller() {
        Seller seller = SellerFactory.createSeller(
                113L,
                NameFactory.createName("John", "Doe"),
                UserFactory.createUser(
                        NameFactory.createName("John", "Doe"),
                        "john@cput.ac.za",
                        "password123",
                        "084567891",
                        "15-01-2021"
                ),
                "ADMIN,SELLER"
        );

        assertNotNull(seller);

        System.out.println(seller);
    }

    @Test
    void createSellerWithNullSellerid() {
        Seller seller = SellerFactory.createSeller(
                null,
                NameFactory.createName("John", "Doe"),
                UserFactory.createUser(
                        NameFactory.createName("John", "Doe"),
                        "john@cput.ac.za",
                        "password123",
                        "084567891",
                        "15-01-2021"
                ),
                "ADMIN,SELLER"
        );

        assertNull(seller);

        System.out.println("Seller creation failed: Sellerid is null");
    }

    @Test
    void createSellerWithNullSellerName() {
        Seller seller = SellerFactory.createSeller(
                113L,
                null,
                UserFactory.createUser(
                        NameFactory.createName("John", "Doe"),
                        "john@cput.ac.za",
                        "password123",
                        "084567891",
                        "15-01-2021"
                ),
                "ADMIN,SELLER"
        );

        assertNull(seller);

        System.out.println("Seller creation failed: Seller name is null");
    }

    @Test
    void createSellerWithNullUser() {
        Seller seller = SellerFactory.createSeller(
                113L,
                NameFactory.createName("John", "Doe"),
                null,
                "ADMIN,SELLER"
        );

        assertNull(seller);

        System.out.println("Seller creation failed: User is null");
    }

    @Test
    void createSellerWithNullPermissions() {
        Seller seller = SellerFactory.createSeller(
                113L,
                NameFactory.createName("John", "Doe"),
                UserFactory.createUser(
                        NameFactory.createName("John", "Doe"),
                        "john@cput.ac.za",
                        "password123",
                        "084567891",
                        "15-01-2021"
                ),
                null
        );

        assertNull(seller);

        System.out.println("Seller creation failed: Permissions is null");
    }
}