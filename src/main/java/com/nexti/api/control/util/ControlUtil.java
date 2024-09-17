package com.nexti.api.control.util;

import java.util.*;

public class ControlUtil {

    public static <T> boolean arrayIsEmpty(Collection<T> array) {
        return array == null || array.isEmpty();
    }

    public static boolean arraysIsEmptyOrNull(Object... values) {
        return Objects.isNull(values) || Arrays.stream(values).allMatch(ControlUtil::isNullOrEmpty);
    }

    private static boolean isNullOrEmpty(Object value) {
        return Objects.isNull(value)
               || value.equals("null")
               || (value instanceof String && ((String) value).trim().isEmpty());
    }
}
