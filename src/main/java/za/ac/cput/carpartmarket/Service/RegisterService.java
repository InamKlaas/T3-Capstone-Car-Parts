package za.ac.cput.carpartmarket.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.carpartmarket.Domain.PaymentMethod;
import za.ac.cput.carpartmarket.Domain.Register;
import za.ac.cput.carpartmarket.Repository.PaymentMethodRepository;
import za.ac.cput.carpartmarket.Repository.RegisterRepository;

import java.util.List;

@Service
public class RegisterService implements IRegisterService {

    private RegisterRepository repository;

    @Autowired
    public RegisterService(RegisterRepository repository){this.repository = repository;}

    @Override
    public Register create(Register register){return this.repository.save(register);}

    @Override
    public Register read(String registrationId){return this.repository.findById(registrationId).orElse(null);}

    @Override
    public Register update(Register register){return this.repository.save(register);}

    @Override
    public boolean delete(String registrationId){
        this.repository.deleteById(registrationId);
        return true;
    }

    @Override
    public List<Register> getall(){
        return this.repository.findAll();
    }
}
