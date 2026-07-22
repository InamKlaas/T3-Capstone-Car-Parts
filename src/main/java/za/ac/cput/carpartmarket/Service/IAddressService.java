package za.ac.cput.carpartmarket.Service;

import za.ac.cput.carpartmarket.Domain.Address;

import java.util.List;

public interface IAddressService extends IService<Address, String>{
    List<Address> getall();
}
