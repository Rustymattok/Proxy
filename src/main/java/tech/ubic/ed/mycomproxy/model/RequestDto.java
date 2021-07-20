package tech.ubic.ed.mycomproxy.model;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;
import tech.ubic.ed.mycomproxy.exception.BadRequestException;
import tech.ubic.ed.mycomproxy.exception.NoZipException;
import tech.ubic.ed.mycomproxy.proto.MyTrackerSDK;
import tech.ubic.ed.mycomproxy.utils.ProtoJsonUtil;
import org.apache.commons.codec.binary.Base64;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

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
            byte[] body = StreamUtils.copyToByteArray(requestInputStream);
            Map<String, String> headers = getMapHeaders(request);
            String nameMethod = request.getMethod();
            String json = getJson(body);

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

    protected static String getJson(byte[] body) {
        String json = "";
        try {
            String text = new String(body);
            
            byte[] compressed = Base64.decodeBase64(text);

            try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(compressed)) {
                try (GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream)) {
                    MyTrackerSDK myTrackerSDK = MyTrackerSDK.parseFrom(gzipInputStream);
                    json = ProtoJsonUtil.toJson(myTrackerSDK);
                    log.info(json);
                }
            }
        } catch (IOException e) {
            log.info("Cannot unzip null or empty bytes");
            json = "no data";
            
            return json;
        }

        return json;
    }

    protected static boolean isZipped(final byte[] compressed) {
        return (compressed[0] == (byte) (GZIPInputStream.GZIP_MAGIC))
            && (compressed[1] == (byte) (GZIPInputStream.GZIP_MAGIC >> 8));
    }


}
