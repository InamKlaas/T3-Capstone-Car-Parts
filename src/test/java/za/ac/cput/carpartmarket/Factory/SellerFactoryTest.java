package za.ac.cput.carpartmarket.Factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.carpartmarket.Domain.Seller;

import static org.junit.jupiter.api.Assertions.*;

class SellerFactoryTest {

    @Test
    void createSeller() {
        Seller seller = SellerFactory.createSeller(
                "113L",
                NameFactory.createName("John", "Doe"),
                "Car Parts"
        );

        assertNotNull(seller);

        System.out.println(seller);
    }

    @Test
    void createSellerWithNullUserid() {
        Seller seller = SellerFactory.createSeller(
                null,
                NameFactory.createName("John", "Doe"),
                "Car Parts"
        );

        assertNull(seller);

        System.out.println("Seller creation failed: userid is null");
    }

    @Test
    void createSellerWithNullSellerName() {
        Seller seller = SellerFactory.createSeller(
                "113L",
                null,
                "Car Parts"
        );

        assertNull(seller);

        System.out.println("Seller creation failed: Seller name is null");
    }

    @Test
    void createSellerWithNullSellingPart() {
        Seller seller = SellerFactory.createSeller(
                "113L",
                NameFactory.createName("John", "Doe"),
                null
        );

        assertNull(seller);

        System.out.println("Seller creation failed: sellingPart is null");
    }
}