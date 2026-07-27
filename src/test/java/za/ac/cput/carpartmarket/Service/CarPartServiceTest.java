package za.ac.cput.carpartmarket.Service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.carpartmarket.Domain.CarPart;
import za.ac.cput.carpartmarket.Factory.CarPartFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CarPartServiceTest {

    @Autowired
    private CarPartService carPartService;

    private static CarPart carPart = CarPartFactory.createCarPart(
            "Brake Pad",
            "High performance brake pad",
            "Toyota Corolla",
            1001L
    );

    @Test
    void create() {
        CarPart carPart1 = carPartService.create(carPart);
        assertNotNull(carPart1);
        System.out.println(carPart1);
    }

    @Test
    void read() {
        CarPart carPart1 = carPartService.read(carPart.getCarPartId());
        assertNotNull(carPart1);
        System.out.println(carPart1);
    }

    @Test
    void update() {
        CarPart carPart1 = carPartService.read(carPart.getCarPartId());
        assertNotNull(carPart1);
        System.out.println(carPart1);
    }

    @Test
    void delete() {
        carPartService.delete(carPart.getCarPartId());
    }
}