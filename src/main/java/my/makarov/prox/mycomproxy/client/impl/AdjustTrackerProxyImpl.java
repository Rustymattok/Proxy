package my.makarov.prox.mycomproxy.client.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import my.makarov.prox.mycomproxy.client.TrackerProxyClient;
import my.makarov.prox.mycomproxy.config.cashInputStream.CachedBodyHttpServletRequest;
import my.makarov.prox.mycomproxy.exception.BadRequestException;
import my.makarov.prox.mycomproxy.exception.TrackerException;
import my.makarov.prox.mycomproxy.exception.URICustomException;
import my.makarov.prox.mycomproxy.model.HttpEnum;
import my.makarov.prox.mycomproxy.model.RequestAdjustDto;
import my.makarov.prox.mycomproxy.model.ResponseDto;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@Profile("adjust")
public class AdjustTrackerProxyImpl implements TrackerProxyClient {

    @Value("${proxy.tracker.url}")
    private String urlTracker;

    @Value("${proxy.headers}")
    private List<String> headers;

    public ResponseDto proxy(RequestAdjustDto requestDto) {
        log.info("ADJUST PROXY ACTIVE");

        ResponseDto responseDto = null;

        try {
            CloseableHttpClient client = HttpClients.createDefault();

            HttpEnum httpEnum = HttpEnum.valueOf(requestDto.getHttpMethod());

            HttpEntityEnclosingRequestBase httpRequest = httpEnum.getRequest(urlTracker);

            sendRequestMetric(requestDto);

            fillHeaders(httpRequest, requestDto.getHeaders(), headers);
            httpRequest.addHeader("X-Adjust-Forwarded-For",requestDto.getRealIpAddress());
            httpRequest.addHeader("host","app.adjust.com");
            HttpEntity entity = new InputStreamEntity(requestDto.getBodyStream());
            httpRequest.setEntity(entity);

            String host = httpRequest.getURI().getHost();
            String scheme = httpRequest.getURI().getScheme();
            String fragment = "";

            URI uri = new URI(scheme, host, requestDto.getPath(), requestDto.getQuery() ,fragment);
            httpRequest.setURI(uri);

            log.info(String.format("real ip %s REQUESTED TO TRACKER START", requestDto.getRealIpAddress()));
            log.info("url COMMAND " + httpRequest.getURI());
            log.info(" -------------------- HEADERS ----------------------- ");
            for (Header allHeader : httpRequest.getAllHeaders()) {
                log.info(allHeader.getName() + " " + allHeader.getValue());
            }
            log.info(" --------------- REQUEST TO TRACKER END --------------- ");
            
            CloseableHttpResponse trackerResponse = client.execute(httpRequest);
            responseDto = ResponseDto.of(trackerResponse);

            log.info("------------ RESPONSE FROM TRACKER START ------------");
            log.info("url COMMAND " + httpRequest.getURI());
            log.info(" -------------------- HEADERS ----------------------- ");
            for (Header allHeader : trackerResponse.getAllHeaders()) {
                log.info(allHeader.getName() + " " + allHeader.getValue());
            }
            log.info(" -------------------- BODY ----------------------- ");
            String body = new String(responseDto.getBody(), StandardCharsets.UTF_8);
            log.info(body);
            log.info("------------- RESPONSE FROM TRACKER END ----------------");

            sendResponseMetric(responseDto);

        } catch (HttpClientErrorException | IOException ex) {
            throw new BadRequestException("internal error with request", ex);
        } catch (ResourceAccessException ex) {
            throw new TrackerException("not available tracker server", ex);
        } catch (URISyntaxException ex) {
            throw new URICustomException("bad uri created", ex);
        }

        return Optional.ofNullable(responseDto).orElseThrow(() -> new TrackerException("no response from server"));
    }

    @Override
    public void sendToProxy(HttpServletResponse response, CachedBodyHttpServletRequest request) {
        ResponseDto responseDto = null;
        RequestAdjustDto requestAdjust = RequestAdjustDto.of(request);

        log.info("--------------- REQUEST FROM DEVICE START ---------------");
        log.info("url COMMAND " + requestAdjust.getUri());
        log.info(" -------------------- HEADERS ----------------------- ");
        for (String key:requestAdjust.getHeaders().keySet()) {
            log.info(key + " : " + requestAdjust.getHeaders().get(key));
        }
        log.info(" -------------------- BODY ----------------------- ");
        log.info(requestAdjust.getJson());
        log.info(" ---------------  REQUEST FROM DEVICE END --------------- ");

        try {
            responseDto = proxy(requestAdjust);
            byte[] byteResponse = responseDto.getBody();
            response.setStatus(responseDto.getResponse().getStatusLine().getStatusCode());

            for (Header header : responseDto.getResponse().getAllHeaders()) {
                response.addHeader(header.getName(), header.getValue());
            }

            if (Objects.nonNull(responseDto.getBody())) {

                response.getOutputStream().write(byteResponse);
            }else {
                response.getWriter();
            }

            log.info("-------------- RESPONSE TO DEVICE START --------------");
            log.info("url COMMAND " + requestAdjust.getUri());
            log.info(" -------------------- HEADERS ----------------------- ");
            Collection<String> headerNames = response.getHeaderNames();
            for (String headerName : headerNames) {
                log.info(headerName + " : " + response.getHeader(headerName));
            }
            log.info(" -------------------- BODY ----------------------- ");
            String body = new String(responseDto.getBody(), StandardCharsets.UTF_8);
            log.info(body);
            log.info(" --------------- RESPONSE TO DEVICE END -------------- ");

        } catch (IOException ex) {
            throw new BadRequestException("cant send response for client", ex);
        }
    }

    public void fillHeaders(HttpEntityEnclosingRequestBase httpRequest, Map<String, String> headers, List<String> nameHeaders) {
        headers.forEach((key, value) -> addCustomHeader(httpRequest, key, value, nameHeaders));
    }

    public void addCustomHeader(HttpEntityEnclosingRequestBase httpRequest, String headerName, String headerValue, List<String> nameHeaders) {

        if (!nameHeaders.contains(headerName)) {
            httpRequest.addHeader(headerName, headerValue);
        }

    }

    public void sendRequestMetric(RequestAdjustDto request) {
        //todo send metric where you want
    }

    public void sendResponseMetric(ResponseDto response) {
        //todo send metric where you want
    }
}