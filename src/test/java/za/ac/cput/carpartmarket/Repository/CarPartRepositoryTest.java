package za.ac.cput.carpartmarket.Repository;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import za.ac.cput.carpartmarket.Domain.CarPart;
import za.ac.cput.carpartmarket.Factory.CarPartFactory;

import static org.junit.jupiter.api.Assertions.*;

class CarPartRepositoryTest {

    private static CarPart C1 = CarPartFactory.createCarPart("Brake Pad", "High performance brake pad", "Toyota Corolla", 1001L);
    private static CarPart C2 = CarPartFactory.createCarPart("Clutch Kit", "Heavy duty clutch kit", "Honda Civic", 1002L);
    private static CarPart C3 = CarPartFactory.createCarPart("", "Heavy duty clutch kit", "Honda Civic", 1002L);

    @Test
    @Order(1)
    public void createCarPart() {
        assertNotNull(C1);
        System.out.println(C1.toString());
    }

    @Test
    @Order(2)
    public void testCreateCarPartWithAllAttributes() {
        assertNotNull(C2);
        System.out.println(C2.toString());
    }

    @Test
    @Order(3)
    public void testCreateCarPartThatFails() {
        assertNotNull(C3);
        System.out.println(C3.toString());
    }
}
