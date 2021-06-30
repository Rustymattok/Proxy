package tech.ubic.ed.mycomproxy.metric;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.StreamUtils;
import tech.ubic.ed.metrics.entity.MetricPayload;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Builder
@Getter
public class MapMetric implements MetricPayload {
    @JsonUnwrapped
    private Map<String, Object> details;


    public static Map<String, Object> putRequest(HttpServletRequest request) {
        byte[] body = new byte[0];
        Map<String, Object> details = new HashMap<>();
        try {
            InputStream requestInputStream = request.getInputStream();
            body = StreamUtils.copyToByteArray(requestInputStream);
            details.put("request", body);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return details;
    }

    public static Map<String, Object> putResponse(ServletOutputStream response) {
        Map<String, Object> details = new HashMap<>();
        details.put("request", response);

        return details;
    }

}
