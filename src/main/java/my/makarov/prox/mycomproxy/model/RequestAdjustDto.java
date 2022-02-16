package my.makarov.prox.mycomproxy.model;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;
import my.makarov.prox.mycomproxy.config.cashInputStream.CachedBodyHttpServletRequest;
import my.makarov.prox.mycomproxy.exception.BadRequestException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Builder
@Getter
@Slf4j
public class RequestAdjustDto {
    String realIpAddress;
    InputStream bodyStream;
    Map<String, String> headers;
    String httpMethod;
    String userAgent;
    String contentType;
    String path;
    String query;
    String json;
    String uri;


    public static RequestAdjustDto of(CachedBodyHttpServletRequest request) {
        String realIpAddress = request.getHeader("X-Real-IP");
        String agent = request.getHeader("User-Agent");
        String contentType = request.getHeader("content-type");
        String path = request.getServletPath();
        String query = request.getQueryString();
        String uri = request.getRequestURI();

        RequestAdjustDto requestDto = null;
        try {
            InputStream inputStream = request.getInputStream();
            String json = getContent(request);
            Map<String, String> headers = getMapHeaders(request);
            String nameMethod = request.getMethod();

            requestDto = RequestAdjustDto.builder()
                .realIpAddress(realIpAddress)
                .headers(headers)
                .bodyStream(inputStream)
                .json(json)
                .path(path)
                .userAgent(agent)
                .contentType(contentType)
                .httpMethod(nameMethod.toUpperCase())
                .query(query)
                .uri(uri)
                .build();
        
        } catch (IOException ex) {
            throw new BadRequestException("bad request to send adjust", ex);
        }
        
        return requestDto;
    }
    
    public static String getContent(CachedBodyHttpServletRequest request){
        String content = "";
        try {
            content = new String(StreamUtils.copyToByteArray(request.getInputStream()));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        
        return content;
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
