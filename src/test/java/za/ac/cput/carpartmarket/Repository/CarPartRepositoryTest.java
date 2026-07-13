package za.ac.cput.carpartmarket.Repository;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CarPartRepositoryTest {

    private static final CarPartRepository repository =
            CarPartRepository.getRepository();

    private static final CarPart carPart =
            CarPartFactory.createCarPart(
                    "Brake Pad",
                    "High performance brake pad",
                    "Toyota Corolla",
                    1001L
            );

    @Test
    @Order(1)
    void create() {

        CarPart created = repository.create(carPart);

        assertNotNull(created);

        System.out.println(created);
    }

    @Test
    @Order(2)
    void read() {

        CarPart read = repository.read(carPart.getCarPartId());

        assertNotNull(read);

        System.out.println(read);
    }

    @Test
    @Order(3)
    void update() {

        CarPart updated = new CarPart.Builder()
                .copy(carPart)
                .setPartName("Clutch Kit")
                .build();

        CarPart result = repository.update(updated);

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

        boolean deleted = repository.delete(carPart.getCarPartId());

        assertTrue(deleted);
    }
}