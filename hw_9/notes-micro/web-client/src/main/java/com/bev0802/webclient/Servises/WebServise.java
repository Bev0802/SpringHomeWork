package com.bev0802.webclient.Servises;

import com.bev0802.webclient.Api.Rest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class WebServise {
    private final Rest restApi;
    private final RestTemplate restTemplate;
    private final String restServiceUrl;

    @Autowired
    public WebServise(Rest restApi, RestTemplate restTemplate, @Value("${api.rest.restUrl}") String restServiceUrl) {
        this.restApi = restApi;
        this.restTemplate = restTemplate;
        this.restServiceUrl = restServiceUrl;
    }
}