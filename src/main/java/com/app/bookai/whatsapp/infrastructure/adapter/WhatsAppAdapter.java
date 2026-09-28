package com.app.bookai.whatsapp.infrastructure.adapter;

import com.app.bookai.whatsapp.domain.port.out.SendWhatsAppMessagePort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Component
public class WhatsAppAdapter implements SendWhatsAppMessagePort  {


    private final RestClient restClient;
    private final String phoneNumberId;
    private final String accessToken;
    private final String apiVersion;

    public WhatsAppAdapter(
            RestClient whatsappRestClient,
            @Value("${whatsapp.api.phone-number-id}") String phoneNumberId,
            @Value("${whatsapp.api.access-token}") String accessToken,
            @Value("${whatsapp.api.version}") String apiVersion) {

        this.restClient = whatsappRestClient;
        this.phoneNumberId = phoneNumberId;
        this.accessToken = accessToken;
        this.apiVersion = apiVersion;
    }

    @Override
    public void sendMessage(String phoneNumber, String message) {

        System.out.println("5. ENVIANDO MENSAJE A META");
        System.out.println("PHONE: " + phoneNumber);
        System.out.println("MESSAGE: " + message);

        try {

            var response = restClient.post()
                    .uri("/{version}/{phoneNumberId}/messages",
                            apiVersion,
                            phoneNumberId)
                    .header(
                            "Authorization",
                            "Bearer " + accessToken
                    )
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Map.of(
                            "messaging_product", "whatsapp",
                            "to", phoneNumber,
                            "type", "text",
                            "text", Map.of(
                                    "body", message
                            )
                    ))
                    .retrieve()
                    .toEntity(String.class);

            System.out.println("6. META RESPONSE: " + response.getStatusCode());
            System.out.println("7. META BODY: " + response.getBody());

        } catch (Exception e) {

            System.out.println("❌ ERROR ENVIANDO A META");
            System.out.println("ERROR: " + e.getMessage());

            e.printStackTrace();
        }
    }
}