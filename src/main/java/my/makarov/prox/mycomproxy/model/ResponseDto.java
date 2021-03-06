package my.makarov.prox.mycomproxy.model;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;


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
                InputStream bodyStream = entity.getContent();
                body = StreamUtils.copyToByteArray(bodyStream);
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
