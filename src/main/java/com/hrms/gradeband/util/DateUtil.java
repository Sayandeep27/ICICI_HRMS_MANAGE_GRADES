package com.hrms.gradeband.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static LocalDate parse(String date){

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MMM-yyyy");

        return LocalDate.parse(date,formatter);
    }

}