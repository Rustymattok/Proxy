package tech.ubic.ed.mycomproxy.model;

import lombok.Getter;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPut;

import java.util.Objects;

public enum HttpEnum {
    POST_REQUEST("POST"),
    PUT_REQUEST("PUT");

    HttpEnum(String title) {
        this.title = title;
    }

    @Getter
    private String title;

    public static HttpEntityEnclosingRequestBase getHttpMethod(String name, String url) {
        if (Objects.equals(name, POST_REQUEST.getTitle())) {
            return new HttpPut(url);
        }

        if (Objects.equals(name, PUT_REQUEST.getTitle())) {
            return new HttpPut(url);
        }

        return null;
    }

}
