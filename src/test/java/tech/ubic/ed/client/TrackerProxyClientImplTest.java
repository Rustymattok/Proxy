package tech.ubic.ed.client;

import org.apache.http.client.methods.HttpPost;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import tech.ubic.ed.metrics.writter.MetricWriter;
import tech.ubic.ed.mycomproxy.client.impl.TrackerProxyClientImpl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TrackerProxyClientImplTest {

    private TrackerProxyClientImpl trackerProxyClient;

    private List<String> nameHeaders;

    @Mock
    private MetricWriter metricWriter;

    @Before
    public void setUp() {
        nameHeaders = Arrays.asList("host", "content");
        trackerProxyClient = new TrackerProxyClientImpl(metricWriter);
    }

    @Test
    public void testAddCustomHeader() {
        HttpPost httpPost = new HttpPost("http://test.com");

        Map<String, String> mapHeaders = new HashMap<>();
        mapHeaders.put("test1", "test1");
        mapHeaders.put("test2", "test2");
        mapHeaders.put("host", "test3");

        trackerProxyClient.fillHeaders(httpPost, mapHeaders, nameHeaders);
        assertThat(httpPost.getAllHeaders().length, is(2));
    }
}
