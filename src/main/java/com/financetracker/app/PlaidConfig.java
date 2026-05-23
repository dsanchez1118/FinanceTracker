package com.financetracker.app;

import com.plaid.client.ApiClient;
import com.plaid.client.request.PlaidApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;

@Configuration
public class PlaidConfig {

    @Value("${plaid.client.id}")
    private String clientId;

    @Value("${plaid.secret}")
    private String secret;

    @Bean
    public PlaidApi plaidApi() {
        HashMap<String, String> apiKeys = new HashMap<>();
        apiKeys.put("clientId", clientId);
        //apiKeys.put("plaidVersion", "2020-09-14"); // Plaid API version
        apiKeys.put("secret", secret);

        ApiClient apiClient = new ApiClient(apiKeys);
        apiClient.setPlaidAdapter(ApiClient.Sandbox); // Start in Sandbox mode!

        return apiClient.createService(PlaidApi.class);
    }
}