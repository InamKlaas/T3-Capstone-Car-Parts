package za.ac.cput.carpartmarket.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.carpartmarket.Domain.Admin;
import za.ac.cput.carpartmarket.Repository.IAdminRepository;
import java.util.List;

@Service
public class AdminService implements IAdminService {

    private IAdminRepository repository;

    @Autowired
    public AdminService(IAdminRepository repository) {
        this.repository = repository;
    }

    @Override
    public Admin create(Admin admin) {
        return this.repository.save(admin);
    }

    @Override
    public Admin read(Long id) {
        return this.repository.findById(id).orElse(null);
    }

    @Override
    public Admin update(Admin admin) {
        return this.repository.save(admin);
    }

    @Override
    public boolean delete(Long id) {
        this.repository.deleteById(id);
        return true;
    }

    @Override
    public List<Admin> getAll() {
        return this.repository.findAll();
    }
}