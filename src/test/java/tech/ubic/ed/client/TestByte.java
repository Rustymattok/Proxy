package tech.ubic.ed.client;

import org.junit.Test;
import java.util.zip.GZIPInputStream;

public class TestByte {

    @Test
    public void testBean() {
        System.out.println("log");
//        try {
//            InputStream inputStream = new FileInputStream("C:\\Projects\\ED\\mycomproxy\\src\\main\\resources\\test.bin");
//            File file = new File("C:\\Projects\\ED\\mycomproxy\\src\\main\\resources\\test.bin");
//            byte[] bytes = loadFile(file);
//
//            BASE64Decoder decoder = new BASE64Decoder();
//            byte[] body = decoder.decodeBuffer(inputStream);


//            byte[] encoded = Base64.encodeBase64(bytes);
//            String encodedString = new String(encoded);
//            System.out.println(encodedString);
//            return encodedString;
//            inputStream.read();
//            iii.read(buffer, 0, len);
//            MyTrackerSDK myTrackerSDK = MyTrackerSDK.parseFrom(inputStream);
//            System.out.println(myTrackerSDK.getEventList().size());
//            System.out.println(myTrackerSDK.getDeviceInfo());
//            System.out.println(myTrackerSDK.getCellCount());
//            System.out.println(myTrackerSDK.getMrgsInfo());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }


//    public static void doCopy(InputStream is, OutputStream os) throws Exception {
//        int oneByte;
//        while ((oneByte = is.read()) != -1) {
//            os.write(oneByte);
//        }
//        os.close();
//        is.close();
//    }
//

//    private static byte[] loadFile(File file) throws IOException {
//        InputStream is = new FileInputStream(file);
//
//        long length = file.length();
//        if (length > Integer.MAX_VALUE) {
//            // File is too large
//        }
//        byte[] bytes = new byte[(int) length];
//
//        int offset = 0;
//        int numRead = 0;
//        while (offset < bytes.length
//            && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
//            offset += numRead;
//        }
//
//        if (offset < bytes.length) {
//            throw new IOException("Could not completely read file " + file.getName());
//        }
//
//        is.close();
//        return bytes;
//    }

//    @SneakyThrows
//    @Test
//    public void testddd() {
//        unzip();
//    }

//    public static void loadddd() {
//        InputStream inputStream = null;
//        try {
//            inputStream = new FileInputStream("C:\\Projects\\ED\\mycomproxy\\src\\main\\resources\\test2.bin");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        try (GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream)) {
//            try (InputStreamReader inputStreamReader =
//                     new InputStreamReader(gzipInputStream, StandardCharsets.UTF_8)) {
//                try (BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
//                    StringBuilder output = new StringBuilder();
//                    String line;
//                    while ((line = bufferedReader.readLine()) != null) {
//                        output.append(line);
//                        System.out.println(output.toString());
//                    }
//                }
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//        } catch (IOException ioException) {
//            ioException.printStackTrace();
//        }
//
//    }

//    @SneakyThrows
//    public static void unzip() throws Base64DecodingException {
////        String encoded = "PUT BASE 64ENCODED GZIPPED STRING HERE";
//        InputStream inputStream = new FileInputStream("C:\\Projects\\ED\\mycomproxy\\src\\main\\resources\\test3.bin");
//        byte[] body1 = StreamUtils.copyToByteArray(inputStream);
//        String df = new String(body1);
//        System.out.println(df);
//        String encode = new BufferedReader(
//            new InputStreamReader(inputStream, StandardCharsets.UTF_8))
//            .lines()
//            .collect(Collectors.joining("\n"));
//        System.out.println(encode);
//        byte[] compressed = Base64.decodeBase64(df);
//
////        BASE64Decoder decoder = new BASE64Decoder();
////        byte[] body = decoder.decodeBuffer(inputStream);
////        byte[] compressed = Base64.decodeBase64(inputStream);
////        String data = new String(compressed);
////        System.out.println(data);
//
//        if ((compressed == null) || (compressed.length == 0)) {
//            throw new IllegalArgumentException("Cannot unzip null or empty bytes");
//        }
//        if (!isZipped(compressed)) {
//            System.out.println(compressed);
//        }
//
//        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(compressed)) {
//            try (GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream)) {
//                try (InputStreamReader inputStreamReader =
//                         new InputStreamReader(gzipInputStream, StandardCharsets.UTF_8)) {
//                    try (BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
//                        StringBuilder output = new StringBuilder();
//                        String line;
//                        while ((line = bufferedReader.readLine()) != null) {
//                            output.append(line);
//                            System.out.println(output.toString());
//                        }
//                    }
//                }
//            }
//        } catch (IOException e) {
//            throw new RuntimeException("Failed to unzip content", e);
//        }
//    }

    public static boolean isZipped(final byte[] compressed) {
        return (compressed[0] == (byte) (GZIPInputStream.GZIP_MAGIC))
            && (compressed[1] == (byte) (GZIPInputStream.GZIP_MAGIC >> 8));
    }
}

