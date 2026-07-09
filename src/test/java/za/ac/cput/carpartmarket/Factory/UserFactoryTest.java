package za.ac.cput.carpartmarket.Factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.carpartmarket.Domain.Name;
import za.ac.cput.carpartmarket.Domain.User;

import static org.junit.jupiter.api.Assertions.*;

class UserFactoryTest {

    @Test
    void createUser() {

        Name name = new Name.Builder()
                .setFirstName("John")
                .setLastName("Smith")
                .build();

        User user = UserFactory.createUser(
                name,
                "john@gmail.com",
                "Password123",
                "0812345678",
                "2026-07-09"
        );

        assertNotNull(user);

        System.out.println(user);
    }

    @Test
    void createUserWithNullEmail() {

        Name name = new Name.Builder()
                .setFirstName("John")
                .setLastName("Smith")
                .build();

        User user = UserFactory.createUser(
                name,

                "",
                "Password123",
                "0812345678",
                "2026-07-09"
        );

        assertNull(user);
    }
}