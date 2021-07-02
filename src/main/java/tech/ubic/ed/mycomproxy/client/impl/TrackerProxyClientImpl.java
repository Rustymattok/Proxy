package tech.ubic.ed.mycomproxy.client.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
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
import tech.ubic.ed.mycomproxy.metric.EventName;
import tech.ubic.ed.mycomproxy.metric.MapMetric;
import tech.ubic.ed.mycomproxy.model.RequestDto;
import tech.ubic.ed.mycomproxy.model.ResponseDto;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
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

            byte[] body = StreamUtils.copyToByteArray(requestDto.getRequestInputStream());

            HttpPost httpPost = new HttpPost(urlTracker);

            httpPost.addHeader("X-Real-IP", requestDto.getRealIpAddress());

            fillHeaders(httpPost, requestDto.getHeaders(),headers);

            httpPost.setEntity(new ByteArrayEntity(body));

            CloseableHttpResponse trackerResponse = client.execute(httpPost);

            responseDto = ResponseDto.of(trackerResponse);

            sendResponseMetric(responseDto);

        } catch (HttpClientErrorException ex) {
            throw new BadRequestException(ex.getMessage());
        } catch (ResourceAccessException ex) {
            throw new TrackerException("not available tracker server", ex);
        } catch (IOException ex) {
            throw new BadRequestException("internal error with request", ex);
        }

        return Optional.ofNullable(responseDto).orElseThrow(() -> new NoSuchElementException("no response from server"));
    }

    public void fillHeaders(HttpPost httpPost, Map<String, String> headers,List<String> nameHeaders) {

        headers.forEach((key, value) -> addCustomHeader(httpPost, key, value,nameHeaders));
    }

    public void addCustomHeader(HttpPost httpPost, String headerName, String headerValue,List<String> nameHeaders) {

        if (!nameHeaders.contains(headerName)) {
            httpPost.addHeader(headerName, headerValue);
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
 