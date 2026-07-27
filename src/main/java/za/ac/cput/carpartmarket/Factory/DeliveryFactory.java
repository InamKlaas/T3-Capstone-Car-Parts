package za.ac.cput.carpartmarket.Factory;

import za.ac.cput.carpartmarket.Domain.Delivery;
import za.ac.cput.carpartmarket.Util.Helper;

public class DeliveryFactory {

    public static Delivery createDelivery(String courierName, String trackingNumber, String deliveryDate, String deliveryStatus) {

        if (Helper.isNullOrEmpty(courierName) || Helper.isNullOrEmpty(trackingNumber) || Helper.isNullOrEmpty(deliveryDate) || Helper.isNullOrEmpty(deliveryStatus))
            return null;

        return new Delivery.Builder()
                .setCourierName(courierName)
                .setTrackingNumber(trackingNumber)
                .setDeliveryDate(deliveryDate)
                .setDeliveryStatus(deliveryStatus)
                .build();
    }
}