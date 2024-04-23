package com.test.reservation.exception;

import lombok.Getter;

/**
 * 이미 예약되어 있는 시간대에 예약을 한 경우 발생하는 예외
 */
@Getter
public class RoomNotAvailableException extends RuntimeException {

    String message;

    public RoomNotAvailableException() {
        super();
    }

    public RoomNotAvailableException(String message) {
        super(message);
        this.message = message;
    }
}
