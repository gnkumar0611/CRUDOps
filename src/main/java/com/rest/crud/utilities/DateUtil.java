package com.rest.crud.utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DateUtil {

    private static final List<DateTimeFormatter> formatters = new ArrayList<>();


    public static LocalDateTime convertToDateTime(String date) {
        formatters.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        formatters.add(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        formatters.add(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss"));
        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDateTime.parse(date, formatter);
            } catch (Exception e) {
                // Continue to try the next pattern
            }
        }
        throw new IllegalArgumentException("Invalid date format: " + date);
    }
}
