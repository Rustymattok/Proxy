package my.makarov.prox.mycomproxy.config.cashInputStream;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Order(Ordered.LOWEST_PRECEDENCE)
@Component
@Slf4j
@WebFilter(filterName = "printRequestContentFilter", urlPatterns = "/*")
public class PrintRequestContentFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        log.info("IN  PrintRequestContentFilter ");
        InputStream inputStream = httpServletRequest.getInputStream();
        byte[] body = StreamUtils.copyToByteArray(inputStream);
        log.info("In PrintRequestContentFilter. Request body is: " + new String(body));
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}