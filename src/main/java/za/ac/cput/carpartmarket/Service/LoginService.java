package za.ac.cput.carpartmarket.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.carpartmarket.Domain.Login;
import za.ac.cput.carpartmarket.Repository.ILoginRepository;

@Service
public class LoginService implements ILoginService {

    @Autowired
    private ILoginRepository repository;

    @Override
    public Login create(Login login) { return repository.save(login);}


    @Override
    public Login read(String loginId) {
        return (Login) repository.findById(loginId).orElse(null);
    }

    @Override
    public Login update(Login login) { return repository.save(login);}

    @Override
    public boolean delete(String loginId) {
        repository.deleteById(loginId);
        return true;
    }
}