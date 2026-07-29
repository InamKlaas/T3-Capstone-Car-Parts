package za.ac.cput.carpartmarket.Service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.carpartmarket.Domain.Admin;
import za.ac.cput.carpartmarket.Factory.AdminFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AdminServiceTest {

    @Autowired
    private IAdminService adminService;

    private static Admin admin;

    @Test
    @Order(1)
    void a_create(){
        admin = AdminFactory.createAdmin("john.doe", "SUPER_ADMIN", "READ_WRITE");
        assertNotNull(admin);

        Admin created = adminService.create(admin);
        assertNotNull(created);
        assertEquals(admin.getAdminId(), created.getAdminId());
        admin = created;

        System.out.println(created);
    }

    @Test
    @Order(2)
    void b_read(){
        assertNotNull(admin);

        Admin read = adminService.read(admin.getAdminId());
        assertNotNull(read);
        assertEquals(admin.getAdminId(), read.getAdminId());

        System.out.println(read);
    }

    @Test
    @Order(3)
    void c_update(){
        assertNotNull(admin);

        Admin updated = new Admin.Builder()
                .copy(admin)
                .setRole("MODERATOR")
                .setPermissions("READ_ONLY")
                .build();

        Admin saved = adminService.update(updated);
        assertNotNull(saved);
        assertEquals("MODERATOR", saved.getRole());
        assertEquals("READ_ONLY", saved.getPermissions());
        admin = saved;

        System.out.println(saved);
    }

    @Test
    @Order(4)
    void d_getAll(){
        List<Admin> admins = adminService.getAll();
        assertNotNull(admins);
        assertFalse(admins.isEmpty());

        admins.forEach(System.out::println);
    }

    @Test
    @Order(5)
    void e_delete(){
        assertNotNull(admin);

        boolean deleted = adminService.delete(admin.getAdminId());
        assertTrue(deleted);

        Admin read = adminService.read(admin.getAdminId());
        assertNull(read);
    }
}