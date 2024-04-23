package com.test.reservation.controller;

import com.test.reservation.domain.Reservation;
import com.test.reservation.dto.BookingForm;
import com.test.reservation.dto.SavedReservation;
import com.test.reservation.exception.RoomNotAvailableException;
import com.test.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("bookingForm", new BookingForm());
        return "reservation";
    }

    @ResponseBody
    @PostMapping("/book")
    public SavedReservation book(@ModelAttribute BookingForm bookingForm) {
        log.info("resForm={}", bookingForm);
        return reservationService.save(bookingForm);
    }

    @ResponseBody
    @ExceptionHandler(RoomNotAvailableException.class)
    public String handleRoomNotAvailableException(RoomNotAvailableException e) {
        return e.getMessage();
    }
}
