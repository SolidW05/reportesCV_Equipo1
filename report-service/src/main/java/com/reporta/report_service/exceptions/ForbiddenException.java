package com.reporta.report_service.exceptions;

public class ForbiddenException
        extends RuntimeException {

    public ForbiddenException(String message) {
        super(message);
    }
}
