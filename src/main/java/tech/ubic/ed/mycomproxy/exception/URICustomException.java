package tech.ubic.ed.mycomproxy.exception;

public class URICustomException extends RuntimeException {
    public URICustomException(String message) {
        super(message);
    }

    public URICustomException(String message, Throwable cause) {
        super(message, cause);
    }
}
