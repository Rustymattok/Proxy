package my.makarov.prox.mycomproxy.config.cashInputStream;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "my.makarov.prox.mycomproxy.config.cashInputStream")
public class HttpRequestDemoConfig implements WebMvcConfigurer {

}