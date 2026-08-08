package za.ac.cput.carpartmarket.Factory;

import za.ac.cput.carpartmarket.Domain.Name;
import za.ac.cput.carpartmarket.Domain.Seller;
import za.ac.cput.carpartmarket.Domain.User;
import za.ac.cput.carpartmarket.Util.Helper;

public class SellerFactory {

    public static Seller createSeller(Long userid, Name sellerName, String sellingPart) {
       if(userid == null){
           return null;
       }
       if(sellerName == null){
           return null;
       }
       if(Helper.isNullOrEmpty(sellingPart)){
           return null;
       }
       return new Seller.Builder()
               .setUserid(userid)
               .setSellerName(sellerName)
               .setSellingPart(sellingPart)
               .build();
    }
}