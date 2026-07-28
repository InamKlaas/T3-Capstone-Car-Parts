package za.ac.cput.carpartmarket.Factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.carpartmarket.Domain.Admin;

import static org.junit.jupiter.api.Assertions.*;

class AdminFactoryTest {

    @Test
    void createAdmin_validData_returnsAdmin(){
        Admin admin = AdminFactory.createAdmin("A001", "john.doe", "SUPER_ADMIN");

        assertNotNull(admin);
        assertEquals("A001", admin.getAdminId());
        assertEquals("john.doe", admin.getUser());
        assertEquals("SUPER_ADMIN", admin.getRole());
        assertEquals("READ_WRITE", admin.getPermissions());
    }

    @Test
    void createAdmin_nullAdminId_returnsNull(){
        Admin admin = AdminFactory.createAdmin(null, "john.doe", "SUPER_ADMIN");
        assertNull(admin);
    }

    @Test
    void createAdmin_emptyAdminId_returnsNull(){
        Admin admin = AdminFactory.createAdmin("  ", "john.doe", "SUPER_ADMIN");
        assertNull(admin);
    }

    @Test
    void createAdmin_nullUser_returnsNull(){
        Admin admin = AdminFactory.createAdmin("A001", null, "SUPER_ADMIN");
        assertNull(admin);
    }

    @Test
    void createAdmin_emptyUser_returnsNull(){
        Admin admin = AdminFactory.createAdmin("A001", "", "SUPER_ADMIN");
        assertNull(admin);
    }

    @Test
    void createAdmin_nullRole_returnsNull(){
        Admin admin = AdminFactory.createAdmin("A001", "john.doe", null);
        assertNull(admin);
    }

    @Test
    void createAdmin_emptyRole_returnsNull(){
        Admin admin = AdminFactory.createAdmin("A001", "john.doe", "   ");
        assertNull(admin);
    }

    @Test
    void createAdmin_nullPermissions_returnsNull(){
        Admin admin = AdminFactory.createAdmin("A001", "john.doe", "SUPER_ADMIN");
        assertNull(admin);
    }

    @Test
    void createAdmin_emptyPermissions_returnsNull(){
        Admin admin = AdminFactory.createAdmin("A001", "john.doe", "SUPER_ADMIN");
        assertNull(admin);
    }
}