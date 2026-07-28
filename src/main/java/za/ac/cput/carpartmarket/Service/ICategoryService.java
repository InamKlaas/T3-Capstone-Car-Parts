package za.ac.cput.carpartmarket.Service;

import za.ac.cput.carpartmarket.Domain.Category;

public interface ICategoryService extends IService<CategoryService, Long> {
    Category create(Category category);

    CategoryService read(String string);

    Category update(Category category);
}
