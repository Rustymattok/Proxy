package tech.ubic.ed.mycomproxy.exception;

public class TrackerException extends RuntimeException {
    public TrackerException(String message) {
        super(message);
    }

    public TrackerException(String message, Throwable cause) {
        super(message, cause);
    }
}
