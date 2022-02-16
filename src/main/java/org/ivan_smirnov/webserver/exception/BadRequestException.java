package org.ivan_smirnov.webserver.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException() {
    }

    public BadRequestException(Exception e) {
        super(e);
    }
}
