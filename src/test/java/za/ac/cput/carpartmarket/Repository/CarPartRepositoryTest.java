package za.ac.cput.carpartmarket.Repository;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.carpartmarket.Domain.CarPart;
import za.ac.cput.carpartmarket.Factory.CarPartFactory;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CarPartRepositoryTest {

    private static CarPart C1 = CarPartFactory.createCarPart(73838L, "High performance brake pad", "Toyota Corolla", 8373.00, 10, "Toyota Corolla", 1001L, 2001L);
    private static CarPart C2 = CarPartFactory.createCarPart(73838L, "High performance brake pad", "Toyota Corolla", 8373.00, 10, "Toyota Corolla", 1001L, 2001L);
    private static CarPart C3 = CarPartFactory.createCarPart(73838L, "High performance brake pad", "Toyota Corolla", 8373.00, 10, "Toyota Corolla", 1001L, 2001L);

//    private static CarPart C2 = CarPartFactory.createCarPart(73839L, "Clutch Kit", "Heavy duty clutch kit", 1002L);
//    private static CarPart C3 = CarPartFactory.createCarPart(73840L, "", "Heavy duty clutch kit", 1002L);

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
        assertNull(C3);
        System.out.println(C3);
    }
}