package tech.ubic.ed.mycomproxy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonPropertyOrder({"data", "metaObject"})
public class DataResultObject<T> {

    @JsonProperty(value = "data")
    private T data;

    @JsonUnwrapped
    private MetaObject metaObject;

    public DataResultObject(T data) {
        this.data = data;
    }

    public DataResultObject(T data, MetaObject metaObject) {
        this.data = data;
        this.metaObject = metaObject;
    }
}