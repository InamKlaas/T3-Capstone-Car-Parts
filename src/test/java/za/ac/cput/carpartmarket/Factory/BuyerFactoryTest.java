package za.ac.cput.carpartmarket.Factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.carpartmarket.Domain.Buyer;

import static org.junit.jupiter.api.Assertions.*;

class BuyerFactoryTest {

    @Test
    void createBuyer() {
        Buyer buyer = BuyerFactory.createBuyer(
                112L,
                NameFactory.createName("Vera", "Doja"),
                "Car Parts"
        );

        assertNotNull(buyer);

        System.out.println(buyer);
    }

    @Test
    void createBuyerWithNullBuyerid() {
        Buyer buyer = BuyerFactory.createBuyer(
                null,
                NameFactory.createName("Vera", "Doja"),
                "Car Parts"
        );

        assertNull(buyer);

        System.out.println("Buyer creation failed: userid is null");
    }

    @Test
    void createBuyerWithNullBuyerName() {
        Buyer buyer = BuyerFactory.createBuyer(
                112L,
                null,
                "Car Parts"
        );

        assertNull(buyer);

        System.out.println("Buyer creation failed: Buyer name is null");
    }

    @Test
    void createBuyerWithNullUser() {
        Buyer buyer = BuyerFactory.createBuyer(
                112L,
                NameFactory.createName("Vera", "Doja"),
                null
        );

        assertNull(buyer);

        System.out.println("Buyer creation failed: User is null");
    }
}