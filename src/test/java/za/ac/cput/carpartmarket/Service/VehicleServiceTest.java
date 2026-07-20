package za.ac.cput.carpartmarket.Service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.carpartmarket.Domain.Vehicle;
import za.ac.cput.carpartmarket.Factory.VehicleFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class VehicleServiceTest {

    @Autowired
    private VehicleService vehicleService;

    private static Vehicle vehicle = VehicleFactory.createVehicle(201L,2020,"BMW","TwinPower-inline4","Diesel");


    @Test
    @Order(1)
    void create() {
        Vehicle vehicle1 = vehicleService.create(vehicle);
        assertNotNull(vehicle1);
        System.out.println(vehicle1);
    }

    @Test
    @Order(2)
    void read() {
        Vehicle vehicle1 = vehicleService.read(vehicle.getVehicleId());
        assertNotNull(vehicle1);
        System.out.println(vehicle1);
    }

    @Test
    @Order(3)
    void update() {
        Vehicle vehicle1 = vehicleService.read(vehicle.getVehicleId());
        assertNotNull(vehicle1);
        System.out.println(vehicle1);
    }

    @Test
    @Order(4)
    void delete() {
        vehicleService.delete(vehicle.getVehicleId());
    }
}