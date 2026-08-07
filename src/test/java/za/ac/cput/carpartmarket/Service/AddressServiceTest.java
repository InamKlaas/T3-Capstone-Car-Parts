package za.ac.cput.carpartmarket.Service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.carpartmarket.Domain.Address;
import za.ac.cput.carpartmarket.Factory.AddressFactory;

//import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class AddressServiceTest {

    @Autowired
    private AddressService service;
    private static Address address = AddressFactory.createAddress("65", 98765421012345L, "Belhar", "Cape Town", "Western Cape", 5084, "South Africa");

    @Test
    void a_create() {
        Address created = service.create(address);
        assertNotNull(created);
        System.out.println(created);
    }

    @Test
    void b_read() {
        Address read = service.read(address.getStreetNumber());
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    void c_update() {
        Address newAddress = new Address.Builder().copy(address).setUserId(98765421012345L).build();
        Address updated = service.update(newAddress);
        assertNotNull(updated);
        System.out.println();
    }

    @Test
    void d_delete() {
        service.delete(address.getStreetNumber());
    }

    @Test
    void e_getall() {
        System.out.println(service.getall());
    }
}