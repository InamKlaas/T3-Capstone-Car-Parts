package za.ac.cput.carpartmarket.Factory;

import za.ac.cput.carpartmarket.Util.Helper;

public class DeliveryFactory {

    private DeliveryFactory() {
    }

    public static Delivery createDelivery(String courierName,
                                          String trackingNumber,
                                          String deliveryDate,
                                          String deliveryStatus) {


        if (Helper.isNullOrEmpty(courierName))
            return null;

        if (Helper.isNullOrEmpty(trackingNumber))
            return null;

        if (Helper.isNullOrEmpty(deliveryDate))
            return null;

        if (Helper.isNullOrEmpty(deliveryStatus))
            return null;


        return new Delivery.Builder()
                .setCourierName(courierName)
                .setTrackingNumber(trackingNumber)
                .setDeliveryDate(deliveryDate)
                .setDeliveryStatus(deliveryStatus)
                .build();
    }
}

