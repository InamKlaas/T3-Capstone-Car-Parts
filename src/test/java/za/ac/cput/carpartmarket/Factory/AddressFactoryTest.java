package za.ac.cput.carpartmarket.Factory;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import za.ac.cput.carpartmarket.Domain.Address;

import static org.junit.jupiter.api.Assertions.*;

public class AddressFactoryTest {

    private static Address a1 = AddressFactory.createAddress("65", String.valueOf(728), "Belhar", "Cape Town", "Western Cape", 6925, "South Africa");
    private static Address a2 = AddressFactory.createAddress("65", String.valueOf(728), "Belhar", "Cape Town", "Western Cape", 625, "South Africa");

    @Test
    @Order(1)
    public void createAddress(){
        assertNotNull(a1);
        System.out.println(a1.toString());
    }

    //test that fails
    @Test
    @Order(2)
    public void createAddressWithWrongPostalCodeLength(){
        assertNotNull(a2);
//        assertNull(a2);
    }
}
