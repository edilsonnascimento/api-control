package com.nexti.api.control.util;

import java.text.*;
import java.time.LocalDate;
import java.util.Date;

public class ConvertUtil {

    public static Date convertLocalDateToDate(LocalDate refDate) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(refDate.toString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}