package tech.ubic.ed.mycomproxy.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ubic.ed.mycomproxy.client.TrackerProxyClient;
import tech.ubic.ed.mycomproxy.exception.BadRequestException;
import tech.ubic.ed.mycomproxy.model.RequestDto;
import tech.ubic.ed.mycomproxy.model.ResponseDto;

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
    @PostMapping()
    public void proxy(HttpServletResponse response, HttpServletRequest request) {
        ResponseDto responseDto = null;

        try {
            responseDto = client.proxy(RequestDto.of(request));
            byte[] byteResponse = responseDto.getBody();

            response.getOutputStream().write(byteResponse);

        } catch (IOException ex) {
            throw new BadRequestException("cant send response for client", ex);
        }
    }

}