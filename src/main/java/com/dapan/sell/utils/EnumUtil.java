package com.dapan.sell.utils;

import com.dapan.sell.enums.CodeEnum;
import com.dapan.sell.enums.CodeEnumEx;

public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }

    public static <T extends CodeEnumEx> T getByMessage(String message, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (message.equalsIgnoreCase(each.getMessage())) {
                return each;
            }
        }
        return null;
    }
}
