package tech.ubic.ed.mycomproxy.client.impl;

import com.sun.jndi.toolkit.url.Uri;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import tech.ubic.ed.metrics.writter.MetricWriter;
import tech.ubic.ed.mycomproxy.client.TrackerProxyClient;
import tech.ubic.ed.mycomproxy.exception.BadRequestException;
import tech.ubic.ed.mycomproxy.exception.TrackerException;
import tech.ubic.ed.mycomproxy.exception.URICustomException;
import tech.ubic.ed.mycomproxy.metric.EventName;
import tech.ubic.ed.mycomproxy.metric.MapMetric;
import tech.ubic.ed.mycomproxy.model.HttpEnum;
import tech.ubic.ed.mycomproxy.model.RequestDto;
import tech.ubic.ed.mycomproxy.model.ResponseDto;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class TrackerProxyClientImpl implements TrackerProxyClient {
    @Value("${proxy.tracker.url}")
    private String urlTracker;

    @Value("${proxy.headers}")
    private List<String> headers;

    private final MetricWriter metricWriter;

    public TrackerProxyClientImpl(MetricWriter metricWriter) {
        this.metricWriter = metricWriter;
    }

    @Override
    public ResponseDto proxy(RequestDto requestDto) {
        sendRequestMetric(requestDto);

        ResponseDto responseDto = null;

        try {
            CloseableHttpClient client = HttpClients.createDefault();

            HttpEnum httpEnum = HttpEnum.valueOf(requestDto.getHttpMethod());

            HttpEntityEnclosingRequestBase httpRequest = Optional.
                ofNullable(httpEnum.getRequest(urlTracker))
                .orElseThrow(() -> new BadRequestException("not correct request"));

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

    public void fillHeaders(HttpEntityEnclosingRequestBase httpRequest, Map<String, String> headers, List<String> nameHeaders) {

        headers.forEach((key, value) -> addCustomHeader(httpRequest, key, value, nameHeaders));
    }

    public void addCustomHeader(HttpEntityEnclosingRequestBase httpRequest, String headerName, String headerValue, List<String> nameHeaders) {

        if (!nameHeaders.contains(headerName)) {
            httpRequest.addHeader(headerName, headerValue);
        }

    }

    public void sendRequestMetric(RequestDto request) {
        metricWriter.writeMetric(
            EventName.REQUEST_TRACKER,
            "",//todo что в id пихать ?
            MapMetric.builder().details(MapMetric.putRequest(request)).build(),
            Arrays.asList("java", "tracker")
        );
    }

    public void sendResponseMetric(ResponseDto response) {
        metricWriter.writeMetric(
            EventName.RESPONSE_TRACKER,
            "",//todo что в id пихать ?
            MapMetric.builder().details(MapMetric.putResponse(response)).build(),
            Arrays.asList("java", "tracker")
        );
    }

}
 