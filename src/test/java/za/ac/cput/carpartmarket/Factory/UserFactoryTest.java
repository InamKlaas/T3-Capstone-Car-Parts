package za.ac.cput.carpartmarket.Factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.carpartmarket.Domain.Name;
import za.ac.cput.carpartmarket.Domain.User;

import static org.junit.jupiter.api.Assertions.*;

class UserFactoryTest {

    private final Name validName = new Name.Builder()
            .setFirstName("John")
            .setLastName("Doe")
            .build();

    @Test
    void createUser_validData_returnsUser(){
        User user = UserFactory.createUser(validName, "john.doe@example.com",
                "Password123", "0821234567", "2026-07-28");

        assertNotNull(user);
        assertNotNull(user.getUserid());
        assertEquals(validName, user.getName());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("Password123", user.getPassword());
        assertEquals("0821234567", user.getPhoneNumber());
        assertEquals("2026-07-28", user.getCreatedAt());
    }

    @Test
    void createUser_nullPhoneNumber_returnsUser(){
        // phoneNumber is not validated by the factory, so a null value should still succeed
        User user = UserFactory.createUser(validName, "john.doe@example.com",
                "Password123", null, "2026-07-28");

        assertNotNull(user);
        assertNull(user.getPhoneNumber());
    }

    @Test
    void createUser_nullName_returnsNull(){
        User user = UserFactory.createUser(null, "john.doe@example.com",
                "Password123", "0821234567", "2026-07-28");
        assertNull(user);
    }

    @Test
    void createUser_nullEmail_returnsNull(){
        User user = UserFactory.createUser(validName, null,
                "Password123", "0821234567", "2026-07-28");
        assertNull(user);
    }

    @Test
    void createUser_emptyEmail_returnsNull(){
        User user = UserFactory.createUser(validName, "   ",
                "Password123", "0821234567", "2026-07-28");
        assertNull(user);
    }

    @Test
    void createUser_nullPassword_returnsNull(){
        User user = UserFactory.createUser(validName, "john.doe@example.com",
                null, "0821234567", "2026-07-28");
        assertNull(user);
    }

    @Test
    void createUser_emptyPassword_returnsNull(){
        User user = UserFactory.createUser(validName, "john.doe@example.com",
                "  ", "0821234567", "2026-07-28");
        assertNull(user);
    }

    @Test
    void createUser_nullCreatedAt_returnsNull(){
        User user = UserFactory.createUser(validName, "john.doe@example.com",
                "Password123", "0821234567", null);
        assertNull(user);
    }

    @Test
    void createUser_emptyCreatedAt_returnsNull(){
        User user = UserFactory.createUser(validName, "john.doe@example.com",
                "Password123", "0821234567", "");
        assertNull(user);
    }
}