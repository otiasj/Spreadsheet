package com.otiasj.easyspreadsheet.utils;

import android.text.TextUtils;

/**
 * Created by julien on 1/10/2017.
 * All rights reserved
 */
public class StringUtils {

    /**
     * @param str
     * @return true if the string is not empty and only alphanumeric
     */
    public static boolean isAlphanumeric(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        for (int i=0; i<str.length(); i++) {
            char c = str.charAt(i);
            if (!Character.isDigit(c) && !Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

}
