package tech.ubic.ed.mycomproxy.model;

import lombok.Getter;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;

public enum HttpEnum {
    POST("POST", new HttpPost()),
    GET("GET", new HttpGet()),
    PUT("PUT", new HttpPut());

    HttpEnum(String title, HttpEntityEnclosingRequestBase http) {
        this.title = title;
        this.httpRequest = http;
    }

    HttpEnum(String title, HttpGet httpGet) {

    }

    @Getter
    private String title;

    @Getter
    private HttpEntityEnclosingRequestBase httpRequest;
}
