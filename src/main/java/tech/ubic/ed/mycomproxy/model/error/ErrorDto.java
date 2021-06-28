package tech.ubic.ed.mycomproxy.model.error;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorDto {
    private int code;
    private String error;
}
