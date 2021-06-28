package tech.ubic.ed.mycomproxy.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MetaObject<T> {
    private T meta;

    public MetaObject(T meta) {
        this.meta = meta;
    }
}
