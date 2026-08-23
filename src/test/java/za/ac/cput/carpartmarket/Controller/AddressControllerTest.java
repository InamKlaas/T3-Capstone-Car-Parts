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
import za.ac.cput.carpartmarket.Domain.CarPart;
import za.ac.cput.carpartmarket.Factory.AddressFactory;
import za.ac.cput.carpartmarket.Factory.CarPartFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
@TestMethodOrder(MethodOrderer.MethodName.class)
class AddressControllerTest {

    private static Address address = AddressFactory.createAddress("28", 92298394L, "Hout Bay", "Cape Town", "Estern Cape", 7382, "South Africa");

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl() {
        return restTemplate.getRootUri() + "/Address";
    }

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
                .setCity("Mount Frere")
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
    void d_deleteById() {
        String url = baseUrl() + "/delete/" + address.getStreetNumber();
        System.out.println("URL: " + url);
        restTemplate.delete(url);
        System.out.println("Delete: true");
    }

//    @Test
//    void e_getall() {
//        String url = baseUrl() + "/getall";
//        ResponseEntity<Address> response = restTemplate.postForEntity(url, address, Address.class);
//        assertNotNull(response);
//        assertNotNull(response.getBody());
//        System.out.println("get all");
//        for(Address adr: response.getBody()){
//            System.out.println(adr);
//        }

//    }
}