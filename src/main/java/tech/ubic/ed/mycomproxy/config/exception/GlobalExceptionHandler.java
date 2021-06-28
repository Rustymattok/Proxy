package tech.ubic.ed.mycomproxy.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tech.ubic.ed.mycomproxy.exception.BadRequestException;
import tech.ubic.ed.mycomproxy.model.error.ErrorDto;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<ErrorDto> notFound(BadRequestException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ErrorDto.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .error(ex.getMessage())
                .build());
    }
}
