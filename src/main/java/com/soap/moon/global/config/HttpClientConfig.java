package com.soap.moon.global.config;

import lombok.Getter;
import lombok.Setter;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@Setter
@Getter
@ConfigurationProperties(prefix = "rest")
public class HttpClientConfig {

    private int maxConnTotal;
    private int maxConnPerRoute;
    private int setConnectTimeout;
    private int readTimeout;

    @Bean
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        HttpClient httpClient = HttpClientBuilder.create()
            .setMaxConnTotal(maxConnTotal) //TODO:매직넘버, 프로퍼티 설정으로 개선
            .setMaxConnPerRoute(maxConnPerRoute)
            .build();

        factory.setHttpClient(httpClient);
        factory.setConnectTimeout(setConnectTimeout);
        factory.setReadTimeout(readTimeout);
        return new RestTemplate(factory);
    }
}
