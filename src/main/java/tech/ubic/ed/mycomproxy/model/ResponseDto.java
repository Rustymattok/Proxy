package tech.ubic.ed.mycomproxy.model;

import lombok.Builder;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.Objects;

@Builder
@Getter
@Slf4j
public class ResponseDto {
    String body;

    @SneakyThrows
    public static ResponseDto of(CloseableHttpResponse response) {
        HttpEntity entity = response.getEntity();
        String responseString = "";
        if (Objects.nonNull(entity)) {
            responseString = EntityUtils.toString(entity, "UTF-8");
        }

        return ResponseDto.builder()
            .body(responseString)
            .build();
    }
}
