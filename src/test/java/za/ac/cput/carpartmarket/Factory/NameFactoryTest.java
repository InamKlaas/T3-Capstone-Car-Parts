package za.ac.cput.carpartmarket.Factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.carpartmarket.Domain.Name;

import static org.junit.jupiter.api.Assertions.*;

class NameFactoryTest {

    @Test
    void createName() {

        Name name = NameFactory.createName(
                "John",
                "Smith"
        );

        assertNotNull(name);

        System.out.println(name);
    }

    @Test
    void createNameWithEmptyFirstName() {

        Name name = NameFactory.createName(
                "",
                "Smith"
        );

        assertNull(name);
    }

    @Test
    void createNameWithEmptyLastName() {

        Name name = NameFactory.createName(
                "John",
                ""
        );

        assertNull(name);
    }
}