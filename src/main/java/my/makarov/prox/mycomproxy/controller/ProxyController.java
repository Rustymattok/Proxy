package my.makarov.prox.mycomproxy.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import my.makarov.prox.mycomproxy.client.TrackerProxyClient;
import my.makarov.prox.mycomproxy.config.cashInputStream.CachedBodyHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@Api("Прокси для трекера")
@RequestMapping("/")
@Slf4j
public class ProxyController {
    private final TrackerProxyClient client;

    public ProxyController(TrackerProxyClient client) {
        this.client = client;
    }


    @ApiOperation("Прокси метод")
    @RequestMapping("/**")
    public void proxy(HttpServletResponse response, HttpServletRequest request) {
        try {
            CachedBodyHttpServletRequest cachedBodyHttpServletRequest = new CachedBodyHttpServletRequest(request);
            client.sendToProxy(response,cachedBodyHttpServletRequest);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

}