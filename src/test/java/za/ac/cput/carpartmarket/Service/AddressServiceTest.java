package za.ac.cput.carpartmarket.Service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.carpartmarket.Domain.Address;
import za.ac.cput.carpartmarket.Factory.AddressFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class AddressServiceTest {

    @Autowired
    private AddressService service;

//    private static Address address = AddressFactory.createAddress( "65", 8765421012345L, "Belhar", "Cape Town", "Western Cape", 5084, "South Africa");
    private Address address = AddressFactory.createAddress( "65", 8765421012345L, "Belhar", "Cape Town", "Western Cape", 5084, "South Africa");

    @Test
    void a_create() {
        Address created = service.create(address);
        assertNotNull(created);
        System.out.println("Created address:" + created);
    }

    @Test
    void b_read(){
        Address read = service.read(address.getStreetNumber());
        assertNotNull(read);
        System.out.println("Read address" + read);
    }


    @Test
    void d_update(){
        Address newAddress = new Address.Builder().copy(address).setSuburb("Wynberg").build();
        Address updated = service.update(newAddress);
        assertNotNull(updated);
        System.out.println("updated: " + updated);
    }

    @Test
    void e_getall(){
        System.out.println("Addresses: " + service.getall());
    }

    @Test
    @Disabled
    void d_delete() {
        boolean deleted = service.delete(address.getStreetNumber());
        assertTrue(deleted);
        System.out.println("Deleted: " + address.getStreetNumber());
    }
}