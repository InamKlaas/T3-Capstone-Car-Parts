package za.ac.cput.carpartmarket.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.carpartmarket.Domain.Category;
import za.ac.cput.carpartmarket.Repository.ICategoryRepository;

@Service
public class CategoryService implements ICategoryService{

    @Autowired
    private ICategoryRepository repository;

    @Override
    public Category create(Category category) {
        return repository.save(category);
    }

    @Override
    public CategoryService create(CategoryService categoryService) {
        return null;
    }

    @Override
    public CategoryService read(String string) {
        return repository.findById(String).orElse(null);
    }

    @Override
    public CategoryService update(CategoryService categoryService) {
        return null;
    }

    @Override
    public Category update(Category category) {
        return repository.save(category);
    }

    @Override
    public boolean delete(Long l) {
        repository.deleteById(String.valueOf(l));
        return true;
    }
}
