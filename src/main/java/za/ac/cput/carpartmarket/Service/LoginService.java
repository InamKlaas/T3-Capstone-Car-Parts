package za.ac.cput.carpartmarket.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.carpartmarket.Domain.Login;
import za.ac.cput.carpartmarket.Domain.User;
import za.ac.cput.carpartmarket.Repository.ILoginRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class LoginService implements ILoginService {

//    private final ILoginRepository loginRepository;
    private ILoginRepository loginRepository;

    @Autowired
    public LoginService(ILoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    // ---------- existing CRUD ----------
    @Override
    public Login create(Login login) {
        return loginRepository.save(login);
    }

    @Override
    public Login read(String loginId) {
        return loginRepository.findById(loginId).orElse(null);
    }

    @Override
    public Login update(Login login) {
        return loginRepository.save(login);
    }

    @Override
    public boolean delete(String loginId) {
        loginRepository.deleteById(loginId);
        return true;
    }

    // ---------- NEW: record a login attempt ----------
    public Login recordLogin(User user, String email, String status) {
        Login login = new Login.Builder()
                .setLoginId(UUID.randomUUID().toString())
                .setUser(user)
                .setEmail(email)
                .setPassword(user.getPassword())   // hashed password only, never raw
                .setLoginDate(LocalDateTime.now())
                .setStatus(status)
                .build();
        return loginRepository.save(login);
    }
}