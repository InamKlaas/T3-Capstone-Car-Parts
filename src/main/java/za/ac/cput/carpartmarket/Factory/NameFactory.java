package za.ac.cput.carpartmarket.Factory;

import za.ac.cput.carpartmarket.Domain.Name;
import za.ac.cput.carpartmarket.Util.Helper;

public class NameFactory {

    private NameFactory() {
    }

    public static Name createName(String firstName, String lastName) {

        if (Helper.isNullOrEmpty(firstName))
            return null;

        if (Helper.isNullOrEmpty(lastName))
            return null;

        return new Name.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .build();
    }
}