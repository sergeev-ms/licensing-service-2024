package ru.sms.license.service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.sms.license.model.Organization;

@Component
public class OrganizationRestTemplateClient {

    @Autowired
    private RestTemplate restTemplate;

    public Organization getOrganization(String organizationId) {
        final ResponseEntity<Organization> exchange = restTemplate.exchange("http://organization-service/v1/organization/{organizationId}", HttpMethod.GET, null,
                Organization.class, organizationId);
        return exchange.getBody();
    }
}
