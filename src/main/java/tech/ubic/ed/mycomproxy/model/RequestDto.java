package tech.ubic.ed.mycomproxy.model;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

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
    String body;
    String realIpAddress;
    Map<String, String> headers;

    public static RequestDto of(HttpServletRequest request) {
        String realIpAddress = request.getHeader("X-Real-IP");
        InputStream requestInputStream = null;
        String body = "";

        try {
            requestInputStream = request.getInputStream();
            body = IOUtils.toString(requestInputStream);
        } catch (IOException e) {
            log.info("request was bad not possible to read ");
        }

        Map<String, String> headers = getMapHeaders(request);

        return RequestDto.builder()
            .requestInputStream(requestInputStream)
            .realIpAddress(realIpAddress)
            .headers(headers)
            .body(body)
            .build();
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
