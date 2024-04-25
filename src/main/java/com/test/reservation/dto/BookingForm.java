package com.test.reservation.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class BookingForm {

    @Max(10)
    private String resName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate resDate = LocalDate.now();

    @NotBlank
    private String startTime;

    @NotBlank
    private String endTime;

    @Size(min = 1, max = 30)
    private int attendee = 1;

    @NotBlank
    @Size(min = 1, max = 30)
    private String title;

    private int price;
}
