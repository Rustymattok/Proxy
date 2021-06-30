package tech.ubic.ed.mycomproxy.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ubic.ed.mycomproxy.client.TrackerProxyClient;
import tech.ubic.ed.mycomproxy.config.url.ApiUrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api("Прокси для трекера")
@RequestMapping(ApiUrl.INTERNAL)
@Slf4j
public class ProxyController {

    private final TrackerProxyClient client;

    public ProxyController(TrackerProxyClient client) {
        this.client = client;
    }


    @ApiOperation("Прокси метод")
    @PostMapping("/")
    public void testMethod(HttpServletResponse response, HttpServletRequest request) {

        client.proxy(response, request);
    }

}