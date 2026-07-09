package za.ac.cput.carpartmarket.Repository;

import org.junit.jupiter.api.*;

import za.ac.cput.carpartmarket.Domain.Admin;
import za.ac.cput.carpartmarket.Factory.AdminFactory;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AdminRepositoryTest {

    private static final AdminRepository repository =
            AdminRepository.getRepository();

    private static final Admin admin =
            AdminFactory.createAdmin(
                    "John Smith",
                    "Administrator",
                    "Manage Users"
            );

    @Test
    @Order(1)
    void create() {

        Admin created = repository.create(admin);

        assertNotNull(created);

        System.out.println(created);
    }

    @Test
    @Order(2)
    void read() {

        Admin read = repository.read(admin.getAdminId());

        assertNotNull(read);

        System.out.println(read);
    }

    @Test
    @Order(3)
    void update() {

        Admin updated = new Admin.Builder()
                .copy(admin)
                .setRole("Super Administrator")
                .build();

        Admin result = repository.update(updated);

        assertNotNull(result);

        System.out.println(result);
    }

    @Test
    @Order(4)
    void getAll() {

        System.out.println(repository.getAll());

        assertFalse(repository.getAll().isEmpty());
    }

    @Test
    @Order(5)
    void delete() {

        boolean deleted = repository.delete(admin.getAdminId());

        assertTrue(deleted);
    }
}