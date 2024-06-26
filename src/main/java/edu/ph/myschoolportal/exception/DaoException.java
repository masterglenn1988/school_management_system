package edu.ph.myschoolportal.exception;

import lombok.Getter;

@Getter
public class DaoException extends IllegalAccessException{

    private static final long serialVersionUID = 1L;
    private final int status;

    public DaoException(String message) {
        super(message);
        this.status = 500;
    }

    public DaoException(String message, int status) {
        super(message);
        this.status = status;
    }
}
