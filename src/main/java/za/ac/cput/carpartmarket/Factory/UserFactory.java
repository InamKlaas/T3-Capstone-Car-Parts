package za.ac.cput.carpartmarket.Factory;

import za.ac.cput.carpartmarket.Domain.Name;
import za.ac.cput.carpartmarket.Domain.User;
import za.ac.cput.carpartmarket.Util.Helper;

public class UserFactory {

    private UserFactory() {
    }

    public static User createUser(Name name,
                                  String email,
                                  String password,
                                  String phoneNumber,
                                  String createdAt) {

        if (name == null)
            return null;

        if (Helper.isNullOrEmpty(email))
            return null;

        if (Helper.isNullOrEmpty(password))
            return null;

        if (Helper.isNullOrEmpty(createdAt))
            return null;

        Long userId = System.currentTimeMillis();

        return new User.Builder()
                .setUserid(userId)
                .setName(name)
                .setEmail(email)
                .setPassword(password)
                .setPhoneNumber(phoneNumber)
                .setCreatedAt(createdAt)
                .build();
    }
}