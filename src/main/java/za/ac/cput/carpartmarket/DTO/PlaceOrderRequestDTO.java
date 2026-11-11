package za.ac.cput.carpartmarket.DTO;

import java.util.List;

public record PlaceOrderRequestDTO(
        List<Line> items,
        String couponCode,
        String paymentMethod
) {
    public record Line(String carPartId, int quantity){

    }
}
