package tech.ubic.ed.client;

import org.junit.Test;
import tech.ubic.ed.mycomproxy.proto.MyTrackerSDK;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TestByte {

    @Test
    public void testBean() {
        MyTrackerSDK myTrackerSDK = MyTrackerSDK.newBuilder()
            .addEvent(MyTrackerSDK.Event.newBuilder().setEventType(1).build())
            .build();

        assertThat(myTrackerSDK.getEvent(0).getEventType(), is(1));
    }
}
