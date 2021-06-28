package tech.ubic.ed.mycomproxy.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tech.ubic.ed.mycomproxy.dto.DataResultObject;
import tech.ubic.ed.mycomproxy.dto.MetaObject;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpUtils {

    public static <T> ResponseEntity<DataResultObject<T>> ok(T data, MetaObject metaObject) {
        return new ResponseEntity<>(new DataResultObject<>(data, metaObject), HttpStatus.OK);
    }

    public static ResponseEntity<DataResultObject<Void>> noContent() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}