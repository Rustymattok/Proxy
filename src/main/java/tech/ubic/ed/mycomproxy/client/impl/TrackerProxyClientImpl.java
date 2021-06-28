package tech.ubic.ed.mycomproxy.client.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import tech.ubic.ed.mycomproxy.client.TrackerProxyClient;
import tech.ubic.ed.mycomproxy.exception.BadRequestException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Service
@Slf4j
public class TrackerProxyClientImpl implements TrackerProxyClient {
    @Value("${proxy.tracker.url}")
    private String urlTracker;

    private final RestTemplate restTemplate;

    public TrackerProxyClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<String> sendResponse(HttpServletRequest request) {
        try {
            String body = IOUtils.toString(request.getReader());
            String realIpAddress = request.getHeader("X-Real-IP");

            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Real-IP", realIpAddress);

            HttpEntity<?> entity = new HttpEntity<>(body, headers);

            ResponseEntity<Object> response = restTemplate.exchange(urlTracker,
                HttpMethod.valueOf(request.getMethod()),
                entity,
                Object.class,
                request.getParameterMap());

            return ResponseEntity.status(response.getStatusCode())
                .body(body);

        } catch (HttpClientErrorException | IOException ex) {

            throw new BadRequestException("bad request");//сделать свой ексепшн
        }
    }

}
 