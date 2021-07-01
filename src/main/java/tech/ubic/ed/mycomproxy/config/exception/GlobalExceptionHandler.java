package tech.ubic.ed.mycomproxy.config.exception;

import org.apache.http.client.ClientProtocolException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tech.ubic.ed.mycomproxy.exception.BadRequestException;
import tech.ubic.ed.mycomproxy.exception.TrackerException;
import tech.ubic.ed.mycomproxy.model.error.ErrorDto;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<ErrorDto> badRequest(BadRequestException ex) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ErrorDto.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(ex.getMessage())
                .build());
    }

    @ExceptionHandler(TrackerException.class)
    protected ResponseEntity<ErrorDto> notAvailable(BadRequestException ex) {

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
    
    
}
