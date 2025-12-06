package com.backendproject.order.client;

import com.backendproject.order.dto.CataloguePriceDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class CatalogueClient {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public CatalogueClient(RestTemplate restTemplate,
                           @Value("${clients.catalogue.base-url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    /**
     * Expects catalogue-service to expose an endpoint that returns pricing
     * for product ids. Example (adjust to your actual API):
     * POST {base}/catalogue/pricing  body: {"ids":[1,2,3]}
     */
    public List<CataloguePriceDto> fetchPricing(List<Long> productIds) {
        String url = baseUrl + "/catalogue/pricing";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = Map.of("ids", productIds);
        HttpEntity<Map<String, Object>> req = new HttpEntity<>(body, headers);

        ResponseEntity<CataloguePriceDto[]> resp =
                restTemplate.postForEntity(url, req, CataloguePriceDto[].class);

        CataloguePriceDto[] arr = resp.getBody();
        return (arr == null) ? List.of() : Arrays.asList(arr);
    }
}
