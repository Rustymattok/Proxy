package my.makarov.prox.mycomproxy.exception;

public class TrackerException extends RuntimeException {
    public TrackerException(String message) {
        super(message);
    }

    public TrackerException(String message, Throwable cause) {
        super(message, cause);
    }
}
