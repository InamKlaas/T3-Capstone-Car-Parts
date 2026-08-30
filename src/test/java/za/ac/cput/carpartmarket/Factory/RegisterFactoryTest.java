package za.ac.cput.carpartmarket.Factory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import za.ac.cput.carpartmarket.Domain.Buyer;
import za.ac.cput.carpartmarket.Domain.Register;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RegisterFactoryTest {

    private static Buyer buyer;
    private static Register r1;

    @BeforeAll
    public static void setUp(){
        buyer = BuyerFactory.createBuyer("hdh", NameFactory.createName("Vera", "Doja"), "car pad");
        r1 = RegisterFactory.createRegister("hdh", buyer, LocalDate.of(2026, 9, 1), "Active");
    }

    @Test
    @Order(1)
    public void createRegister(){
        assertNotNull(r1);
        System.out.println(r1.toString());
    }
}
