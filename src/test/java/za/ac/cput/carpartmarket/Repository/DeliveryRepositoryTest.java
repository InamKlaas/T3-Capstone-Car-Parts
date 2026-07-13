package za.ac.cput.carpartmarket.Repository;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DeliveryRepositoryTest {

    private static final DeliveryRepository repository =
            DeliveryRepository.getRepository();

    private static final Delivery delivery =
            DeliveryFactory.createDelivery(
                    "Courier Guy",
                    "TRK123456",
                    "2026-06-30",
                    "Pending"
            );

    @Test
    @Order(1)
    void create() {

        Delivery created = repository.create(delivery);

        assertNotNull(created);

        System.out.println(created);
    }

    @Test
    @Order(2)
    void read() {

        Delivery read = repository.read(delivery.getDeliveryId());

        assertNotNull(read);

        System.out.println(read);
    }

    @Test
    @Order(3)
    void update() {

        Delivery updated = new Delivery.Builder()
                .copy(delivery)
                .setDeliveryStatus("Delivered")
                .build();

        Delivery result = repository.update(updated);

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

        boolean deleted = repository.delete(delivery.getDeliveryId());

        assertTrue(deleted);
    }
}

