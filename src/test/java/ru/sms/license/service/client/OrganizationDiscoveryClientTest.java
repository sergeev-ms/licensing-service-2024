package ru.sms.license.service.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.sms.license.model.Organization;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class OrganizationDiscoveryClientTest {

    @Autowired
    private OrganizationDiscoveryClient organizationDiscoveryClient;
    @Test
    void getOrganization() {
        final Organization organization =
                organizationDiscoveryClient.getOrganization("e6a625cc-718b-48c2-ac76-1dfdff9a531e");
        assertNotNull(organization);
    }
}