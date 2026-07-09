package za.ac.cput.carpartmarket.Repository;

import org.junit.jupiter.api.*;

import za.ac.cput.carpartmarket.Domain.Name;
import za.ac.cput.carpartmarket.Domain.User;
import za.ac.cput.carpartmarket.Factory.UserFactory;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserRepositoryTest {

    private static final UserRepository repository =
            UserRepository.getRepository();

    private static final Name name = new Name.Builder()
            .setFirstName("John")
            .setLastName("Smith")
            .build();

    private static final User user =
            UserFactory.createUser(
                    name,
                    "john@gmail.com",
                    "Password123",
                    "0812345678",
                    "2026-07-09"
            );

    @Test
    @Order(1)
    void create() {

        User created = repository.create(user);

        assertNotNull(created);

        System.out.println(created);
    }

    @Test
    @Order(2)
    void read() {

        User read = repository.read(user.getUserid());

        assertNotNull(read);

        System.out.println(read);
    }

    @Test
    @Order(3)
    void update() {

        User updated = new User.Builder()
                .copy(user)
                .setEmail("newemail@gmail.com")
                .build();

        User result = repository.update(updated);

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

        boolean deleted = repository.delete(user.getUserid());

        assertTrue(deleted);
    }
}