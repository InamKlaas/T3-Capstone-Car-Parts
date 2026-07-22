package za.ac.cput.carpartmarket.Service;

import za.ac.cput.carpartmarket.Domain.PaymentMethod;

import java.util.List;

public interface IPaymentMethodService extends IService<PaymentMethod, String>{
    List<PaymentMethod> getall();
}
