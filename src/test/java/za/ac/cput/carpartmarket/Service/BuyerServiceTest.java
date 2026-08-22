package za.ac.cput.carpartmarket.Service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.carpartmarket.Domain.Buyer;
import za.ac.cput.carpartmarket.Factory.BuyerFactory;
import za.ac.cput.carpartmarket.Factory.NameFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BuyerServiceTest {

    @Autowired
    private BuyerService buyerService;

    private static final Buyer buyer = BuyerFactory.createBuyer(
            "112",
            NameFactory.createName("Vera", "Doja"),
            "Car Parts"
    );

    @Test
    void create() {
        Buyer buyer1 = buyerService.create(buyer);
        assertNotNull(buyer1);
        System.out.println(buyer1);
    }

    @Test
    void read() {
        Buyer buyer1 = buyerService.read(buyer.getUserid());
        assertNotNull(buyer1);
        System.out.println(buyer1);
    }

    @Test
    void update() {
        Buyer updatedBuyer = BuyerFactory.createBuyer(
                "112L",
                NameFactory.createName("Vera", "Smith"),
                "Engine Parts"
        );

        Buyer buyer1 = buyerService.update(updatedBuyer);
        assertNotNull(buyer1);
        System.out.println(buyer1);
    }

    @Test
    void delete() {
        buyerService.delete(buyer.getUserid());
    }
}