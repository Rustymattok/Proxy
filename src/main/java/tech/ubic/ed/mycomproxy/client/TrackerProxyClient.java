package tech.ubic.ed.mycomproxy.client;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface TrackerProxyClient {
    ResponseEntity<String> sendResponse(HttpServletRequest request);
}
