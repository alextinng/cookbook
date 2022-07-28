package com.jokes;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ESClientFactory {
    public static RestHighLevelClient singleton(String userName, String password,
                                                List<String> endpoints) {
        HttpHost[] httpHosts = new HttpHost[endpoints.size()];
        for (int i = 0; i < endpoints.size(); i++) {
            httpHosts[i] = HttpHost.create(endpoints.get(i));
        }
        RestClientBuilder builder = RestClient.builder(httpHosts);
        builder.setHttpClientConfigCallback(buildHttpclientConfigCallback(userName, password));

        return new RestHighLevelClient(builder);
    }

    private static RestClientBuilder.HttpClientConfigCallback buildHttpclientConfigCallback(String userName, String password) {
        return httpClientBuilder -> {
            if (StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(password)) {
                // set username and password
                CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
                credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(userName, password));

                httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            }

            // set max connection
            httpClientBuilder.setMaxConnTotal(10);
            // set keep-alive duration to 60 seconds. default keep-alive duration in nginx is 75 seconds while
            // most web-browsers set it to 60 seconds.
            httpClientBuilder.setKeepAliveStrategy(((response, context) -> TimeUnit.SECONDS.toMillis(60)));

            return httpClientBuilder;
        };
    }
}
