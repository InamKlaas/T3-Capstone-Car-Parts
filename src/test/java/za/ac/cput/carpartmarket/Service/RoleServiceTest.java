package za.ac.cput.carpartmarket.Service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.carpartmarket.Domain.Role;
import za.ac.cput.carpartmarket.Domain.Transaction;
import za.ac.cput.carpartmarket.Domain.Vehicle;
import za.ac.cput.carpartmarket.Factory.RoleFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)

class RoleServiceTest {
    @Autowired
    private RoleService roleService;

    private static Role role= RoleFactory.createRole("W12","Seller","Selling different parts");

    @Test
    void a_create() {
        Role role1 = roleService.create(role);
        assertNotNull(role1);
        System.out.println(role1);
    }

    @Test
    void b_read() {
        Role role1 = roleService.read(role.getRoleId());
        assertNotNull(role1);
        System.out.println(role1);
    }

    @Test
    void c_update() {
        Role role1 = roleService.update(role);
        assertNotNull(role1);
        System.out.println(role1);
    }

    @Test
    @Disabled
    void d_delete() {
        roleService.delete(role.getRoleId());
    }
}