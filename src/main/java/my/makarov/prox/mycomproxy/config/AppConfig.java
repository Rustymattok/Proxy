package my.makarov.prox.mycomproxy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class AppConfig {

    @Value("${rest.timeout.connect}")
    private long connectTimeout;
    @Value("${rest.timeout.read}")
    private long readTimeout;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
            .setConnectTimeout(Duration.ofSeconds(connectTimeout))
            .setReadTimeout(Duration.ofSeconds(readTimeout))
            .build();
    }
}
