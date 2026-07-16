package za.ac.cput.carpartmarket.Service;

import org.springframework.beans.factory.annotation.Autowired;
import za.ac.cput.carpartmarket.Domain.Address;
import za.ac.cput.carpartmarket.Domain.PaymentMethod;
import za.ac.cput.carpartmarket.Repository.AddressRepository;
import za.ac.cput.carpartmarket.Repository.PaymentMethodRepository;

import java.util.List;

public class PaymentMethodService implements IPaymentMethodService{

    private PaymentMethodRepository repository;

    @Autowired
    public PaymentMethodService(PaymentMethodRepository repository){this.repository = repository;}

    @Override
    public PaymentMethod create(PaymentMethod payment){return this.repository.save(payment);}

    @Override
    public PaymentMethod read(String id){return this.repository.findById(id).orElse(null);}

    @Override
    public PaymentMethod update(PaymentMethod payment){return this.repository.save(payment);}

    @Override
    public boolean delete(String id){
        this.repository.deleteById(id);
        return true;
    }

    @Override
    public List<PaymentMethod> getall(){
        return this.repository.findAll();
    }
}
