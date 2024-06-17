package com.inventario.covid.config;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Random;

@Component
public class Utils {
    private final Random random = new SecureRandom();

    public String generateUsername(String firstName, String lastName){
        String[] nameParts = lastName.split(" ");
        String s = nameParts[0].toLowerCase() + firstName.toLowerCase().charAt(0) + random.nextInt(10_000);
        return s;
    }

    public String generatePassword(int length){
        StringBuilder returnValue = new StringBuilder(length);
        for (int i = 0; i<length; i++){
            String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%&";
            returnValue.append(chars.charAt(random.nextInt(chars.length())));
        }
        return returnValue.toString();
    }

    public String GetCurrentDateFormatted(){
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(System.currentTimeMillis());
    }
}
