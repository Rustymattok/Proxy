package my.makarov.prox.mycomproxy.metric;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Builder;
import lombok.Getter;
import my.makarov.prox.mycomproxy.model.RequestAdjustDto;
import my.makarov.prox.mycomproxy.model.RequestDto;
import my.makarov.prox.mycomproxy.model.ResponseDto;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Builder
@Getter
public class MapMetric {
    @JsonUnwrapped
    private Map<String, Object> details;

    public static Map<String, Object> putRequest(RequestDto request) {
        Map<String, Object> details = new HashMap<>();
        details.put("user-agent", request.getUserAgent());
        details.put("request", request.getBody());
        details.put("json" , request.getProtoJson(request.getBody()));
        details.put("realIp", request.getRealIpAddress());

        return details;
    }

    public static Map<String, Object> putAdjustRequest(RequestAdjustDto request){
        Map<String, Object> details = new HashMap<>();
        details.put("user-agent", request.getUserAgent());
        details.put("json" , request.getJson());
        details.put("realIp", request.getRealIpAddress());

        return details;
    }

    public static Map<String, Object> putAdjustResponse(ResponseDto response){
        Map<String, Object> details = new HashMap<>();
        if(Objects.nonNull(response)) {
            details.put("response", response.getBody());
            details.put("data",  new String(response.getBody(), StandardCharsets.UTF_8));
        }

        return details;
    }

    public static Map<String, Object> putResponse(ResponseDto response) {
        Map<String, Object> details = new HashMap<>();
        if(Objects.nonNull(response)) {
            details.put("response", response.getBody());
        }

        return details;
    }

}