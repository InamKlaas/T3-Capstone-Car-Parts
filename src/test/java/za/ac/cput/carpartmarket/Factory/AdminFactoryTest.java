package za.ac.cput.carpartmarket.Factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.carpartmarket.Domain.Admin;

import static org.junit.jupiter.api.Assertions.*;

class AdminFactoryTest {

    @Test
    void createAdmin_validData_returnsAdmin(){
        Admin admin = AdminFactory.createAdmin("john.doe", "SUPER_ADMIN", "READ_WRITE");

        assertNotNull(admin);
        assertNotNull(admin.getAdminId());
        assertEquals("john.doe", admin.getUser());
        assertEquals("SUPER_ADMIN", admin.getRole());
        assertEquals("READ_WRITE", admin.getPermissions());
    }

    @Test
    void createAdmin_nullUser_returnsNull(){
        Admin admin = AdminFactory.createAdmin(null, "SUPER_ADMIN", "READ_WRITE");
        assertNull(admin);
    }

    @Test
    void createAdmin_emptyUser_returnsNull(){
        Admin admin = AdminFactory.createAdmin("  ", "SUPER_ADMIN", "READ_WRITE");
        assertNull(admin);
    }

    @Test
    void createAdmin_nullRole_returnsNull(){
        Admin admin = AdminFactory.createAdmin("john.doe", null, "READ_WRITE");
        assertNull(admin);
    }

    @Test
    void createAdmin_emptyRole_returnsNull(){
        Admin admin = AdminFactory.createAdmin("john.doe", "   ", "READ_WRITE");
        assertNull(admin);
    }

    @Test
    void createAdmin_nullPermissions_returnsNull(){
        Admin admin = AdminFactory.createAdmin("john.doe", "SUPER_ADMIN", null);
        assertNull(admin);
    }

    @Test
    void createAdmin_emptyPermissions_returnsNull(){
        Admin admin = AdminFactory.createAdmin("john.doe", "SUPER_ADMIN", "");
        assertNull(admin);
    }
}