package com.test.reservation.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter @Setter @ToString
public class BookingForm {
    private String resName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate resDate = LocalDate.now();
    private String startTime;
    private String endTime;
    private int attendee = 1;
    private String title;
    private int price = 5;
}
