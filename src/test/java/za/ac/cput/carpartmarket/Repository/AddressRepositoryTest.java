package za.ac.cput.carpartmarket.Repository;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import za.ac.cput.carpartmarket.Domain.Address;
import za.ac.cput.carpartmarket.Factory.AddressFactory;

import static org.junit.jupiter.api.Assertions.*;

class AddressRepositoryTest {

    private static Address A1 = AddressFactory.createAddress("65", 98765421012345L, "Belhar", "Cape Town", "Western Cape", 5084, "South Africa");
    private static Address A2 = AddressFactory.createAddress("65", 98765421012345L, "Belhar", "Cape Town", "Western Cape", 5084, "South Africa");
    private static Address A3 = AddressFactory.createAddress("65", 98765421012345L, "Belhar", "Cape Town", "Western Cape", 584, "South Africa");


    @Test
    @Order(1)
    public void createAddress() {
        assertNotNull(A1);
        System.out.println(A1.toString());
    }

    @Test
    @Order(2)
    public void testCreateAddressWithAllAttributes() {
        assertNotNull(A2);
        System.out.println(A2.toString());
    }
    @Test
    @Order(3)
    public void testCreateAddressThatFails(){
        //fail
        assertNotNull(A3);
        System.out.println(A3.toString());
    }

}