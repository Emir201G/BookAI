package com.app.bookai.whatsapp.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class WhatsAppConfig {

    @Bean
    public RestClient whatsappRestClient(
            @Value("${whatsapp.api.base-url}") String baseUrl
    ) {
        return RestClient.builder()
                .baseUrl(baseUrl).
                build();
    }
}
