package com.test.reservation.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.test.reservation.domain.QReservation;
import com.test.reservation.domain.Reservation;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.test.reservation.domain.QReservation.reservation;

@Repository
public class ReservationQueryRepository {

    private final JPAQueryFactory query;

    public ReservationQueryRepository(EntityManager em) {
        query = new JPAQueryFactory(em);
    }

    public boolean isRoomAvailable(LocalDateTime startDate, LocalDateTime endDate) {

//        LocalDateTime startDate = LocalDateTime.parse(resStart);
//        LocalDateTime endDate = LocalDateTime.parse(resEnd);

        List<Reservation> overlappingReservations = query
                .selectFrom(reservation)
                .where(reservation.resStart.lt(endDate)
                        .and(reservation.resEnd.gt(startDate))
                        .or(reservation.resStart.goe(startDate).and(reservation.resStart.lt(endDate)))
                        .or(reservation.resEnd.gt(startDate).and(reservation.resEnd.lt(endDate))))
                .fetch();

        // 겹치는 예약이 없으면 회의실 예약 가능
        return overlappingReservations.isEmpty();
    }
}
