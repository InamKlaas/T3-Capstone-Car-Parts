package za.ac.cput.carpartmarket.Service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.carpartmarket.Domain.Buyer;
import za.ac.cput.carpartmarket.Domain.Login;
import za.ac.cput.carpartmarket.Factory.BuyerFactory;
import za.ac.cput.carpartmarket.Factory.LoginFactory;
import za.ac.cput.carpartmarket.Factory.NameFactory;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LoginServiceTest {

    @Autowired
    private LoginService loginService;

    private static final Buyer buyer = BuyerFactory.createBuyer(
            "112L",
            NameFactory.createName("Lulo", "Kolisi"),
            "Brake Pads"
    );

    private static final Login login = LoginFactory.createLogin(
            "201L",
            buyer,
            "lulo@gmail.com",
            "password123",
            LocalDateTime.of(2026, 8, 22, 10, 30),
            "SUCCESS"
    );

    @Test
    void create() {

        Login login1 = loginService.create(login);

        assertNotNull(login1);
        System.out.println("Created: " + login1);
    }

    @Test
    void read() {

        loginService.create(login);

        Login login1 =
                loginService.read(login.getLoginId());

        assertNotNull(login1);
        System.out.println("Read: " + login1);
    }

    @Test
    void update() {

        loginService.create(login);

        Login updatedLogin = new Login.Builder()
                .copy(login)
                .setStatus("FAILED")
                .build();

        Login login1 = loginService.update(updatedLogin);

        assertNotNull(login1);
        assertEquals("FAILED", login1.getStatus());

        System.out.println("Updated: " + login1);
    }

    @Test
    void delete() {

        loginService.create(login);

        boolean deleted =
                loginService.delete(login.getLoginId());

        assertTrue(deleted);

        System.out.println("Deleted: " + deleted);
    }
}
