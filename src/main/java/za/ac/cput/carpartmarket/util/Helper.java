package za.ac.cput.carpartmarket.util;

import org.apache.commons.validator.routines.EmailValidator;

public class Helper {
    public static boolean isEmptyOrNull(String str){
        if(str == null || str.isEmpty()){
            return true;
        }
        return false;
    }
    public static boolean isEmptyOrNull(Long value){
        return value == null || value <=0;
    }

    public static boolean isNotEmptyOrNull(String str){
        if (str == null || str.isEmpty()){
            return false;
        }
        return true;
    }

    public static boolean isNotEmptyOrNull(Long value){
        return value != null && value >0;
    }
    public static boolean isValidEmail(String email){
        EmailValidator emailValidator = EmailValidator.getInstance();
        if(!emailValidator.isValid(email)){
            return true;
        }
        return false;
    }


    public static boolean isValidMobile(String mobile) {
        if (mobile == null) {
            return false;
        }
        return mobile.matches("\\d{10}");
    }
}
