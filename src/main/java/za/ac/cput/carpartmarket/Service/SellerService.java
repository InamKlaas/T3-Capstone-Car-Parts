package za.ac.cput.carpartmarket.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.carpartmarket.Domain.Name;
import za.ac.cput.carpartmarket.Domain.Seller;
import za.ac.cput.carpartmarket.Domain.User;
import za.ac.cput.carpartmarket.Repository.ISellerRepository;

@Service
public class SellerService implements ISellerService {

    @Autowired
    private ISellerRepository repository;

    @Override
    public Seller create(Seller seller) {
        return repository.save(seller);
    }

    @Override
    public Seller read(String str) {
        return repository.findById(str).orElse(null);
    }

    @Override
    public Seller update(Seller seller) {
        return repository.save(seller);
    }

    @Override
    public boolean delete(String l) {
        repository.deleteById(l);
        return false;
    }


}