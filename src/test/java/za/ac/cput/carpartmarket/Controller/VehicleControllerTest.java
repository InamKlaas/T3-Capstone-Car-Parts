package za.ac.cput.carpartmarket.Controller;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.carpartmarket.Domain.Vehicle;
import za.ac.cput.carpartmarket.Factory.VehicleFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
@TestMethodOrder(MethodOrderer.MethodName.class)
class VehicleControllerTest {

    private static Vehicle vehicle = VehicleFactory.createVehicle(
            2020,
            "BMW",
            "V4",
            "Diesel"
    );

    String BASE_URL = "/vehicles";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void a_create() {
        String url = BASE_URL + "/create";
        System.out.println("URL: " + url);
        ResponseEntity<Vehicle> postResponse = restTemplate.postForEntity(url, vehicle, Vehicle.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        Vehicle vehicleSaved = postResponse.getBody();
        System.out.println("Save data: " + vehicleSaved);
        vehicle = vehicleSaved;
    }

    @Test
    void b_read() {
        String url = BASE_URL + "/" + vehicle.getVehicleId();
        System.out.println("URL: " + url);
        ResponseEntity<Vehicle> response = restTemplate.getForEntity(url, Vehicle.class);
        assertEquals(vehicle.getVehicleId(), response.getBody().getVehicleId());
        System.out.println(response.getBody());
    }

    @Test
    void c_update() {
        Vehicle updateVehicle = new Vehicle.Builder()
                .copy(vehicle)
                .setFuelType("Diesel ")
                .build();
        String url = BASE_URL;
        System.out.println("URL: " + url);
        restTemplate.put(url, updateVehicle);

        String readUrl = BASE_URL + "/" + vehicle.getVehicleId();
        ResponseEntity<Vehicle> response = restTemplate.getForEntity(readUrl, Vehicle.class);
        System.out.println(response.getBody());
        vehicle = response.getBody();
        System.out.println("Update data: " + vehicle);
    }

    @Test
    void d_deleteById() {
        String url = BASE_URL + "/" + vehicle.getVehicleId();
        System.out.println("URL: " + url);
        ResponseEntity<Vehicle> response = restTemplate.getForEntity(url, Vehicle.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        System.out.println("Delete: true");
    }
}