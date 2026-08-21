//package za.ac.cput.carpartmarket.Controller;
//
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.resttestclient.TestRestTemplate;
//import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import za.ac.cput.carpartmarket.Domain.Name;
//import za.ac.cput.carpartmarket.Domain.Seller;
//import za.ac.cput.carpartmarket.Factory.NameFactory;
//import za.ac.cput.carpartmarket.Factory.SellerFactory;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@AutoConfigureTestRestTemplate
//@TestMethodOrder(MethodOrderer.MethodName.class)
//class SellerControllerTest {
//
//    private static Name name = new Name.Builder()
//            .setFirstName("John")
//            .setLastName("Doe")
//            .build();
//
//    private static Seller seller = SellerFactory.createSeller(
//            113L,
//            name,
//            "Car Parts"
//    );
//
//    String BASE_URL = "http://localhost:8080/seller";
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Test
//    void a_create() {
//
//        String url = BASE_URL;
//
//        ResponseEntity<Seller> postResponse =
//                restTemplate.postForEntity(
//                        url,
//                        seller,
//                        Seller.class
//                );
//
//        assertNotNull(postResponse);
//        assertNotNull(postResponse.getBody());
//        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
//
//        Seller sellerSaved = postResponse.getBody();
//
//        System.out.println("Save data: " + sellerSaved);
//
//        seller = sellerSaved;
//    }
//
//    @Test
//    void b_read() {
//
//        String url = BASE_URL + "/" + seller.getSellerId();
//
//        System.out.println("URL: " + url);
//
//        ResponseEntity<Seller> response =
//                restTemplate.getForEntity(
//                        url,
//                        Seller.class
//                );
//
//        assertNotNull(response);
//        assertNotNull(response.getBody());
//
//        assertEquals(
//                seller.getSellerId(),
//                response.getBody().getSellerId()
//        );
//
//        System.out.println(response.getBody());
//    }
//
//    @Test
//    void c_update() {
//
//        Seller updateSeller = SellerFactory.createSeller(
//                seller.getSellerId(),
//                NameFactory.createName(
//                        "John",
//                        "Smith"
//                ),
//                "Engine Parts"
//        );
//
//        String url = BASE_URL;
//
//        System.out.println("URL: " + url);
//
//        restTemplate.put(url, updateSeller);
//
//        String readUrl =
//                BASE_URL + "/" + seller.getSellerId();
//
//        ResponseEntity<Seller> response =
//                restTemplate.getForEntity(
//                        readUrl,
//                        Seller.class
//                );
//
//        System.out.println(response.getBody());
//
//        seller = response.getBody();
//
//        System.out.println("Update data: " + seller);
//    }
//
//    @Test
//    @Disabled
//    void d_delete() {
//
//        String url =
//                BASE_URL + "/" + seller.getSellerId();
//
//        System.out.println("URL: " + url);
//
//        restTemplate.delete(url);
//
//        ResponseEntity<Seller> response =
//                restTemplate.getForEntity(
//                        url,
//                        Seller.class
//                );
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//        System.out.println("Delete: true");
//    }
//}