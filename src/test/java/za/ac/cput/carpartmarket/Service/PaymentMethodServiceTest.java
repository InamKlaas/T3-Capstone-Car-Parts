package za.ac.cput.carpartmarket.Service;


import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.carpartmarket.Domain.Buyer;
import za.ac.cput.carpartmarket.Domain.PaymentMethod;
import za.ac.cput.carpartmarket.Factory.PaymentMethodFactory;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class PaymentMethodServiceTest {

    @Autowired
    private PaymentMethodService service;
    private static Buyer buyer;
    private PaymentMethod payment = PaymentMethodFactory.createPaymentFactory("838HJ", buyer, "transaction", "FNB", 746);

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

//    @Test
//    void delete() {
//    }

    @Test
    void e_getall() {
        System.out.println(service.getall());
    }
}