package za.ac.cput.carpartmarket.Service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import za.ac.cput.carpartmarket.Domain.CarPart;
import za.ac.cput.carpartmarket.Factory.CarPartFactory;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CarPartServiceTest {

    @Autowired
    private CarPartService service;
    private static CarPart carPart1 = CarPartFactory.createCarPart(73637372L, "side Mirror", "white Toyota Corolla", 150.00, 10, "Toyota Corolla", 1001L, 2001L);


    @Test
    void create() {
        CarPart created = service.create(carPart1);
        assertNotNull(created);
        System.out.println(created);
    }

    @Test
    void read() {
        CarPart read = service.read(carPart1.getCarPartId());
        assertNotNull(carPart1);
        System.out.println(read);
    }

    @Test
    void update() {
        CarPart update = service.read(carPart1.getCarPartId());
        assertNotNull(carPart1);
        System.out.println(carPart1);
    }

    @Test
    void delete() {
        service.delete(carPart1.getCarPartId());
    }
}