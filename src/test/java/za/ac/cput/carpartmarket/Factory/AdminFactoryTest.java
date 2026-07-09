package za.ac.cput.carpartmarket.Factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.carpartmarket.Domain.Admin;

import static org.junit.jupiter.api.Assertions.*;

class AdminFactoryTest {

    @Test
    void createAdmin() {

        Admin admin = AdminFactory.createAdmin(
                "John Smith",
                "Administrator",
                "Manage Users"
        );

        assertNotNull(admin);

        System.out.println(admin);
    }

    @Test
    void createAdminWithNullUser() {

        Admin admin = AdminFactory.createAdmin(
                "",
                "Administrator",
                "Manage Users"
        );

        assertNull(admin);
    }
}