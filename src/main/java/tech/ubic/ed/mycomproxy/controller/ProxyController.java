package tech.ubic.ed.mycomproxy.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
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
import java.util.Objects;

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
        ResponseDto responseDto = null;

        try {
            responseDto = client.proxy(RequestDto.of(request));
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

}