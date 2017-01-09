package com.orionhealth.contacts.helpers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

/**
 * Created by irineul on 07/01/17.
 */

public class StringHelper {

    private static final String ALLOWED_CHARACTERS ="qwertyuiopasdfghjklzxcvbnm";

    public static String randomString(final int length) {
        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(length);
        for(int i=0;i<length;++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
    }

    public static boolean isValidEmail(String email) {
        if (email.isEmpty()) return false;
        else{
            String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
            java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
            java.util.regex.Matcher m = p.matcher(email);
            return m.matches();
        }
    }

    public static boolean isValidUrl(String url){
        try {
            new URL(url);
            return true;
        } catch (MalformedURLException malformedURLException) {
            return false;
        }
    }
}
