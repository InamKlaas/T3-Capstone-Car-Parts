package za.ac.cput.carpartmarket.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.carpartmarket.Domain.Delivery;
import za.ac.cput.carpartmarket.Repository.DeliveryRepository;

import java.util.List;

@Service
public class DeliveryService implements IDeliveryService {

    @Autowired
    private DeliveryRepository repository;

    @Override
    public Delivery create(Delivery delivery) {
        return repository.save(delivery);
    }

    @Override
    public Delivery read(String deliveryId) {
        return repository.findById(deliveryId).orElse(null);
    }

    @Override
    public Delivery update(Delivery delivery) {
        return repository.save(delivery);
    }

    @Override
    public boolean delete(String carPartId) {
        repository.deleteById(carPartId);
        return true;
    }

    @Override
    public List<Delivery> getall() {
        return repository.findAll();
    }
}