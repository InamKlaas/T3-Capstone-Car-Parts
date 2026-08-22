package za.ac.cput.carpartmarket.Factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.carpartmarket.Domain.Role;

import static org.junit.jupiter.api.Assertions.*;

class RoleFactoryTest {

    @Test
    void createRole() {
        Role role = RoleFactory.createRole("R02","Admin","responsible for managing users and giving permissions");
        assertNotNull(role);
        System.out.println(role);
    }

    @Test
    void createRoleWithNullRoleName(){
        Role role = RoleFactory.createRole("R01",null,"responsible for managing users and giving permissions");
        assertNull(role);
    }

    @Test
    void createRoleWithEmptyDescription(){
        Role role = RoleFactory.createRole("R03","Admin","");
        assertNull(role);
    }


}