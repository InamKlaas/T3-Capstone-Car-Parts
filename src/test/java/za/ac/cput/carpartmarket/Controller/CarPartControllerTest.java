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
import za.ac.cput.carpartmarket.Domain.CarPart;
import za.ac.cput.carpartmarket.Factory.CarPartFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
@TestMethodOrder(MethodOrderer.MethodName.class)
class CarPartControllerTest {

    private static CarPart carPart = CarPartFactory.createCarPart(
            "1001L",
            "Brake Pad",
            "High performance brake pad",
            350.00,
            10,
            "Toyota Corolla",
            2L,
            "3L"
    );

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl() {
        return restTemplate.getRootUri() + "/carparts";
    }

    @Test
    void a_create() {
        String url = baseUrl() + "/create";
        System.out.println("URL: " + url);
        ResponseEntity<CarPart> postResponse = restTemplate.postForEntity(url, carPart, CarPart.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        CarPart carPartSaved = postResponse.getBody();
        System.out.println("Save data: " + carPartSaved);
        carPart = carPartSaved;
    }

    @Test
    void b_read() {
        String url = baseUrl() + "/read/" + carPart.getCarPartId();
        System.out.println("URL: " + url);
        ResponseEntity<CarPart> response = restTemplate.getForEntity(url, CarPart.class);
        assertEquals(carPart.getCarPartId(), response.getBody().getCarPartId());
        System.out.println(response.getBody());
    }

    @Test
    void c_update() {
        CarPart updateCarPart = new CarPart.Builder()
                .copy(carPart)
                .setPartName("Clutch Kit")
                .build();
        String url = baseUrl() + "/update";
        System.out.println("URL: " + url);
        restTemplate.put(url, updateCarPart);

        String readUrl = baseUrl() + "/read/" + carPart.getCarPartId();
        ResponseEntity<CarPart> response = restTemplate.getForEntity(readUrl, CarPart.class);
        System.out.println(response.getBody());
        carPart = response.getBody();
        System.out.println("Update data: " + carPart);
    }

    @Test
    void d_deleteById() {
        String url = baseUrl() + "/delete/" + carPart.getCarPartId();
        System.out.println("URL: " + url);
        restTemplate.delete(url);
        System.out.println("Delete: true");
    }
}