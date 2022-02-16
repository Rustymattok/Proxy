package my.makarov.prox.mycomproxy.exception;

public class NoZipException extends RuntimeException {

    public NoZipException(String message) {
        super(message);
    }

    public NoZipException(String message, Throwable cause) {
        super(message, cause);
    }
}
