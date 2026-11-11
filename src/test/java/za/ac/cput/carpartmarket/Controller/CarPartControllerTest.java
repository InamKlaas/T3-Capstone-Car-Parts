package za.ac.cput.carpartmarket.Controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import za.ac.cput.carpartmarket.Domain.CarPart;
import za.ac.cput.carpartmarket.Domain.Name;
import za.ac.cput.carpartmarket.Domain.Seller;
import za.ac.cput.carpartmarket.Factory.CarPartFactory;
import za.ac.cput.carpartmarket.Repository.IUserRepository;
import za.ac.cput.carpartmarket.Service.JwtService;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
@TestMethodOrder(MethodOrderer.MethodName.class)
class CarPartControllerTest {

    private static final String TEST_SELLER_EMAIL = "test.seller@automarket.test";

    private static CarPart carPart = CarPartFactory.createCarPart(
            "1001L",
            "Brake Pad",
            "High performance brake pad",
            350.00,
            10,
            "Toyota Corolla",
            2L,
            "3L",
            "Approved",
            "IMG",
            "Used"
    );

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUserRepository userRepository;

    private String baseUrl() {
        return restTemplate.getRootUri() + "/carparts";
    }

    // Make sure the test seller exists, then return a valid seller token
    private String sellerToken() {
        if (userRepository.findByEmail(TEST_SELLER_EMAIL).isEmpty()) {
            Seller seller = new Seller.Builder()
                    .setUserid(UUID.randomUUID().toString())
                    .setSellerName(new Name.Builder().setFirstName("Test").setLastName("Seller").build())
                    .setEmail(TEST_SELLER_EMAIL)
                    .setPassword("not-used-in-tests")
                    .setSellingPart("Various")
                    .build();
            userRepository.save(seller);
        }
        return jwtService.generateToken(TEST_SELLER_EMAIL, "seller");
    }

    private <T> HttpEntity<T> withToken(T body, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (token != null) headers.setBearerAuth(token);
        return new HttpEntity<>(body, headers);
    }

    @Test
    void a_create() {
        String url = baseUrl() + "/create";
        ResponseEntity<CarPart> postResponse = restTemplate.exchange(
                url, HttpMethod.POST, withToken(carPart, sellerToken()), CarPart.class);

        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        assertNotNull(postResponse.getBody());
        assertEquals("PENDING", postResponse.getBody().getStatus());
        carPart = postResponse.getBody();
        System.out.println("Save data: " + carPart);
    }

    @Test
    void b_read() {
        String url = baseUrl() + "/read/" + carPart.getCarPartId();
        ResponseEntity<CarPart> response = restTemplate.getForEntity(url, CarPart.class);
        assertEquals(carPart.getCarPartId(), response.getBody().getCarPartId());
        System.out.println(response.getBody());
    }

    @Test
    void c_update() {
        CarPart updateCarPart = new CarPart.Builder()
                .copy(carPart)
                .setPartName("Clutch Kits")
                .build();
        String url = baseUrl() + "/update";
        ResponseEntity<CarPart> response = restTemplate.exchange(
                url, HttpMethod.PUT, withToken(updateCarPart, sellerToken()), CarPart.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Clutch Kits", response.getBody().getPartName());
        carPart = response.getBody();
        System.out.println("Update data: " + carPart);
    }

    @Test
    @Disabled
    void d_deleteById() {
        String url = baseUrl() + "/delete/" + carPart.getCarPartId();
        ResponseEntity<String> response = restTemplate.exchange(
                url, HttpMethod.DELETE, withToken(null, sellerToken()), String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        System.out.println("Delete: true");
    }

    // ---------- Security tests ----------

    @Test
    void e_buyerCannotApprove() {
        String buyerToken = jwtService.generateToken("someone.buyer@automarket.test", "buyer");
        String url = baseUrl() + "/" + carPart.getCarPartId() + "/approve";
        ResponseEntity<String> response = restTemplate.exchange(
                url, HttpMethod.PUT, withToken(null, buyerToken), String.class);
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    void f_createWithoutTokenIsRejected() {
        String url = baseUrl() + "/create";
        ResponseEntity<String> response = restTemplate.exchange(
                url, HttpMethod.POST, withToken(carPart, null), String.class);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }
}