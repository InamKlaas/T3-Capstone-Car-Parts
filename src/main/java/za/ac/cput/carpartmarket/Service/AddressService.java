package za.ac.cput.carpartmarket.Service;

import org.springframework.beans.factory.annotation.Autowired;
import za.ac.cput.carpartmarket.Domain.Address;
import za.ac.cput.carpartmarket.Repository.AddressRepository;

import java.util.List;

public class AddressService implements IAddressService{

    private AddressRepository repository;

    @Autowired
    public AddressService(AddressRepository repository){this.repository = repository;}

    @Override
    public Address create(Address address){return this.repository.save(address);}

    @Override
    public Address read(String id){return this.repository.findById(id).orElse(null);}

    @Override
    public Address update(Address address){return this.repository.save(address);}

    @Override
    public boolean delete(String id){
        this.repository.deleteById(id);
        return true;
    }

    @Override
    public List<Address> getall(){
        return this.repository.findAll();
    }
}
