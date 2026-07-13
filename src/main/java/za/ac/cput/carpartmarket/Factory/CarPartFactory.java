package za.ac.cput.carpartmarket.Factory;

import za.ac.cput.carpartmarket.Util.Helper;

public class CarPartFactory {

    public static CarPart createCarPart(String partName,
                                        String description,
                                        String model,
                                        Long sellerId) {

        if (Helper.isNullOrEmpty(partName))
            return null;

        if (Helper.isNullOrEmpty(description))
            return null;

        if (Helper.isNullOrEmpty(model))
            return null;


        if (Helper.isEmptyOrNull(sellerId))
            return null;

        return new CarPart.Builder()
                .setPartName(partName)
                .setDescription(description)
                .setModel(model)
                .setSellerId(sellerId)
                .build();
    }
}
