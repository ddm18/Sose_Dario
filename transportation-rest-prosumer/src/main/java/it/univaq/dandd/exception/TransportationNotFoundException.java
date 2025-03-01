package it.univaq.dandd.exception;

public class TransportationNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -1234567890123456789L;

    public TransportationNotFoundException() {
    }

    public TransportationNotFoundException(String message) {
        super(message);
    }

    public TransportationNotFoundException(Throwable cause) {
        super(cause);
    }

    public TransportationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransportationNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                           boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
