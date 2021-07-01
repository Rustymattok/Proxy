package tech.ubic.ed.mycomproxy.metric;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Builder;
import lombok.Getter;
import tech.ubic.ed.metrics.entity.MetricPayload;
import tech.ubic.ed.mycomproxy.model.RequestDto;
import tech.ubic.ed.mycomproxy.model.ResponseDto;

import javax.servlet.ServletOutputStream;
import java.util.HashMap;
import java.util.Map;

@Builder
@Getter
public class MapMetric implements MetricPayload {
    @JsonUnwrapped
    private Map<String, Object> details;
    
    public static Map<String, Object> putRequest(RequestDto request) {
        Map<String, Object> details = new HashMap<>();
        String body = request.getBody();
        details.put("request", body);
        details.put("realIp", request.getRealIpAddress());

        return details;
    }

    public static Map<String, Object> putResponse(ResponseDto response) {
        Map<String, Object> details = new HashMap<>();
        details.put("request", response.getBody());

        return details;
    }

}
