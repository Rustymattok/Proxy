package my.makarov.prox.mycomproxy.utils;

import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.googlecode.protobuf.format.JsonFormat;

import java.io.IOException;


public final class ProtoJsonUtil {

    public static String toJson(MessageOrBuilder messageOrBuilder) throws IOException {

        return new JsonFormat().printToString((Message) messageOrBuilder);
    }
}