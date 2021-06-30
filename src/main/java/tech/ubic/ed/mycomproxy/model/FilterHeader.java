package tech.ubic.ed.mycomproxy.model;


import java.util.ArrayList;
import java.util.List;

public class FilterHeader {

    public static Boolean exceptionHeaders(String nameHeader) {
        List<String> nameHeaders = new ArrayList<>();
//        nameHeaders.add("postman-token");
//        nameHeaders.add("accept");
//        nameHeaders.add("user-agent");
        nameHeaders.add("host");
//        nameHeaders.add("accept-encoding");
//        nameHeaders.add("connection");
//        nameHeaders.add("cookie");
        nameHeaders.add("content-length");

        return nameHeaders.contains(nameHeader);
    }
}
