package za.ac.cput.carpartmarket.Service;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.carpartmarket.Domain.Buyer;
import za.ac.cput.carpartmarket.Domain.PaymentMethod;
import za.ac.cput.carpartmarket.Factory.BuyerFactory;
import za.ac.cput.carpartmarket.Factory.NameFactory;
import za.ac.cput.carpartmarket.Factory.PaymentMethodFactory;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class PaymentMethodServiceTest {

    @Autowired
    private PaymentMethodService service;
    @Autowired
    private BuyerService buyerService;


    @BeforeEach
    void setUp() {
         Buyer buyer = BuyerFactory.createBuyer(
                "112L",
                NameFactory.createName("Vera", "Doja"),
                "Car Parts"
        );
        Buyer buyer1 = buyerService.create(buyer);

   }
    private static Buyer buyer;
    private PaymentMethod payment = PaymentMethodFactory.createPaymentFactory("JDH87", buyer, "transaction", "FNB", 738 );

    @Test
    void a_create(){
        PaymentMethod created = service.create(payment);
        assertNotNull(created);
        System.out.println(created);
    }

    @Test
    void b_read() {
        PaymentMethod read = service.read(payment.getMethodId());
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    void c_update() {
        PaymentMethod newPaymentMethod = new PaymentMethod.Builder().copy(payment).setMethodId("8373").build();
        PaymentMethod updated = service.update(newPaymentMethod);
        assertNotNull(updated);
        System.out.println();
    }

    @Test
    @Disabled
    void d_delete() {
        service.delete(payment.getMethodId());
        PaymentMethod deleted = service.read(payment.getMethodId());
        assertNull(deleted);
    }

    @Test
    void e_getall() {
        System.out.println(service.getall());
    }
}