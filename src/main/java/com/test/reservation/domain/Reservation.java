package com.test.reservation.domain;

import com.test.reservation.dto.BookingForm;
import com.test.reservation.utils.TypeConverter;
import jakarta.persistence.*;
import lombok.Getter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "res_id")
    private Long id;

    @Column(length = 10)
    private String resName;

    @Column(nullable = false)
    private LocalDateTime resStart;

    @Column(nullable = false)
    private LocalDateTime resEnd;

    @Column(length = 2)
    private int attendee;

    @Column(length = 30, nullable = false)
    private String title;

    @Column(length = 2)
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
