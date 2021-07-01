package tech.ubic.ed.mycomproxy.client;

import tech.ubic.ed.mycomproxy.model.RequestDto;
import tech.ubic.ed.mycomproxy.model.ResponseDto;

public interface TrackerProxyClient {
    ResponseDto proxy(RequestDto requestDto);
}
