package za.ac.cput.carpartmarket.Factory;

import za.ac.cput.carpartmarket.Domain.Address;
import za.ac.cput.carpartmarket.Util.Helper;

public class AddressFactory {

    public static Address createAddress(String streetNumber, Long userId, String suburb, String city, String province, int postalCode, String country){
        if (Helper.isNullOrEmpty(streetNumber) || Helper.isEmptyOrNull(userId) || Helper.isNullOrEmpty(suburb) || Helper.isNullOrEmpty(city) || Helper.isNullOrEmpty(province) || Helper.isNullOrEmpty(country))
            return null;

        if (!Helper.isValidPostalCode(postalCode)){
            return null;
        }

        return new Address.Builder()
                .setStreetNumber(streetNumber)
                .setUserId(userId)
                .setSuburb(suburb)
                .setCity(city)
                .setProvince(province)
                .setPostalCode(postalCode)
                .setCountry(country)
                .build();
    }
}
