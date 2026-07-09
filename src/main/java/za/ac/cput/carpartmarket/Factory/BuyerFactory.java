package za.ac.cput.carpartmarket.Factory;

import za.ac.cput.carpartmarket.Domain.Buyer;
import za.ac.cput.carpartmarket.Domain.Name;
import za.ac.cput.carpartmarket.Domain.User;
import za.ac.cput.carpartmarket.Util.Helper;

public class BuyerFactory {

    public static Buyer createBuyer(Long buyerId, Name buyerName, User user) {
        if (Helper.isEmptyOrNull(buyerId)) {
            return null;
        }
        if (buyerName == null) {
            return null;
        }
        if (user == null) {
            return null;
        }

        return new Buyer.Builder()
                .setBuyerId(buyerId)
                .setBuyerName(buyerName)
                .setUser(user)
                .build();
    }
}