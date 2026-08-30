package za.ac.cput.carpartmarket.Service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.carpartmarket.Domain.Buyer;
import za.ac.cput.carpartmarket.Domain.Register;
import za.ac.cput.carpartmarket.Factory.BuyerFactory;
import za.ac.cput.carpartmarket.Factory.NameFactory;
import za.ac.cput.carpartmarket.Factory.RegisterFactory;
import za.ac.cput.carpartmarket.Repository.IBuyerRepository;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
class RegisterServiceTest {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private IBuyerRepository buyerRepository;

    private static Buyer buyer;
    private static Register register;
    private static final String REGISTRATION_ID = "REG001";

    @BeforeAll
    public static void setUp() {
        buyer = BuyerFactory.createBuyer("YRY73", NameFactory.createName("Inacio", "Miguel"), "Headlights");
    }

    @BeforeEach
    public void init() {
        buyer = buyerRepository.save(buyer);

        register = RegisterFactory.createRegister(REGISTRATION_ID, buyer, LocalDate.now(), "ACTIVE");
    }

    @Test
    @Order(1)
    void testCreate() {
        Register created = registerService.create(register);
        assertNotNull(created);
        assertEquals(REGISTRATION_ID, created.getRegistrationId());
        System.out.println("Created: " + created);
    }

    @Test
    @Order(2)
    void testRead() {
        registerService.create(register);
        Register read = registerService.read(REGISTRATION_ID);
        assertNotNull(read);
        assertEquals(REGISTRATION_ID, read.getRegistrationId());
        System.out.println("Read: " + read);
    }

    @Test
    @Order(3)
    void testUpdate() {
        registerService.create(register);

        Register updatedRegister = new Register.Builder()
                .copy(register)
                .setStatus("INACTIVE")
                .build();

        Register updated = registerService.update(updatedRegister);
        assertNotNull(updated);
        assertEquals("INACTIVE", updated.getStatus());
        System.out.println("Updated: " + updated);
    }

    @Test
    @Order(4)
    void testGetAll() {
        registerService.create(register);
        List<Register> registers = registerService.getall();
        assertNotNull(registers);
        System.out.println("All registers: " + registers);
    }

    @Test
    @Order(5)
    @Disabled
    void testDelete() {
        registerService.create(register);
        boolean deleted = registerService.delete(REGISTRATION_ID);
        assertTrue(deleted);

        Register read = registerService.read(REGISTRATION_ID);
        assertNull(read);
        System.out.println("Deleted: " + REGISTRATION_ID);
    }
}
