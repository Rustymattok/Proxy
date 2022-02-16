package my.makarov.prox.mycomproxy.client.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.ByteArrayEntity;
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
import my.makarov.prox.mycomproxy.model.RequestDto;
import my.makarov.prox.mycomproxy.model.ResponseDto;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@Profile("mailTracker")
public class TrackerProxyClientImpl implements TrackerProxyClient {
    @Value("${proxy.tracker.url}")
    private String urlTracker;

    @Value("${proxy.headers}")
    private List<String> headers;

    public ResponseDto proxy(RequestDto requestDto) {
        log.info("MAILTRACKER PROXY ACTIVE");

        ResponseDto responseDto = null;

        try {
            CloseableHttpClient client = HttpClients.createDefault();

            HttpEnum httpEnum = HttpEnum.valueOf(requestDto.getHttpMethod());

            HttpEntityEnclosingRequestBase httpRequest = Optional.
                ofNullable(httpEnum.getRequest(urlTracker))
                .orElseThrow(() -> new BadRequestException("not correct request"));

            sendRequestMetric(requestDto); //todo подумать как лучше вставлять метрику,исключая сторнних дятлов.

            fillHeaders(httpRequest, requestDto.getHeaders(), headers);

            HttpEntity entity = new ByteArrayEntity(requestDto.getBody());

            httpRequest.setEntity(entity);

            String host = httpRequest.getURI().getHost();
            String scheme = httpRequest.getURI().getScheme();

            URI uri = new URI(scheme, host, requestDto.getPath(), requestDto.getQuery());
            httpRequest.setURI(uri);

            log.info(String.format("real ip %s REQUEST START", requestDto.getRealIpAddress()));
            log.info(" -------------------- HEADERS ----------------------- ");
            for (Header allHeader : httpRequest.getAllHeaders()) {
                log.info(allHeader.getName() + " " + allHeader.getValue());
            }
            log.info(" -------------------- REQUEST END ----------------------- ");

            CloseableHttpResponse trackerResponse = client.execute(httpRequest);

            responseDto = ResponseDto.of(trackerResponse);

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

        try {
            responseDto = proxy(RequestDto.of(request));
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

    public void sendRequestMetric(RequestDto request) {
        //todo send metric where you want
    }

    public void sendResponseMetric(ResponseDto response) {
        //todo send metric where you want
    }

}
 