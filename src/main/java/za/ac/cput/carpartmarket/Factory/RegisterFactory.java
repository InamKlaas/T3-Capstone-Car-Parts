package za.ac.cput.carpartmarket.Factory;

import za.ac.cput.carpartmarket.Domain.Register;
import za.ac.cput.carpartmarket.Domain.User;
import za.ac.cput.carpartmarket.Util.Helper;

import java.time.LocalDate;

public class RegisterFactory {

    public static Register createRegister(String registrationId, User user, LocalDate registrationDate, String status){
        if (Helper.isNullOrEmpty(registrationId)){
            return null;
        }

        if (user == null){
            return null;
        }

        if (registrationDate == null){
            return null;
        }

        if (Helper.isNullOrEmpty(status)){
            return null;
        }

        return new Register.Builder()
                .setRegistrationId(registrationId)
                .setUser(user)
                .setRegistrationDate(registrationDate)
                .setStatus(status)
                .build();
    }
}
