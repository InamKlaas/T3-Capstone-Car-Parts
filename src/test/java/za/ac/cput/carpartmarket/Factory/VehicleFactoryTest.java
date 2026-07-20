package za.ac.cput.carpartmarket.Factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.carpartmarket.Domain.Vehicle;

import static org.junit.jupiter.api.Assertions.*;

class VehicleFactoryTest {

    @Test
    void createVehicle() {
        Vehicle vehicle = VehicleFactory.createVehicle(201L,2020,"BMW","TwinPower-inline4","Diesel");
        assertNotNull(vehicle);
        System.out.println(vehicle);
    }

    @Test
    void createVehicleWithInvalidYear(){
        Vehicle vehicle = VehicleFactory.createVehicle(223L,0,"BMW","TwinPower-inline4","Diesel");
        assertNotNull(vehicle);
        System.out.println(vehicle);
    }
    @Test
    void createVehicleWithNullModel(){
        Vehicle vehicle = VehicleFactory.createVehicle(234L, 2020,null,"TwinPower-inline4","Diesel");
        assertNotNull(vehicle);
        System.out.println(vehicle);
    }
}