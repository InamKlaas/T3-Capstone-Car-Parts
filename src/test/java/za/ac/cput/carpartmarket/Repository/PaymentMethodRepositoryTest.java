package za.ac.cput.carpartmarket.Repository;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import za.ac.cput.carpartmarket.Domain.Buyer;
import za.ac.cput.carpartmarket.Domain.PaymentMethod;
import za.ac.cput.carpartmarket.Factory.PaymentMethodFactory;

import static org.junit.jupiter.api.Assertions.*;

class PaymentMethodRepositoryTest {

    private static Buyer buyer;
    private static PaymentMethod P1 = PaymentMethodFactory.createPaymentFactory("HFU87", buyer, "cash send", "Nedbank", 736);
    private static PaymentMethod P2 = PaymentMethodFactory.createPaymentFactory("HFU87", buyer, "cash send", "Nedbank", 736);
    private static PaymentMethod P3 = PaymentMethodFactory.createPaymentFactory("HFU87", buyer, "cash send", "Nedbank", 7368);


    @Test
    @Order(1)
    public void createPaymentMethod() {
        assertNotNull(P1);
        System.out.println(P1.toString());
    }

    @Test
    @Order(2)
    public void testCreatePaymentMethodWithAllAttributes() {
        assertNotNull(P2);
        System.out.println(P2.toString());
    }
    @Test
    @Order(3)
    public void testCreatePaymentMethodThatFails(){
        //fail
        assertNotNull(P3);
        System.out.println(P3.toString());
    }
}