package za.ac.cput.carpartmarket.Controller;

import org.junit.jupiter.api.Disabled;
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
import za.ac.cput.carpartmarket.Domain.Name;
import za.ac.cput.carpartmarket.Domain.Order;
import za.ac.cput.carpartmarket.Domain.Transaction;
import za.ac.cput.carpartmarket.Factory.BuyerFactory;
import za.ac.cput.carpartmarket.Factory.NameFactory;
import za.ac.cput.carpartmarket.Factory.OrderFactory;
import za.ac.cput.carpartmarket.Factory.TransactionFactory;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureTestRestTemplate
@TestMethodOrder(MethodOrderer.MethodName.class)
class TransactionControllerTest {

    private static Name name = NameFactory.createName("Vera","Doja");
    private static Buyer buyer = BuyerFactory.createBuyer(
            1L,
            name,
            "Brake pads"
    );

    private static Order order = OrderFactory.createOrder(
            2L,
            buyer,
            "Pending",
            LocalDateTime.of(2020,3,23,0,0),
            1500.00,
            11L
    );

    private static Transaction transaction = TransactionFactory.createTransaction(
            1L,
            LocalDateTime.of(2020,3,23,0,0),
            order,
            1500.00
    );

    String BASE_URL = "http://localhost:8080/transactions";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void a_create() {
        String url = BASE_URL;
        System.out.println("URL: " + url);
        ResponseEntity<Transaction> postResponse = restTemplate.postForEntity(url, transaction, Transaction.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        Transaction transactionSaved = postResponse.getBody();
        System.out.println("Save data: " + transactionSaved);
        transaction = transactionSaved;
    }

    @Test
    void b_read() {
        String url = BASE_URL + "/" + transaction.getTransactionId();
        System.out.println("URL: " + url);
        ResponseEntity<Transaction> response = restTemplate.getForEntity(url, Transaction.class);
        assertEquals(transaction.getTransactionId(), response.getBody().getTransactionId());
        System.out.println(response.getBody());
    }

    @Test
    void c_update() {
        Transaction updateTransaction = new Transaction.Builder().copy(transaction)
                .setAmount(2000.00)
                .build();
        String url = BASE_URL;
        System.out.println("URL: " + url);
        restTemplate.put(url, updateTransaction);

        String readUrl = BASE_URL + "/" + transaction.getTransactionId();
        ResponseEntity<Transaction> response = restTemplate.getForEntity(readUrl, Transaction.class);
        System.out.println(response.getBody());
        transaction = response.getBody();
        System.out.println("Update data: " + transaction);
    }

    @Test
    @Disabled
    void d_delete() {
        String url = BASE_URL + "/" + transaction.getTransactionId();
        System.out.println("URL: " + url);
        restTemplate.delete(url);
        ResponseEntity<Transaction> response = restTemplate.getForEntity(url, Transaction.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        System.out.println("Delete: true");
    }
}