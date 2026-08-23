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
import za.ac.cput.carpartmarket.Domain.Role;
import za.ac.cput.carpartmarket.Domain.Vehicle;
import za.ac.cput.carpartmarket.Factory.RoleFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
@TestMethodOrder(MethodOrderer.MethodName.class)
class RoleControllerTest {

    private static Role role = RoleFactory.createRole("S01","Admin","managing users and giving permissions");

    String BASE_URL = "/roles";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void a_create() {
        String url = BASE_URL + "/create";
        System.out.println("URL: " + url);
        ResponseEntity<Role> postResponse = restTemplate.postForEntity(url, role, Role.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        Role roleSaved = postResponse.getBody();
        System.out.println("Save data: " + roleSaved);
        role = roleSaved;
    }

    @Test
    void b_read() {
        String url = BASE_URL + "/" + role.getRoleId();
        System.out.println("URL: " + url);
        ResponseEntity<Role> response = restTemplate.getForEntity(url, Role.class);
        assertEquals(role.getRoleId(), response.getBody().getRoleId());
        System.out.println(response.getBody());
    }

    @Test
    void c_update() {
        Role updateRole = new Role.Builder()
                .setRoleId("Z02")
                .setRoleName("Seller")
                .build();
        String url = BASE_URL;
        System.out.println("URL: " + url);
        restTemplate.put(url, updateRole);

        String readUrl = BASE_URL + "/" + role.getRoleId();
        ResponseEntity<Role> response = restTemplate.getForEntity(readUrl, Role.class);
        System.out.println(response.getBody());
        role = response.getBody();
        System.out.println("Update data: " + role);


    }

    @Test
    void d_deleteById() {
        String url = BASE_URL + "/" + role.getRoleId();
        System.out.println("URL: " + url);
        ResponseEntity<Role> response = restTemplate.getForEntity(url, Role.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        System.out.println("Delete: true");
    }
}