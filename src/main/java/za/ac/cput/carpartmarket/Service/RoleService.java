package za.ac.cput.carpartmarket.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.carpartmarket.Domain.Role;
import za.ac.cput.carpartmarket.Repository.RoleRepository;

@Service

public class RoleService implements IRoleService {
    @Autowired
    private RoleRepository repository;

    @Override
    public Role create(Role role) {
        return repository.save(role);
    }

    @Override
    public Role read(String s) {
        return repository.findById(s).orElse(null);
    }

    @Override
    public Role update(Role role) {
        return repository.save(role);
    }

    @Override
    public boolean delete(String s) {
        repository.deleteById(s);
        return true;
    }
}
