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
import za.ac.cput.carpartmarket.Domain.Buyer;
import za.ac.cput.carpartmarket.Domain.PaymentMethod;
import za.ac.cput.carpartmarket.Factory.PaymentMethodFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
@TestMethodOrder(MethodOrderer.MethodName.class)
class PaymentMethodControllerTest {

    private static Buyer buyer;
    private static PaymentMethod payment = PaymentMethodFactory.createPaymentFactory("823UH", buyer, "Transaction", "Nedbank", 829);
    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl() {
        return restTemplate.getRootUri() + "/Payments";
    }

    @Test
    void a_create() {
        String url = baseUrl() + "/create";
        System.out.println("URL: " + url);
        ResponseEntity<PaymentMethod> postResponse = restTemplate.postForEntity(url, payment, PaymentMethod.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        PaymentMethod paymentSaved = postResponse.getBody();
        System.out.println("Save data: " + paymentSaved);
        payment = paymentSaved;
    }


    @Test
    void b_read() {
        String url = baseUrl() + "/read/" + payment.getMethodId();
        System.out.println("URL: " + url);
        ResponseEntity<PaymentMethod> response = restTemplate.getForEntity(url, PaymentMethod.class);
        assertEquals(payment.getMethodId(), response.getBody().getMethodId());
        System.out.println(response.getBody());
    }


    @Test
    void c_update() {
        PaymentMethod updatePaymentMethod = new PaymentMethod.Builder()
                .copy(payment)
                .setProvider("Absa")
                .build();
        String url = baseUrl() + "/update";
        System.out.println("URL: " + url);
        restTemplate.put(url, updatePaymentMethod);

        String readUrl = baseUrl() + "/read/" + payment.getMethodId();
        ResponseEntity<PaymentMethod> response = restTemplate.getForEntity(readUrl, PaymentMethod.class);
        System.out.println(response.getBody());
        payment = response.getBody();
        System.out.println("Update data: " + payment);
    }


    @Test
    void d_deleteById() {
        String url = baseUrl() + "/delete/" + payment.getMethodId();
        System.out.println("URL: " + url);
        restTemplate.delete(url);
        System.out.println("Delete: true");
    }
}