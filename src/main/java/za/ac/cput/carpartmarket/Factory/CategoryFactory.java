package za.ac.cput.carpartmarket.Factory;

import za.ac.cput.carpartmarket.Domain.Category;
import za.ac.cput.carpartmarket.Util.Helper;

public class CategoryFactory {

    private CategoryFactory() {
    }

    public static Category createCategory(String categoryName,
                                          String description) {

        if (Helper.isNullOrEmpty(categoryName))
            return null;

        if (Helper.isNullOrEmpty(description))
            return null;

        String categoryId = Helper.generateId();

        return new Category.Builder()
                .setCategoryId(categoryId)
                .setCategoryName(categoryName)
                .setDescription(description)
                .build();
    }
}