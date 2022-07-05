package com.jokes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public class ESClientFactoryTest {
    private Gson gson;

    @Before
    public void setup() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Test
    public void singleton() throws IOException {
        RestHighLevelClient esClient = ESClientFactory.singleton("elastic", "seczone",
                Collections.singletonList("http://127.0.0.1:9200"));

        GetRequest getRequest = new GetRequest("test_index");
        getRequest.id("8b21c3def0bd3f3755ff3190586aa8f5adb8377dd96c5735e5134de18b06a40c");
        GetResponse response = esClient.get(getRequest, RequestOptions.DEFAULT);
        Map<String, Object> source = response.getSourceAsMap();
        System.out.println(gson.toJson(source, Map.class));
    }
}