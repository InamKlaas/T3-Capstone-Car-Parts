package za.ac.cput.carpartmarket.Service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.carpartmarket.Domain.Name;
import za.ac.cput.carpartmarket.Domain.User;
import za.ac.cput.carpartmarket.Factory.UserFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceTest {

    @Autowired
    private IUserService userService;

    private static User user;

    @Test
    @Order(1)
    void a_create(){
        Name name = new Name.Builder()
                .setFirstName("John")
                .setLastName("Doe")
                .build();

        user = UserFactory.createUser(name, "john.doe@example.com",
                "Password123", "0821234567", "2026-07-28");
        assertNotNull(user);

        User created = userService.create(user);
        assertNotNull(created);
        assertEquals(user.getUserid(), created.getUserid());
        user = created;

        System.out.println(created);
    }

    @Test
    @Order(2)
    void b_read(){
        assertNotNull(user);

        User read = userService.read(user.getUserid());
        assertNotNull(read);
        assertEquals(user.getUserid(), read.getUserid());

        System.out.println(read);
    }

    @Test
    @Order(3)
    void c_update(){
        assertNotNull(user);

        Name updatedName = new Name.Builder()
                .setFirstName("Jane")
                .setLastName("Doe")
                .build();

        User updated = new User.Builder()
                .copy(user)
                .setName(updatedName)
                .setPhoneNumber("0839876543")
                .build();

        User saved = userService.update(updated);
        assertNotNull(saved);
        assertEquals("0839876543", saved.getPhoneNumber());
        user = saved;

        System.out.println(saved);
    }

    @Test
    @Order(4)
    void d_delete(){
        assertNotNull(user);

        boolean deleted = userService.delete(user.getUserid());
        assertTrue(deleted);

        User read = userService.read(user.getUserid());
        assertNull(read);
    }
}