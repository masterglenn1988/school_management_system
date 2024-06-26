package edu.ph.myschoolportal.exception;

import lombok.Getter;

@Getter
public class ServiceException extends IllegalAccessException{

    private static final long serialVersionUID = 1L;
    private final int status;

    public ServiceException(String message) {
        super(message);
        this.status = 500;
    }

    public ServiceException(String message, int status) {
        super(message);
        this.status = status;
    }
}