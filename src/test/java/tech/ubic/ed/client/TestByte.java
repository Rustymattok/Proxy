package tech.ubic.ed.client;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.util.StreamUtils;
import sun.misc.BASE64Decoder;
import tech.ubic.ed.mycomproxy.proto.MyTrackerSDK;
import tech.ubic.ed.mycomproxy.utils.ProtoJsonUtil;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.zip.GZIPInputStream;


public class TestByte {
    @Test 
    public void testGZIP() {
        try {
            InputStream inputStream = new FileInputStream("C:\\Projects\\ED\\mycomproxy\\src\\main\\resources\\gzip2.txt");
            byte[] body = StreamUtils.copyToByteArray(inputStream);
            String text = new String(body);
            
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] compressed = decoder.decodeBuffer(text);
            
            if (compressed.length == 0) {
                throw new IllegalArgumentException("Cannot unzip null or empty bytes");
            }
            if (!isZipped(compressed)) {
                System.out.println("compressed " + Arrays.toString(compressed));
            }

            try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(compressed)) {
                try (GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream)) {
                    MyTrackerSDK myTrackerSDK = MyTrackerSDK.parseFrom(gzipInputStream);
                    String json = ProtoJsonUtil.toJson(myTrackerSDK);
                    System.out.println(json);
                }
            } catch (IOException e) {
                throw new RuntimeException("Failed to unzip content", e);
            }

        } catch (IOException  e) {
            e.printStackTrace();
        }
    }

    public static boolean isZipped(final byte[] compressed) {
        return (compressed[0] == (byte) (GZIPInputStream.GZIP_MAGIC))
            && (compressed[1] == (byte) (GZIPInputStream.GZIP_MAGIC >> 8));
    }

}

