package za.ac.cput.carpartmarket.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.carpartmarket.Domain.Buyer;
import za.ac.cput.carpartmarket.Repository.IBuyerRepository;

    @Service
    public class BuyerService implements IBuyerService {

        @Autowired
        private IBuyerRepository repository;

        @Override
        public Buyer create(Buyer buyer) {
            return repository.save(buyer);
        }

        @Override
        public Buyer read(Long aLong) {
            return repository.findById(aLong).orElse(null);
        }

        @Override
        public Buyer update(Buyer buyer) {
            return repository.save(buyer);
        }

        @Override
        public boolean delete(Long l) {
            repository.deleteById(l);
            return true;
        }
    }