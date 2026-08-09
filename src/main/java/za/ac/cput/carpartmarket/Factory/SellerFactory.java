package za.ac.cput.carpartmarket.Factory;

import za.ac.cput.carpartmarket.Domain.Name;
import za.ac.cput.carpartmarket.Domain.Seller;
import za.ac.cput.carpartmarket.Util.Helper;

public class SellerFactory {

    public static Seller createSeller(
            Long sellerId,
            Name sellerName,
            String sellingPart) {

        if (sellerId == null) {
            return null;
        }

        if (sellerName == null) {
            return null;
        }

        if (Helper.isNullOrEmpty(sellingPart)) {
            return null;
        }

        return new Seller.Builder()
                .setSellerId(sellerId)
                .setSellerName(sellerName)
                .setSellingPart(sellingPart)
                .build();
    }
}
