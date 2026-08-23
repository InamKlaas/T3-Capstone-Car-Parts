package za.ac.cput.carpartmarket.Factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.carpartmarket.Domain.Buyer;
import za.ac.cput.carpartmarket.Domain.Login;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class LoginFactoryTest {

    private static final Buyer buyer = BuyerFactory.createBuyer(
            "112L",
            NameFactory.createName("Lulo", "Kolisi"),
            "Brake Pads"
    );

    @Test
    void createLogin() {

        Login login = LoginFactory.createLogin(
                201L,
                buyer,
                "lulo@gmail.com",
                "password123",
                LocalDateTime.of(2026, 8, 22, 10, 30),
                "SUCCESS"
        );

        assertNotNull(login);
        System.out.println(login);
    }

    @Test
    void createLoginWithNullLoginId() {

        Login login = LoginFactory.createLogin(
                null,
                buyer,
                "lulo@gmail.com",
                "password123",
                LocalDateTime.of(2026, 8, 22, 10, 30),
                "SUCCESS"
        );

        assertNull(login);
        System.out.println("Login creation failed: loginId is null");
    }

    @Test
    void createLoginWithNullUser() {

        Login login = LoginFactory.createLogin(
                201L,
                null,
                "lulo@gmail.com",
                "password123",
                LocalDateTime.of(2026, 8, 22, 10, 30),
                "SUCCESS"
        );

        assertNull(login);
        System.out.println("Login creation failed: user is null");
    }

    @Test
    void createLoginWithNullEmail() {

        Login login = LoginFactory.createLogin(
                201L,
                buyer,
                null,
                "password123",
                LocalDateTime.of(2026, 8, 22, 10, 30),
                "SUCCESS"
        );

        assertNull(login);
        System.out.println("Login creation failed: email is null");
    }

    @Test
    void createLoginWithNullPassword() {

        Login login = LoginFactory.createLogin(
                201L,
                buyer,
                "lulo@gmail.com",
                null,
                LocalDateTime.of(2026, 8, 22, 10, 30),
                "SUCCESS"
        );

        assertNull(login);
        System.out.println("Login creation failed: password is null");
    }

    @Test
    void createLoginWithNullLoginDate() {

        Login login = LoginFactory.createLogin(
                201L,
                buyer,
                "lulo@gmail.com",
                "password123",
                null,
                "SUCCESS"
        );

        assertNull(login);
        System.out.println("Login creation failed: loginDate is null");
    }

    @Test
    void createLoginWithNullStatus() {

        Login login = LoginFactory.createLogin(
                201L,
                buyer,
                "lulo@gmail.com",
                "password123",
                LocalDateTime.of(2026, 8, 22, 10, 30),
                null
        );

        assertNull(login);
        System.out.println("Login creation failed: status is null");
    }
}