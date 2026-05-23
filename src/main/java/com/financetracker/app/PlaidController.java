package com.financetracker.app;

import com.plaid.client.model.CountryCode;
import com.plaid.client.model.LinkTokenCreateRequest;
import com.plaid.client.model.LinkTokenCreateRequestUser;
import com.plaid.client.model.LinkTokenCreateResponse;
import com.plaid.client.model.Products;
import com.plaid.client.request.PlaidApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Response;

import java.io.IOException;
import java.util.Arrays;

@RestController
@RequestMapping("/api/plaid")
public class PlaidController {
    @Autowired
    PlaidApi plaidApi;

    @PostMapping("/create_link_token")
    public LinkTokenCreateResponse createLinkToken() throws IOException {
        LinkTokenCreateRequestUser user = new LinkTokenCreateRequestUser().clientUserId("user-id");
        LinkTokenCreateRequest request = new LinkTokenCreateRequest()
                .user(user)
                .clientName("Finance Tracker")
                .products(Arrays.asList(Products.TRANSACTIONS))
                .countryCodes(Arrays.asList(CountryCode.US))
                .language("en");

        Response<LinkTokenCreateResponse> response = plaidApi.linkTokenCreate(request).execute();
        return response.body();
    }
}
