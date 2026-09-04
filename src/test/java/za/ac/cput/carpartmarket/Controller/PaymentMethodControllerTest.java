package za.ac.cput.carpartmarket.Controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.carpartmarket.Domain.Buyer;
import za.ac.cput.carpartmarket.Domain.PaymentMethod;
import za.ac.cput.carpartmarket.Factory.BuyerFactory;
import za.ac.cput.carpartmarket.Factory.NameFactory;
import za.ac.cput.carpartmarket.Factory.PaymentMethodFactory;
import za.ac.cput.carpartmarket.Repository.IBuyerRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
@TestMethodOrder(MethodOrderer.MethodName.class)
class PaymentMethodControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private IBuyerRepository buyerRepository;

    private String baseUrl() { return restTemplate.getRootUri() + "/Payments";}

    private static Buyer buyer;
    private static PaymentMethod payment;

    @BeforeAll
    public static void setUp() {
        buyer = BuyerFactory.createBuyer("buyer123", NameFactory.createName("Vera", "Doja"), "car pad");
        payment = PaymentMethodFactory.createPaymentFactory("823UH", buyer, "Transaction", "Nedbank", 829);
    }

    @Test
    void a_create() {
        buyer = buyerRepository.save(buyer);
        assertNotNull(buyer, "Buyer should be saved successfully");
        System.out.println("Buyer saved: " + buyer.getUserid());


//        register = RegisterFactory.createRegister("reg456", buyer, LocalDate.of(2026, 9, 1), "Active");
        assertNotNull(payment, "payment should be created");
        System.out.println("payment created: " + payment.getMethodId());

        String url = baseUrl() + "/create";
        System.out.println("URL: " + url);

        ResponseEntity<PaymentMethod> postResponse = restTemplate.postForEntity(url, payment, PaymentMethod.class);

        assertNotNull(postResponse, "Response should not be null");
        assertNotNull(postResponse.getBody(), "Response body should not be null");
        assertEquals(HttpStatus.OK, postResponse.getStatusCode(), "Status should be 200 OK");

        PaymentMethod paymentSaved = postResponse.getBody();
        System.out.println("Saved payment: " + paymentSaved);

        payment = paymentSaved;
    }

    @Test
    void b_read() {
        assertNotNull(payment, "Payment must be created first");

        String url = baseUrl() + "/read/" + payment.getMethodId();
        System.out.println("URL: " + url);

        ResponseEntity<PaymentMethod> response = restTemplate.getForEntity(url, PaymentMethod.class);

        assertNotNull(response.getBody(), "Response body should not be null");
        assertEquals(payment.getMethodId(), response.getBody().getMethodId(), "Registration ID should match");
        System.out.println("Read register: " + response.getBody());
    }

    @Test
    void c_update() {
        assertNotNull(payment, "payment must be created first ");

        PaymentMethod updatePaymentMethod = new PaymentMethod.Builder()
                .copy(payment)
                .setProvider("Standard Bank")
                .build();

        String url = baseUrl() + "/update";
        System.out.println("URL: " + url);

        restTemplate.put(url, updatePaymentMethod);

        String readUrl = baseUrl() + "/read/" + payment.getMethodId();
        ResponseEntity<PaymentMethod> response = restTemplate.getForEntity(readUrl, PaymentMethod.class);

        assertNotNull(response.getBody(), "Response body should not be null");
        payment = response.getBody();
        assertEquals("Standard Bank", payment.getProvider(), "provider should be updated to Standard Bank");
        System.out.println("Updated register: " + payment);
    }

    @Test
    void d_deleteById() {
        assertNotNull(payment, "Register must be created first");

        String url = baseUrl() + "/delete/" + payment.getMethodId();
        System.out.println("URL: " + url);

        restTemplate.delete(url);
        System.out.println("Deleted payment ID: " + payment.getMethodId());

        String readUrl = baseUrl() + "/read/" + payment.getMethodId();
        ResponseEntity<PaymentMethod> response = restTemplate.getForEntity(readUrl, PaymentMethod.class);
        assertNull(response.getBody(), "Payment Method should be null after deletion");
    }

    @Test
    void e_getall() {
        String url = baseUrl() + "/getall";
        System.out.println("URL: " + url);

        ResponseEntity<PaymentMethod[]> response = restTemplate.getForEntity(url, PaymentMethod[].class);
        assertNotNull(response.getBody(), "Response body should not be null");
        System.out.println("All registers count: " + response.getBody().length);
        for (PaymentMethod r : response.getBody()) {
            System.out.println("Register: " + r);
        }
    }
}