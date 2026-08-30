package za.ac.cput.carpartmarket.Factory;

import za.ac.cput.carpartmarket.Domain.Buyer;
import za.ac.cput.carpartmarket.Domain.Register;
import java.time.LocalDate;

public class RegisterFactory {

    public static Register createRegister(String registrationId, Buyer user,
                                          LocalDate registrationDate, String status) {
        if (registrationId == null || registrationId.isEmpty() ||
                user == null ||
                registrationDate == null ||
                status == null || status.isEmpty()) {
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
