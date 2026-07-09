package za.ac.cput.carpartmarket.Repository;

import za.ac.cput.carpartmarket.Domain.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepository implements ICategoryRepository {

    private static CategoryRepository repository = null;

    private final List<Category> categoryList;

    private CategoryRepository() {
        categoryList = new ArrayList<>();
    }

    public static CategoryRepository getRepository() {

        if (repository == null) {
            repository = new CategoryRepository();
        }

        return repository;
    }

    @Override
    public Category create(Category category) {

        categoryList.add(category);

        return category;
    }

    @Override
    public Category read(String categoryId) {

        for (Category category : categoryList) {

            if (category.getCategoryId().equals(categoryId)) {
                return category;
            }

        }

        return null;
    }

    @Override
    public Category update(Category category) {

        Category oldCategory = read(category.getCategoryId());

        if (oldCategory != null) {

            categoryList.remove(oldCategory);
            categoryList.add(category);

            return category;
        }

        return null;
    }

    @Override
    public boolean delete(String categoryId) {

        Category category = read(categoryId);

        if (category != null) {

            categoryList.remove(category);
            return true;
        }

        return false;
    }

    @Override
    public List<Category> getAll() {

        return categoryList;
    }
}