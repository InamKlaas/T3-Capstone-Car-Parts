package za.ac.cput.carpartmarket.Factory;

import za.ac.cput.carpartmarket.Domain.OrderItem;
import za.ac.cput.carpartmarket.Util.Helper;

public class OrderItemFactory {

    public static OrderItem createOrderItem(Long orderItemId, Long orderId, Long partId,
                                            String quantity, Double unitPrice, Double subTotal) {
        if (Helper.isEmptyOrNull(orderItemId)) {
            return null;
        }
        if (Helper.isEmptyOrNull(orderId)) {
            return null;
        }
        if (Helper.isEmptyOrNull(partId)) {
            return null;
        }
        if (Helper.isEmptyOrNull(quantity)) {
            return null;
        }
        if (unitPrice == null || unitPrice <= 0) {
            return null;
        }
        if (subTotal == null || subTotal <= 0) {
            return null;
        }

        return new OrderItem.Builder()
                .setOrderItemId(orderItemId)
                .setOrderId(orderId)
                .setPartId(partId)
                .setQuantity(quantity)
                .setUnitPrice(unitPrice)
                .setSubTotal(subTotal)
                .build();
    }
}
