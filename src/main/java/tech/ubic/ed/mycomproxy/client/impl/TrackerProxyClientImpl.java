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
import tech.ubic.ed.metrics.writter.MetricWriter;
import tech.ubic.ed.mycomproxy.client.TrackerProxyClient;
import tech.ubic.ed.mycomproxy.exception.BadRequestException;
import tech.ubic.ed.mycomproxy.metric.EventName;
import tech.ubic.ed.mycomproxy.metric.MapMetric;
import tech.ubic.ed.mycomproxy.model.FilterHeader;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Objects;

@Service
@Slf4j
public class TrackerProxyClientImpl implements TrackerProxyClient {
    @Value("${proxy.tracker.url}")
    private String urlTracker;

    private final MetricWriter metricWriter;

    public TrackerProxyClientImpl(MetricWriter metricWriter) {
        this.metricWriter = metricWriter;
    }

    @Override
    public void proxy(HttpServletResponse response, HttpServletRequest request) {
        sendRequestMetric(request);

        try {
            CloseableHttpClient client = HttpClients.createDefault();

            String realIpAddress = request.getHeader("X-Real-IP");

            InputStream requestInputStream = request.getInputStream();
            byte[] body = StreamUtils.copyToByteArray(requestInputStream);

            HttpPost httpPost = new HttpPost(urlTracker);

            httpPost.addHeader("X-Real-IP", realIpAddress);

            fillHeaders(httpPost, request);

            httpPost.setEntity(new ByteArrayEntity(body));

            CloseableHttpResponse trackerResponse = client.execute(httpPost);

            responseClient(response, trackerResponse);

            ServletOutputStream out = response.getOutputStream();

            sendResponseMetric(out);

        } catch (HttpClientErrorException ex) {
            throw new BadRequestException(ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
            log.info("Common exception");
        }
    }

    public void responseClient(HttpServletResponse response, CloseableHttpResponse trackerResponse) {
        if (Objects.nonNull(trackerResponse)) {
            response.setStatus(trackerResponse.getStatusLine().getStatusCode());
            //todo добавить контент хедеры и вот это все или просто и зачем это с трекера нужно ?
        }
    }

    public void fillHeaders(HttpPost httpPost, HttpServletRequest request) {
        Enumeration en = request.getHeaderNames();
        while (en.hasMoreElements()) {
            String headerName = (String) en.nextElement();
            String headerValue = request.getHeader(headerName);

            if (!FilterHeader.exceptionHeaders(headerName)) {
                httpPost.addHeader(headerName, headerValue);
            }
        }

    }

    public void sendRequestMetric(HttpServletRequest request) {
        metricWriter.writeMetric(
            EventName.REQUEST_TRACKER,
            "",//todo что в id пихать ?
            MapMetric.builder().details(MapMetric.putRequest(request)).build(),
            Arrays.asList("java", "tracker")
        );
    }

    public void sendResponseMetric(ServletOutputStream response) {
        metricWriter.writeMetric(
            EventName.RESPONSE_TRACKER,
            "",//todo что в id пихать ?
            MapMetric.builder().details(MapMetric.putResponse(response)).build(),
            Arrays.asList("java", "tracker")
        );
    }

}
 