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

    public static ResponseDto of(CloseableHttpResponse response) {
        HttpEntity entity = response.getEntity();
        ResponseDto responseDto = null;
        try {
            if (Objects.nonNull(entity)) {
                byte[] body = StreamUtils.copyToByteArray(entity.getContent());
                responseDto = ResponseDto.builder().body(body).build();
            }
        } catch (IOException ex) {
            throw new BadRequestException("cant send response metric to clickhouse", ex);
        }

        return responseDto;

    }
}
