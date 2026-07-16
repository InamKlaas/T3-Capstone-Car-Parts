package za.ac.cput.carpartmarket.Factory;

import za.ac.cput.carpartmarket.Domain.Buyer;
import za.ac.cput.carpartmarket.Domain.PaymentMethod;
import za.ac.cput.carpartmarket.Util.Helper;

public class PaymentMethodFactory {

    public static PaymentMethod createPaymentFactory(String methodId, Buyer buyer, String type, String provider, int cvv){
        if (Helper.isNullOrEmpty(methodId) || Helper.isNullOrEmpty(type) || Helper.isNullOrEmpty(provider))
            return null;

        if (!Helper.isValidCvv(cvv)){
            return null;
        }

        return new PaymentMethod.Builder()
                .setMethodId(methodId)
                .setBuyer(buyer)
                .setType(type)
                .setProvider(provider)
                .setcvv(cvv)
                .build();
    }
}
