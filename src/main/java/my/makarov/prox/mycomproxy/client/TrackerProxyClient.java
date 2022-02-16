package my.makarov.prox.mycomproxy.client;

import my.makarov.prox.mycomproxy.config.cashInputStream.CachedBodyHttpServletRequest;

import javax.servlet.http.HttpServletResponse;

public interface TrackerProxyClient {
    void sendToProxy(HttpServletResponse response, CachedBodyHttpServletRequest request);
}
