package za.ac.cput.carpartmarket.Factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.carpartmarket.Domain.Admin;

import static org.junit.jupiter.api.Assertions.*;

class AdminFactoryTest {

    @Test
    void createAdmin_validData_returnsAdmin() {
        Admin admin = AdminFactory.createAdmin("101", "SUPER_ADMIN", "READ_WRITE");

        assertNotNull(admin);
        assertNotNull(admin.getUserid());
        assertEquals("101", admin.getUserid());
        assertEquals("SUPER_ADMIN", admin.getRole());
        assertEquals("READ_WRITE", admin.getPermissions());
    }

    @Test
    void createAdmin_nullUserid_returnsNull() {
        Admin admin = AdminFactory.createAdmin(null, "SUPER_ADMIN", "READ_WRITE");
        assertNull(admin);
    }

    @Test
    void createAdmin_nullRole_returnsNull() {
        Admin admin = AdminFactory.createAdmin("101", null, "READ_WRITE");
        assertNull(admin);
    }

    @Test
    void createAdmin_emptyRole_returnsNull() {
        Admin admin = AdminFactory.createAdmin("101", "   ", "READ_WRITE");
        assertNull(admin);
    }

    @Test
    void createAdmin_nullPermissions_returnsNull() {
        Admin admin = AdminFactory.createAdmin("101", "SUPER_ADMIN", null);
        assertNull(admin);
    }

    @Test
    void createAdmin_emptyPermissions_returnsNull() {
        Admin admin = AdminFactory.createAdmin("101", "SUPER_ADMIN", "");
        assertNull(admin);
    }
}