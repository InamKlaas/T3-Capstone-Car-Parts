package za.ac.cput.carpartmarket.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.carpartmarket.Domain.Order;
import za.ac.cput.carpartmarket.Repository.OrderRepository;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository repository;

    @Override
    public Order create(Order order) {
        return repository.save(order);
    }

    @Override
    public Order read(String str) {
        return repository.findById(str).orElse(null);
    }

    @Override
    public Order update(Order order) {
        return repository.save(order);
    }

    @Override
    public boolean delete(String l) {
        repository.deleteById(l);
        return true;
    }
}