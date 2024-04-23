package com.test.reservation.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class TypeConverter {

    StringBuilder sb;

    public LocalDateTime dateAndTimeToLocalDateTime(LocalDate resDate, String time) {
        sb = new StringBuilder();
        String dateTime = sb.append(resDate).append("T").append(time).toString();
        return LocalDateTime.parse(dateTime);
    }

    public String titleAndNameToTitle(String resName, String title) {
        sb = new StringBuilder();
        return sb.append(title)
                .append("(")
                .append(resName)
                .append(")")
                .toString();
    }
}
