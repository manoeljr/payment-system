package com.manoeljr.payment.utils;

import java.security.SecureRandom;

public class GenerateCodeActivationAccountUser {

    private static final String KEY_CODE_ACCOUNT_ACTIVATION_USER = "SJFLASJFJDLKFJLKAJURONCNEDFJAJF123456789asdfghjklçpoiuyqwert";

    public static String generateRandomString(int lengthString) {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lengthString; i++) {
            int index = secureRandom.nextInt(KEY_CODE_ACCOUNT_ACTIVATION_USER.length());
            sb.append(KEY_CODE_ACCOUNT_ACTIVATION_USER.charAt(index));
        }
        return sb.toString();
    }

}
