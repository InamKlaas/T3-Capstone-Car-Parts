package za.ac.cput.carpartmarket.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.carpartmarket.Domain.Category;
import za.ac.cput.carpartmarket.Repository.ICategoryRepository;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryRepository repository;

    @Override
    public Category create(Category category) {
        return repository.save(category);
    }

    @Override
    public Category read(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Category update(Category category) {
        return repository.save(category);
    }

    @Override
    public boolean delete(String id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public List<Category> getAll() {
        return repository.findAll();
    }
}