package za.ac.cput.carpartmarket.DTO;

import java.util.List;

public record OrderResponseDTO(
        String reference,
        String date,
        String status,
        String buyerEmail,
        List<Item> items,
        double subtotal,
        double deliveryFee,
        double discount,
        String couponCode,
        double total,
        String paymentMethod
) {

    public record Item(
            String id,
            String name,
            double price,
            int quantity,
            String seller,
            String category,
            String imageUrl
    ) {}
}
