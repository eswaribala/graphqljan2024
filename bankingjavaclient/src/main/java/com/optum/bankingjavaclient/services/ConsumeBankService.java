package com.optum.bankingjavaclient.services;

import graphql.servlet.internal.GraphQLRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

@Service
@Slf4j
public class ConsumeBankService {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${url}")
    private String url;


    public String getAllCustomers(){
        HttpHeaders headers = new HttpHeaders();

        headers.add("content-type", "application/json"); // just modified graphql into json

        String query1 = "query{\n" +
                "  findAllCustomers{\n" +
                "    name{\n" +
                "      firstName,\n" +
                "      lastName\n" +
                "    },\n" +
                "    email\n" +
                "  }\n" +
                "}";

        //MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        GraphQLRequest   graphQLRequest=new GraphQLRequest();
        graphQLRequest.setQuery(query1);
        HttpEntity<GraphQLRequest> entity = new HttpEntity<>(graphQLRequest, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        log.info("The response================="+response);
        return response.getBody().toString();
    }



}
