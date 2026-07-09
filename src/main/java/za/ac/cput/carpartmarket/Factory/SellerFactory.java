package za.ac.cput.carpartmarket.Factory;

import za.ac.cput.carpartmarket.Domain.Name;
import za.ac.cput.carpartmarket.Domain.Seller;
import za.ac.cput.carpartmarket.Domain.User;
import za.ac.cput.carpartmarket.Util.Helper;

public class SellerFactory {

    public static Seller createSeller(Long sellerId, Name sellerName, User user, String permissions) {
        if (Helper.isEmptyOrNull(sellerId)) {
            return null;
        }
        if (sellerName == null) {
            return null;
        }
        if (user == null) {
            return null;
        }
        if (Helper.isEmptyOrNull(permissions)) {
            return null;
        }

        return new Seller.Builder()
                .setSellerId(sellerId)
                .setSellerName(sellerName)
                .setUser(user)
                .setPermissions(permissions)
                .build();
    }
}
