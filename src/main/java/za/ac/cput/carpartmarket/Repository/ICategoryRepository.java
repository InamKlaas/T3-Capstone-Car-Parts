package za.ac.cput.carpartmarket.Repository;

import za.ac.cput.carpartmarket.Domain.Category;

import java.util.List;

public interface ICategoryRepository {

    Category create(Category category);

    Category read(String categoryId);

    Category update(Category category);

    boolean delete(String categoryId);

    List<Category> getAll();
}