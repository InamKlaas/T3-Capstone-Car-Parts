package za.ac.cput.carpartmarket.Service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.carpartmarket.Domain.Seller;
import za.ac.cput.carpartmarket.Factory.NameFactory;
import za.ac.cput.carpartmarket.Factory.SellerFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SellerServiceTest {

    @Autowired
    private SellerService sellerService;

    private static final Seller seller = SellerFactory.createSeller(
            113L,
            NameFactory.createName("John", "Doe"),
            "Car Parts"
    );

    @Test
    void create() {
        Seller seller1 = sellerService.create(seller);
        assertNotNull(seller1);
        System.out.println(seller1);
    }

    @Test
    void read() {
        Seller seller1 = sellerService.read(seller.getUserid());
        assertNotNull(seller1);
        System.out.println(seller1);
    }

    @Test
    void update() {
        Seller updatedSeller = SellerFactory.createSeller(
                113L,
                NameFactory.createName("John", "Smith"),
                "Engine Parts"
        );

        Seller seller1 = sellerService.update(updatedSeller);
        assertNotNull(seller1);
        System.out.println(seller1);
    }

    @Test
    void delete() {
        sellerService.delete(seller.getUserid());
    }
}