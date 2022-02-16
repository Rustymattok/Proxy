package my.makarov.prox.mycomproxy.model.error;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorDto {
     int code;
     String error;
}
