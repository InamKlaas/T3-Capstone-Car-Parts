package za.ac.cput.carpartmarket.Repository;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.carpartmarket.Domain.Admin;
import za.ac.cput.carpartmarket.Factory.AdminFactory;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class IAdminRepositoryTest {

    @Autowired
    private IAdminRepository adminRepository;

    private static Admin admin;

    @Test
    @Order(1)
    void a_create(){
        admin = AdminFactory.createAdmin("john.doe", "SUPER_ADMIN", "READ_WRITE");
        assertNotNull(admin);

        Admin saved = adminRepository.save(admin);
        assertNotNull(saved);
        assertEquals(admin.getAdminId(), saved.getAdminId());
        admin = saved;

        System.out.println(saved);
    }

    @Test
    @Order(2)
    void b_read(){
        assertNotNull(admin);

        Optional<Admin> found = adminRepository.findById(admin.getAdminId());
        assertTrue(found.isPresent());
        assertEquals(admin.getAdminId(), found.get().getAdminId());

        System.out.println(found.get());
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

        Admin saved = adminRepository.save(updated);
        assertNotNull(saved);
        assertEquals("MODERATOR", saved.getRole());
        assertEquals("READ_ONLY", saved.getPermissions());
        admin = saved;

        System.out.println(saved);
    }

    @Test
    @Order(4)
    void d_getAll(){
        List<Admin> admins = adminRepository.findAll();
        assertNotNull(admins);
        assertFalse(admins.isEmpty());

        admins.forEach(System.out::println);
    }

    @Test
    @Order(5)
    void e_delete(){
        assertNotNull(admin);

        adminRepository.deleteById(admin.getAdminId());
        Optional<Admin> found = adminRepository.findById(admin.getAdminId());
        assertFalse(found.isPresent());
    }
}