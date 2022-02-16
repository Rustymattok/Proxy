package my.makarov.prox.mycomproxy.model;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

import java.net.URI;

public class HttpGetCustom extends HttpEntityEnclosingRequestBase {
    public static final String METHOD_NAME = "GET";

    public HttpGetCustom() {
    }

    public HttpGetCustom(URI uri) {
        this.setURI(uri);
    }

    public HttpGetCustom(String uri) {
        this.setURI(URI.create(uri));
    }

    public String getMethod() {
        return "GET";
    }
}
