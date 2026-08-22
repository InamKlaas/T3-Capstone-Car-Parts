package za.ac.cput.carpartmarket.Factory;

import za.ac.cput.carpartmarket.Domain.CarPart;
import za.ac.cput.carpartmarket.Util.Helper;

public class CarPartFactory {

    public static CarPart createCarPart(String carPartId, String partName, String description, Double price, int stockQuantity, String model, Long categoryId, String sellerId) {

        if (Helper.isNullOrEmpty(partName) || Helper.isNullOrEmpty(description) || Helper.isNullOrEmpty(model) || Helper.isEmptyOrNull(sellerId)){
            return null;
        }

        return new CarPart.Builder()
                .setCarPartId(carPartId)
                .setPartName(partName)
                .setDescription(description)
                .setPrice(price)
                .setStockQuantity(stockQuantity)
                .setModel(model)
                .setCategoryId(categoryId)
                .setSellerId(sellerId)
                .build();
    }
}