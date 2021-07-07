package tech.ubic.ed.mycomproxy.model;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.springframework.util.StreamUtils;
import tech.ubic.ed.mycomproxy.exception.BadRequestException;

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

    public static RequestDto of(HttpServletRequest request) {
        String realIpAddress = request.getHeader("X-Real-IP");
        String agent = request.getHeader("User-Agent");
        String contentType = request.getHeader("content-type");
        
        RequestDto requestDto = null;
        try {
            InputStream requestInputStream = request.getInputStream();
            byte[] body = StreamUtils.copyToByteArray(requestInputStream);
            Map<String, String> headers = getMapHeaders(request);
            String nameMethod = request.getMethod();
            requestDto = RequestDto.builder()
                .requestInputStream(requestInputStream)
                .realIpAddress(realIpAddress)
                .headers(headers)
                .body(body)
                .userAgent(agent)
                .contentType(contentType)
                .httpMethod(nameMethod.toUpperCase())
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
