package tech.ubic.ed.mycomproxy.client;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface TrackerProxyClient {
    void proxy(HttpServletResponse response, HttpServletRequest request);
}
