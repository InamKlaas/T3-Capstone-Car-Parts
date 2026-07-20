package za.ac.cput.carpartmarket.Factory;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import za.ac.cput.carpartmarket.Domain.Buyer;
import za.ac.cput.carpartmarket.Domain.PaymentMethod;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PaymentMethodFactoryTest {

    private static Buyer buyer;
    private static PaymentMethod b1 = PaymentMethodFactory.createPaymentFactory("HE82", buyer, "cash send", "Nedbank", 726);
    private static PaymentMethod b2 = PaymentMethodFactory.createPaymentFactory("HE82", buyer, "cash send", "Nedbank", 7296);


    @Test
    @Order(1)
    public void createPaymentMethod(){
        assertNotNull(b1);
        System.out.println(b1.toString());
    }

    @Test
    @Order(2)
    public void createPaymentMethodWithWrongCvvFormat(){
        assertNotNull(b2);
        System.out.println(b2.toString());
    }
}
