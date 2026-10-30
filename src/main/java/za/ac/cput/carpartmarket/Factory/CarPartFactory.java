package za.ac.cput.carpartmarket.Factory;

import za.ac.cput.carpartmarket.Domain.CarPart;
import za.ac.cput.carpartmarket.Util.Helper;

import java.util.List;

public class CarPartFactory {

    public static final List<String> VALID_CONDITIONS = List.of("New", "Like New", "Used", "Fair");

    public static CarPart createCarPart(String carPartId, String partName, String description, Double price, int stockQuantity, String model, Long categoryId, String sellerId, String status, String image, String partCondition) {

        if (Helper.isNullOrEmpty(partName) || Helper.isNullOrEmpty(description) || Helper.isNullOrEmpty(model) || Helper.isEmptyOrNull(sellerId)){
            return null;
        }

        if (partCondition == null || !VALID_CONDITIONS.contains(partCondition)){
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
                .setStatus(status)
                .setPartCondition(partCondition)
                .setImage(image)
                .build();
    }
}