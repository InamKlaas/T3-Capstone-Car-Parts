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

    private static Address address = AddressFactory.createAddress(
            "65",
            98765421012345L,
            "Belhar",
            "Cape Town",
            "Western Cape",
            5084,
            "South Africa"
    );

    @Test
    void a_create() {
        Address created = service.create(address);
        assertNotNull(created);
        System.out.println("Created: " + created);
    }

    @Test
    void b_read() {
        Address read = service.read(address.getStreetNumber());
        assertNotNull(read);
        System.out.println("Read: " + read);
    }

    @Test
    void c_update() {
        // Change the suburb to verify that update works properly
        Address newAddress = new Address.Builder()
                .copy(address)
                .setSuburb("Belhar Ext 1")
                .build();

        Address updated = service.update(newAddress);
        assertNotNull(updated);
        assertEquals("Belhar Ext 1", updated.getSuburb());
        System.out.println("Updated: " + updated);
    }

    @Test
    void e_getall() {
        System.out.println("All Addresses: " + service.getall());
    }

    @Test
    @Disabled
    void d_delete() {
        boolean deleted = service.delete(address.getStreetNumber());
        assertTrue(deleted);
        System.out.println("Deleted: " + address.getStreetNumber());
    }
}