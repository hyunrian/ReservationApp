package com.test.reservation.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SavedReservation {
    private String title;
    private String startTime;
    private String endTime;
}
