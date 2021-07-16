package tech.ubic.ed.mycomproxy;

import com.google.protobuf.TextFormat;

import com.googlecode.protobuf.format.JsonFormat;
import com.sun.demo.jvmti.hprof.Tracker;
import lombok.SneakyThrows;
import org.junit.Test;
import tech.ubic.ed.mycomproxy.utils.ProtoJsonUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class TestProto {
    
    @Test
    public void take(){
        
//        AddressBookProtos.AddressBook deserialized
//            = AddressBookProtos.AddressBook.newBuilder()
//            .mergeFrom(new FileInputStream("filePath")).build();
//
//        AddressBookProtos.Person person = AddressBookProtos.Person.newBuilder().mergeFrom(new FileInputStream("")).build();
//
//        TrackerSDK.MyTrackerSDK des = null;
//        try {
//            FileInputStream fis = new FileInputStream("C:\\Projects\\ED\\mycomproxy\\src\\main\\resources\\test.txt");
//
//            InputStreamReader reader = new InputStreamReader(fis, "ASCII");
//
//            // Use the normal try/finally for closing reliably
//         
//            TrackerSDK.MyTrackerSDK someProto = TrackerSDK.MyTrackerSDK.getDefaultInstance();
//            
//            String jsonFormat;
//            jsonFormat = JsonFormat.printToString(someProto);

//            
//            TextFormat.merge(reader, TrackerSDK.MyTrackerSDK.newBuilder());
//
//            des = TrackerSDK.MyTrackerSDK.newBuilder().pa(reader).build();
            
//            String json = ProtoJsonUtil.toJson(des);
//
//            System.out.println(json);
//        } catch (IOException ioException) {
//            ioException.printStackTrace();
//        }
//        
        
        //        deserialized.getPeople(0).

//        assertEquals(deserialized.getPeople(0).getEmail(), email);
//        assertEquals(deserialized.getPeople(0).getId(), id);
//        assertEquals(deserialized.getPeople(0).getName(), name);
//        assertEquals(deserialized.getPeople(0).getNumbers(0), number);
    }
}
