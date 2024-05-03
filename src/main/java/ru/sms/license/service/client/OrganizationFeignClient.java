package ru.sms.license.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.sms.license.model.Organization;

@FeignClient("organization-service")
public interface OrganizationFeignClient {

    @GetMapping("/v1/organization/{organizationId}")
    Organization getOrganization(@PathVariable String organizationId);
}
