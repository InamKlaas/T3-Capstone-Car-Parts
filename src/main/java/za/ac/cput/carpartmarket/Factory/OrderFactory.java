package za.ac.cput.carpartmarket.Factory;

import za.ac.cput.carpartmarket.Domain.Buyer;
import za.ac.cput.carpartmarket.Domain.Order;
import za.ac.cput.carpartmarket.Util.Helper;

import java.time.LocalDateTime;

public class OrderFactory {

    public static Order createOrder(Long orderId, Buyer buyer, String status, LocalDateTime orderDate, double totalAmount,Long deliveryId) {

        if(Helper.isEmptyOrNull(orderId)){
            return null;
        }
        if (buyer == null){
            return null;
        }
        if (Helper.isEmptyOrNull(status)){
            return null;
        }
        if (orderDate == null){
            return null;
        }
        if (totalAmount<=0){
            return null;
        }
        if(Helper.isEmptyOrNull(deliveryId)){
            return null;
        }


        return new Order.Builder()
                .setOrderId(orderId)
                .setOrderDate(orderDate)
                .setStatus(status)
                .setDeliveryId(deliveryId)
                .setBuyer(buyer)
                .setTotalAmount(totalAmount)
                .build();



    }
}
