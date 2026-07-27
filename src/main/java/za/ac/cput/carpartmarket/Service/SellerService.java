package za.ac.cput.carpartmarket.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.carpartmarket.Domain.Seller;
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
    public Seller read(Long aLong) {
        return repository.findById(aLong).orElse(null);
    }

    @Override
    public Seller update(Seller seller) {
        return repository.save(seller);
    }

    @Override
    public boolean delete(Long l) {
        repository.deleteById(l);
        return false;
    }
}