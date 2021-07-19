package tech.ubic.ed.mycomproxy.model;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;
import tech.ubic.ed.mycomproxy.exception.BadRequestException;
import tech.ubic.ed.mycomproxy.proto.MyTrackerSDK;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Builder
@Getter
@Slf4j
public class RequestDto {
    InputStream requestInputStream;
    byte[] body;
    String realIpAddress;
    Map<String, String> headers;
    String httpMethod;
    String userAgent;
    String contentType;
    String path;
    String query;
    String json;


    public static RequestDto of(HttpServletRequest request) {
        String realIpAddress = request.getHeader("X-Real-IP");
        String agent = request.getHeader("User-Agent");
        String contentType = request.getHeader("content-type");
        String path = request.getServletPath();
        String query = request.getQueryString();

        RequestDto requestDto = null;
        try {
            InputStream requestInputStream = request.getInputStream();
//            byte[] body = StreamUtils.copyToByteArray(requestInputStream);
//            byte[] metricBody = Base64.getEncoder().encode(body);
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] body = decoder.decodeBuffer(requestInputStream);

            Map<String, String> headers = getMapHeaders(request);
            String nameMethod = request.getMethod();
            String json = "";
            try {
                log.info("------------- start PROTO --------------");
                MyTrackerSDK mailTracker = MyTrackerSDK.parseFrom(requestInputStream);
//                MyTrackerSDKOuterClass.MyTrackerSDK trackerProto = MyTrackerSDKOuterClass.MyTrackerSDK.parseFrom(decodedBytes);
                log.info("------ JSON PARSE -------");
                json = JsonFormat.printer().print(mailTracker);
//                json = JsonFormat.printer().print(trackerProto);
//                json = ProtoJsonUtil.toJson(trackerProto);
                log.info("------ JSON TEXT -------");
                log.info(json);
            } catch (InvalidProtocolBufferException e) {
                log.info("------------- FAILED PROTO --------------");

                log.info(e.getMessage());
            }

            requestDto = RequestDto.builder()
                .requestInputStream(requestInputStream)
                .realIpAddress(realIpAddress)
                .headers(headers)
                .json(json)
                .body(body)
                .path(path)
                .userAgent(agent)
                .contentType(contentType)
                .httpMethod(nameMethod.toUpperCase())
                .query(query)
                .build();
        } catch (IOException ex) {
            throw new BadRequestException("cant send request metric to clickhouse", ex);
        }

        return requestDto;
    }

    protected static Map<String, String> getMapHeaders(HttpServletRequest request) {
        Map<String, String> headers = new HashMap<>();
        Enumeration en = request.getHeaderNames();

        while (en.hasMoreElements()) {
            String headerName = (String) en.nextElement();
            String headerValue = request.getHeader(headerName);

            headers.put(headerName, headerValue);
        }

        return headers;
    }


}
