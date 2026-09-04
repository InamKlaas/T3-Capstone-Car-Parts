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
import za.ac.cput.carpartmarket.Domain.Address;
import za.ac.cput.carpartmarket.Factory.AddressFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
@TestMethodOrder(MethodOrderer.MethodName.class)
class AddressControllerTest {

    private static Address address = AddressFactory.createAddress(
            "73",
            "TT45",
            "Bellville",
            "Cape Town",
            "Western Cape",
            6437,
            "South africa");

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl(){return restTemplate.getRootUri() + "/Addresses";}

    @Test
    void a_create() {
        String url = baseUrl() + "/create";
        System.out.println("URL: " + url);
        ResponseEntity<Address> postResponse = restTemplate.postForEntity(url, address, Address.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        Address addressSaved = postResponse.getBody();
        System.out.println("Save data: " + addressSaved);
        address = addressSaved;
    }

    @Test
    void b_read() {
        String url = baseUrl() + "/read/" + address.getStreetNumber();
        System.out.println("URL: " + url);
        ResponseEntity<Address> response = restTemplate.getForEntity(url, Address.class);
        assertEquals(address.getStreetNumber(), response.getBody().getStreetNumber());
        System.out.println(response.getBody());
    }

    @Test
    void c_update() {
        Address updateAddress = new Address.Builder()
                .copy(address)
                .setCity("Mount Frerer")
                .build();
        String url = baseUrl() + "/update";
        System.out.println("URL: " + url);
        restTemplate.put(url, updateAddress);

        String readUrl = baseUrl() + "/read/" + address.getStreetNumber();
        ResponseEntity<Address> response = restTemplate.getForEntity(readUrl, Address.class);
        System.out.println(response.getBody());
        address = response.getBody();
        System.out.println("Update data: " + address);
    }

    @Test
    void d_delete() {
        String url = baseUrl() + "/delete/" + address.getStreetNumber();
        System.out.println("URL: " + url);
        restTemplate.delete(url);
        System.out.println("Delete: true");
    }

//    @Test
//    void getall() {
//    }
}