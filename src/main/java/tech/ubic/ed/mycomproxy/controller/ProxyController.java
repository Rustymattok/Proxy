package tech.ubic.ed.mycomproxy.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ubic.ed.metrics.writter.MetricWriter;
import tech.ubic.ed.mycomproxy.client.TrackerProxyClient;
import tech.ubic.ed.mycomproxy.config.url.ApiUrl;
import tech.ubic.ed.mycomproxy.dto.DataResultObject;
import tech.ubic.ed.mycomproxy.metric.EventName;
import tech.ubic.ed.mycomproxy.metric.MapMetric;
import tech.ubic.ed.mycomproxy.utils.HttpUtils;
import tech.ubic.ed.security.util.Authorized;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@RestController
@Api("Прокси для трекера")
@RequestMapping(ApiUrl.INTERNAL)
@Slf4j
public class ProxyController implements Authorized {

    private final MetricWriter metricWriter;
    private final TrackerProxyClient client;

    public ProxyController(MetricWriter metricWriter, TrackerProxyClient client) {
        this.metricWriter = metricWriter;
        this.client = client;
    }


    @ApiOperation("Прокси метод")
    @PostMapping("/test/")
    public ResponseEntity<DataResultObject<Void>> testMethod(HttpServletRequest request) {
        //todo обработать данные по протобафу в в боде.

        metricWriter.writeMetric(
            EventName.TRACKER,
            getUser().getGuid(),
            MapMetric.builder().build(),
            Arrays.asList("java", "tracker")
        );

        client.sendResponse(request);

        return HttpUtils.noContent();
    }

}