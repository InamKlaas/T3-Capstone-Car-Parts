package za.ac.cput.carpartmarket.Service;

import za.ac.cput.carpartmarket.Domain.Category;

import java.util.List;

public interface ICategoryService extends IService<Category,String> {

    Category create(Category category);

    Category read(String id);

    Category update(Category category);

    boolean delete(String id);

    List<Category> getAll();
}