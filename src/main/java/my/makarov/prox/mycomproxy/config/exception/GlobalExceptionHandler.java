package my.makarov.prox.mycomproxy.config.exception;

import org.apache.http.client.ClientProtocolException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import my.makarov.prox.mycomproxy.exception.BadRequestException;
import my.makarov.prox.mycomproxy.exception.NoZipException;
import my.makarov.prox.mycomproxy.exception.TrackerException;
import my.makarov.prox.mycomproxy.exception.URICustomException;
import my.makarov.prox.mycomproxy.model.error.ErrorDto;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<ErrorDto> badRequest(BadRequestException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ErrorDto.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .error(ex.getMessage())
                .build());
    }

    @ExceptionHandler(NoZipException.class)
    protected ResponseEntity<ErrorDto> badZip(NoZipException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ErrorDto.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .error(ex.getMessage())
                .build());
    }

    @ExceptionHandler(TrackerException.class)
    protected ResponseEntity<ErrorDto> notAvailable(TrackerException ex) {

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
            .body(ErrorDto.builder()
                .code(HttpStatus.SERVICE_UNAVAILABLE.value())
                .error(ex.getMessage())
                .build());
    }
    
    @ExceptionHandler(ClientProtocolException.class)
    protected ResponseEntity<ErrorDto> notFound(BadRequestException ex) {

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
            .body(ErrorDto.builder()
                .code(HttpStatus.SERVICE_UNAVAILABLE.value())
                .error(ex.getMessage())
                .build());
    }

    @ExceptionHandler(URICustomException.class)
    protected ResponseEntity<ErrorDto> badUri(URICustomException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ErrorDto.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .error(ex.getMessage())
                .build());
    }
    
    
}
