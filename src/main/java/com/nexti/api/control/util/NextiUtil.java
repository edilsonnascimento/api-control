package com.nexti.api.control.util;

import java.text.*;
import java.time.LocalDate;
import java.util.*;

public class NextiUtil {

    public static Date convertLocalDateToDate(LocalDate refDate) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(refDate.toString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> boolean arrayIsEmpty(Collection<T> array) {
        return (Objects.isNull(array) || array.isEmpty() );
    }

    public static boolean nonNull(Object... objects) {
        return Arrays.stream(objects).anyMatch(Objects::nonNull);
    }
}