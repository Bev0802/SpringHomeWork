package com.bev0802.webclient.Api;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "api.rest")
public class Rest {
    private String restUrl;

    public String getUrl() {
        return restUrl;
    }
}
