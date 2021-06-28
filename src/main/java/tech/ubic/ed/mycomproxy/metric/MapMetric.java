package tech.ubic.ed.mycomproxy.metric;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Builder;
import lombok.Getter;
import tech.ubic.ed.metrics.entity.MetricPayload;

import java.util.Map;

@Builder
@Getter
public class MapMetric implements MetricPayload {
    @JsonUnwrapped
    private Map<String, Object> details;
}
