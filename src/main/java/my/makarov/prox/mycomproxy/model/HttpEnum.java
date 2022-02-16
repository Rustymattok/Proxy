package my.makarov.prox.mycomproxy.model;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;

import java.util.Arrays;

@Slf4j
public enum HttpEnum {
    POST("POST") {
        @Override
        public HttpEntityEnclosingRequestBase getRequest(String url) {
            return new HttpPost(url);
        }
    },

    PUT("PUT") {
        @Override
        public HttpEntityEnclosingRequestBase getRequest(String url) {
            return new HttpPut(url);
        }
    },
    
    GET("GET"){
        @Override
        public HttpEntityEnclosingRequestBase getRequest(String url) {
     
            return new HttpGetCustom(url);
        }
    };

    HttpEnum(String title) {
        this.title = title;
    }

    abstract public HttpEntityEnclosingRequestBase getRequest(String url);

    @Getter
    private String title;


    public static HttpEnum of(String method) {
        for (HttpEnum e : Arrays.asList(HttpEnum.values())) {
            if (e.getTitle().equals(method)) {
                return e;
            }
        }

        return null;
    }
}
    
