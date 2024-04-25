package com.test.reservation.controller;

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

    /**
     * 예약 데이터 저장
     * @param bookingForm
     * @return
     */
    @ResponseBody
    @PostMapping("/book")
    public SavedReservation book(@ModelAttribute BookingForm bookingForm) {
        log.info("resForm={}", bookingForm);
        return reservationService.save(bookingForm);
    }

    /**
     * 메인 페이지 로딩 시 데이터 초기화
     * @return DB에 저장된 예약 데이터
     */
    @ResponseBody
    @PostMapping("/init")
    public List<SavedReservation> initData() {
        return reservationService.findAll();
    }

    /**
     * 입력된 시간에 이미 예약이 있을 경우 발생하는 예외
     * @param e
     * @return 예외 메시지
     */
    @ResponseBody
    @ExceptionHandler(RoomNotAvailableException.class)
    public String handleRoomNotAvailableException(RoomNotAvailableException e) {
        return e.getMessage();
    }
}
