package tech.ubic.ed.mycomproxy.model;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.springframework.util.StreamUtils;
import tech.ubic.ed.mycomproxy.exception.BadRequestException;

import java.io.IOException;
import java.util.Objects;

import static tech.ubic.ed.mycomproxy.utils.CommonUtils.nullsafe;


@Builder
@Getter
@Slf4j
public class ResponseDto {
    byte[] body;
    CloseableHttpResponse response;

    public static ResponseDto of(CloseableHttpResponse response) {
        HttpEntity entity = response.getEntity();
        byte[] body = null;
        
        try {
            if (Objects.nonNull(entity)) {
                body = StreamUtils.copyToByteArray(entity.getContent());
            }
        } catch (IOException ex) {
            log.info("no body response");
        }

        return ResponseDto.builder()
            .body(body)
            .response(response)
            .build();

    }
}
