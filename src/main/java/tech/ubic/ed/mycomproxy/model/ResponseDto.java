package tech.ubic.ed.mycomproxy.model;

import com.google.protobuf.InvalidProtocolBufferException;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.springframework.util.StreamUtils;
import tech.ubic.ed.mycomproxy.TrackerSDK;
import tech.ubic.ed.mycomproxy.exception.BadRequestException;
import tech.ubic.ed.mycomproxy.utils.ProtoJsonUtil;

import java.io.IOException;
import java.util.Objects;

import static tech.ubic.ed.mycomproxy.utils.CommonUtils.nullsafe;


@Builder
@Getter
@Slf4j
public class ResponseDto {
    byte[] body;
    String json;
    CloseableHttpResponse response;

    public static ResponseDto of(CloseableHttpResponse response) {
        HttpEntity entity = response.getEntity();
        byte[] body = null;
        String json = "";
        try {
            if (Objects.nonNull(entity)) {
                try {
                    log.info("------------- END PROTO --------------");

                    TrackerSDK.MyTrackerSDK sdk = TrackerSDK.MyTrackerSDK.parseFrom(body);
                    json = ProtoJsonUtil.toJson(sdk);
//                json = printer.print(TrackerSDK.MyTrackerSDK.newBuilder().mergeFrom(body).build());
                    log.info(json);
                } catch (InvalidProtocolBufferException e) {
                    log.info(e.getMessage());
                }
                body = StreamUtils.copyToByteArray(entity.getContent());
            }
        } catch (IOException ex) {
            log.info("no body response");
        }

        return ResponseDto.builder()
            .body(body)
            .json(json)
            .response(response)
            .build();

    }
}
