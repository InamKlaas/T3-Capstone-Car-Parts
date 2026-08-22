package za.ac.cput.carpartmarket.Factory;

import za.ac.cput.carpartmarket.Domain.Buyer;
import za.ac.cput.carpartmarket.Domain.Name;
import za.ac.cput.carpartmarket.Util.Helper;

public class BuyerFactory {

    public static Buyer createBuyer(String userid, Name buyerName, String buyingPart) {
        if (userid == null){
            return null;
        }

        if(buyerName == null){
            return null;
        }
        if(Helper.isNullOrEmpty(buyingPart)){
            return null;
        }
        return new Buyer.Builder()
                .setUserid(userid)
                .setBuyerName(buyerName)
                .setBuyingPart(buyingPart)
                .build();
    }
}