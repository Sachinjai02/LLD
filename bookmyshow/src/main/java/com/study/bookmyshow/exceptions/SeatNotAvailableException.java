package com.study.bookmyshow.exceptions;

public class SeatNotAvailableException extends RuntimeException {
    public SeatNotAvailableException(String s) {
        super(s);
    }
}
