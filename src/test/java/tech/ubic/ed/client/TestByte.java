package tech.ubic.ed.client;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import sun.misc.BASE64Decoder;
import tech.ubic.ed.mycomproxy.proto.MyTrackerSDK;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TestByte {

    @Test
    public void testBean() {
        try {
            InputStream inputStream = new FileInputStream("C:\\Projects\\ED\\mycomproxy\\src\\main\\resources\\test.bin");
            File file = new File("C:\\Projects\\ED\\mycomproxy\\src\\main\\resources\\test2.bin");
            byte[] bytes = loadFile(file);

            GZIPInputStream gis = new GZIPInputStream(inputStream);
//            InputStream inputStream1 = null;
//            doCopy(inputStream1, gis); // copy and uncompress
//            InputStream in = GZIPInputStream(inputStream);
            

            BASE64Decoder decoder = new BASE64Decoder();
            byte[] body = decoder.decodeBuffer(gis);

            
//            byte[] encoded = Base64.encodeBase64(bytes);
//            String encodedString = new String(encoded);
//            System.out.println(encodedString);
//            return encodedString;
//            inputStream.read();
//            iii.read(buffer, 0, len);
                MyTrackerSDK myTrackerSDK = MyTrackerSDK.parseFrom(gis);
                System.out.println(myTrackerSDK.getEventList().size());
                System.out.println(myTrackerSDK.getDeviceInfo());
                System.out.println(myTrackerSDK.getCellCount());
                System.out.println(myTrackerSDK.getMrgsInfo());

        } catch (Exception e) {
            e.printStackTrace();
        } 
    }


    public static void doCopy(InputStream is, OutputStream os) throws Exception {
        int oneByte;
        while ((oneByte = is.read()) != -1) {
            os.write(oneByte);
        }
        os.close();
        is.close();
    }
    

    private static byte[] loadFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        long length = file.length();
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
        byte[] bytes = new byte[(int)length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
            && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }

        is.close();
        return bytes;
    }
}
