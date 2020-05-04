package com.optimagrowth.license.service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;

import com.optimagrowth.license.model.Organization;

@Component
public class OrganizationRestTemplateClient {
    @Autowired
    OAuth2RestTemplate restTemplate;

    public Organization getOrganization(String organizationId){
        ResponseEntity<Organization> restExchange =
                restTemplate.exchange(
                        "http://gateway:8072/organization/v1/organization/{organizationId}",
                        HttpMethod.GET,
                        null, Organization.class, organizationId);

        return restExchange.getBody();
    }
}
