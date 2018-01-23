package com.takefour.themoment.config;

import java.nio.charset.StandardCharsets;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by hanbyeol on 2018. 1. 12..
 */
@Configuration
public class HttpClientConfig {

	private static final int DEFAULT_MAX_TOTAL_CONNECTIONS = 20;
	private static final int DEFAULT_MAX_CONNECTIONS_PER_ROUTE = 10;
	private static final int DEFAULT_CONNECTION_TIMEOUT = 2 * 1000;
	private static final int DEFAULT_CONNECTION_REQUEST_TIMEOUT = 2 * 1000;
	private static final int DEFAULT_READ_TIMEOUT = 2 * 1000;

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate(httpRequestFactory());
		restTemplate.getMessageConverters()
				.add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
		return restTemplate;
	}

	@Bean
	public ClientHttpRequestFactory httpRequestFactory() {
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient());
		requestFactory.setReadTimeout(DEFAULT_READ_TIMEOUT);
		requestFactory.setConnectTimeout(DEFAULT_CONNECTION_TIMEOUT);
		requestFactory.setConnectionRequestTimeout(DEFAULT_CONNECTION_REQUEST_TIMEOUT);
		return requestFactory;
	}

	@Bean
	public CloseableHttpClient httpClient() {
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
		connectionManager.setMaxTotal(DEFAULT_MAX_TOTAL_CONNECTIONS);
		connectionManager.setDefaultMaxPerRoute(DEFAULT_MAX_CONNECTIONS_PER_ROUTE);

		return HttpClients.createMinimal(connectionManager);
	}

}


