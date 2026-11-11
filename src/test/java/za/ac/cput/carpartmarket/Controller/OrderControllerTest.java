package za.ac.cput.carpartmarket.Controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import za.ac.cput.carpartmarket.DTO.OrderResponseDTO;
import za.ac.cput.carpartmarket.DTO.PlaceOrderRequestDTO;
import za.ac.cput.carpartmarket.Domain.Buyer;
import za.ac.cput.carpartmarket.Domain.CarPart;
import za.ac.cput.carpartmarket.Domain.Name;
import za.ac.cput.carpartmarket.Domain.Seller;
import za.ac.cput.carpartmarket.Repository.CarPartRepository;
import za.ac.cput.carpartmarket.Repository.IUserRepository;
import za.ac.cput.carpartmarket.Repository.OrderRepository;
import za.ac.cput.carpartmarket.Service.JwtService;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
@TestMethodOrder(MethodOrderer.MethodName.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrderControllerTest {

    private static final String BUYER_EMAIL = "test.buyer@automarket.test";
    private static final String SELLER_EMAIL = "test.seller@automarket.test";

    @Autowired private TestRestTemplate restTemplate;
    @Autowired private JwtService jwtService;
    @Autowired private IUserRepository userRepository;
    @Autowired private CarPartRepository carPartRepository;
    @Autowired private OrderRepository orderRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    private String buyerToken;
    private String sellerToken;
    private String partId;
    private String orderReference;

    private String baseUrl() {
        return restTemplate.getRootUri() + "/orders";
    }

    private <T> HttpEntity<T> withToken(T body, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (token != null) headers.setBearerAuth(token);
        return new HttpEntity<>(body, headers);
    }

    @BeforeAll
    void setUp() {
        // Test buyer (hashed password)
        if (userRepository.findByEmail(BUYER_EMAIL).isEmpty()) {
            userRepository.save(new Buyer.Builder()
                    .setUserid(UUID.randomUUID().toString())
                    .setBuyerName(new Name.Builder().setFirstName("Test").setLastName("Buyer").build())
                    .setEmail(BUYER_EMAIL)
                    .setPassword(passwordEncoder.encode("not-used-in-tests"))
                    .setBuyingPart("Various")
                    .build());
        }

        // Test seller (hashed password)
        if (userRepository.findByEmail(SELLER_EMAIL).isEmpty()) {
            userRepository.save(new Seller.Builder()
                    .setUserid(UUID.randomUUID().toString())
                    .setSellerName(new Name.Builder().setFirstName("Test").setLastName("Seller").build())
                    .setEmail(SELLER_EMAIL)
                    .setPassword(passwordEncoder.encode("not-used-in-tests"))
                    .setSellingPart("Various")
                    .build());
        }
        String sellerId = userRepository.findByEmail(SELLER_EMAIL).orElseThrow().getUserid();

        // An approved part: R500, 5 in stock
        partId = UUID.randomUUID().toString();
        carPartRepository.save(new CarPart.Builder()
                .setCarPartId(partId)
                .setPartName("Test Brake Pads")
                .setDescription("Created by OrderControllerTest")
                .setPrice(500.0)
                .setStockQuantity(5)
                .setModel("Toyota")
                .setCategoryId(1L)
                .setSellerId(sellerId)
                .setStatus("APPROVED")
                .setPartCondition("New")
                .build());

        buyerToken = jwtService.generateToken(BUYER_EMAIL, "buyer");
        sellerToken = jwtService.generateToken(SELLER_EMAIL, "seller");
    }

    @AfterAll
    void cleanUp() {
        if (orderReference != null) orderRepository.deleteById(orderReference);
        if (partId != null) carPartRepository.deleteById(partId);
    }

    @Test
    void a_buyerPlacesOrder() {
        PlaceOrderRequestDTO request = new PlaceOrderRequestDTO(
                List.of(new PlaceOrderRequestDTO.Line(partId, 1)), "WELCOME10", "card");

        ResponseEntity<OrderResponseDTO> response = restTemplate.exchange(
                baseUrl(), HttpMethod.POST, withToken(request, buyerToken), OrderResponseDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        OrderResponseDTO order = response.getBody();
        assertNotNull(order);

        // Prices are calculated on the server: 500 + 50 delivery - 10% of 500
        assertEquals(500.0, order.subtotal());
        assertEquals(50.0, order.deliveryFee());
        assertEquals(50.0, order.discount());
        assertEquals(500.0, order.total());
        assertEquals("PAID", order.status());

        orderReference = order.reference();
        System.out.println("Placed order: " + order.reference());
    }

    @Test
    void b_buyerCanReadOwnOrder() {
        ResponseEntity<OrderResponseDTO> response = restTemplate.exchange(
                baseUrl() + "/" + orderReference, HttpMethod.GET, withToken(null, buyerToken), OrderResponseDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orderReference, response.getBody().reference());
    }

    @Test
    void c_strangerCannotReadOrder() {
        String strangerToken = jwtService.generateToken("someone.else@automarket.test", "buyer");
        ResponseEntity<String> response = restTemplate.exchange(
                baseUrl() + "/" + orderReference, HttpMethod.GET, withToken(null, strangerToken), String.class);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    void d_buyerCannotShip() {
        ResponseEntity<String> response = restTemplate.exchange(
                baseUrl() + "/" + orderReference + "/ship", HttpMethod.PUT, withToken(null, buyerToken), String.class);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    void e_sellerShipsOrder() {
        ResponseEntity<OrderResponseDTO> response = restTemplate.exchange(
                baseUrl() + "/" + orderReference + "/ship", HttpMethod.PUT, withToken(null, sellerToken), OrderResponseDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("SHIPPED", response.getBody().status());
    }

    @Test
    void f_cannotOrderMoreThanStock() {
        // 5 in stock, 1 already bought: 4 left
        PlaceOrderRequestDTO request = new PlaceOrderRequestDTO(
                List.of(new PlaceOrderRequestDTO.Line(partId, 10)), null, "card");

        ResponseEntity<String> response = restTemplate.exchange(
                baseUrl(), HttpMethod.POST, withToken(request, buyerToken), String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void g_placeOrderWithoutTokenIsRejected() {
        PlaceOrderRequestDTO request = new PlaceOrderRequestDTO(
                List.of(new PlaceOrderRequestDTO.Line(partId, 1)), null, "card");

        ResponseEntity<String> response = restTemplate.exchange(
                baseUrl(), HttpMethod.POST, withToken(request, null), String.class);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }
}