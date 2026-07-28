package za.ac.cput.carpartmarket.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.carpartmarket.Domain.User;
import za.ac.cput.carpartmarket.Repository.IUserRepository;

@Service
public class UserService implements IUserService{

    @Autowired
    private IUserRepository repository;

    @Override
    public User create(User user) {
        return repository.save(User);
    }

    @Override
    public User read(Long aLong) {
        return repository.findById(aLong).orElse(null);
    }

    @Override
    public User update(User user) {
        return repository.save(User);
    }

    @Override
    public boolean delete(Long transactionId) {
        repository.deleteById(transactionId);
        return true;

    }
}