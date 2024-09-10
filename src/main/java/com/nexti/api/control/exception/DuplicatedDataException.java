package com.nexti.api.control.exception;

public class DuplicatedDataException extends DomainException {

    public DuplicatedDataException(Error error, Throwable cause) {
        super(error, cause);
    }

    public DuplicatedDataException(Error error) {
        super(error);
    }
}
