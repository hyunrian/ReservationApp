package com.test.reservation.service;

import com.test.reservation.domain.Reservation;
import com.test.reservation.dto.BookingForm;
import com.test.reservation.dto.SavedReservation;
import com.test.reservation.exception.RoomNotAvailableException;
import com.test.reservation.repository.ReservationQueryRepository;
import com.test.reservation.repository.ReservationRepository;
import com.test.reservation.utils.TypeConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationQueryRepository queryRepository;
    private final TypeConverter typeConverter;

    @Transactional
    public SavedReservation save(BookingForm bookingForm) {
        LocalDateTime startTime = typeConverter.dateAndTimeToLocalDateTime(
                bookingForm.getResDate(), bookingForm.getStartTime());
        LocalDateTime endTime = typeConverter.dateAndTimeToLocalDateTime(
                bookingForm.getResDate(), bookingForm.getEndTime());

        boolean isRoomAvailable = queryRepository.isRoomAvailable(startTime, endTime);

        if (isRoomAvailable) {
            Reservation reservation = new Reservation().getBookedInfo(bookingForm, typeConverter);
            reservationRepository.save(reservation);
            return findLast(reservation);
        } else {
            throw new RoomNotAvailableException("선택한 시간에는 예약이 불가능합니다.");
        }
    }

    @Transactional(readOnly = true)
    public List<SavedReservation> findAll() {
        List<Reservation> list = reservationRepository.findAll();
        log.info("savedList.size()=", list.size());
        List<SavedReservation> data = new ArrayList<>();
        for (Reservation reservation : list) {
            SavedReservation savedReservation = new SavedReservation();
            savedReservation.setTitle(reservation.getTitle());
            savedReservation.setStartTime(reservation.getResStart().toString());
            savedReservation.setEndTime(reservation.getResEnd().toString());
            data.add(savedReservation);
        }
        return data;
    }

    private SavedReservation findLast(Reservation reservation) {
        SavedReservation savedReservation = new SavedReservation();
        savedReservation.setTitle(reservation.getTitle());
        savedReservation.setStartTime(reservation.getResStart().toString());
        savedReservation.setEndTime(reservation.getResEnd().toString());
        return savedReservation;
    }
}
