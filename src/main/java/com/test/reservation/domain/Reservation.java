package com.test.reservation.domain;

import com.test.reservation.dto.BookingForm;
import com.test.reservation.utils.TypeConverter;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "res_id")
    private Long id;

    private String resName;

    private LocalDateTime resStart;

    private LocalDateTime resEnd;

    private int attendee;

    private String title;

    private int price;


    /**
     * 비즈니스 로직
     */
    public Reservation getBookedInfo(BookingForm bookingForm, TypeConverter typeConverter) {
        this.resName = bookingForm.getResName();
        LocalDate resDate = bookingForm.getResDate();
        this.resStart = typeConverter.dateAndTimeToLocalDateTime(
                resDate, bookingForm.getStartTime());
        this.resEnd = typeConverter.dateAndTimeToLocalDateTime(
                resDate, bookingForm.getEndTime());
        this.title = bookingForm.getTitle();
        this.attendee = bookingForm.getAttendee();
        this.price = bookingForm.getPrice();
        return this;
    }

}
