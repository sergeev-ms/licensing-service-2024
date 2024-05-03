package ru.sms.license.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.sms.license.config.ServiceConfig;
import ru.sms.license.exception.EntityNotFoundException;
import ru.sms.license.model.License;
import ru.sms.license.model.Organization;
import ru.sms.license.repository.LicenseRepository;
import ru.sms.license.service.client.OrganizationDiscoveryClient;
import ru.sms.license.service.client.OrganizationFeignClient;
import ru.sms.license.service.client.OrganizationRestTemplateClient;

import java.util.Locale;
import java.util.UUID;

@Slf4j
@Service
public class LicenseService {
    @Autowired
    private MessageSource messages;
    @Autowired
    private ServiceConfig serviceConfig;
    @Autowired
    private LicenseRepository licenseRepository;
    @Autowired
    private OrganizationDiscoveryClient organizationDiscoveryClient;
    @Autowired
    private OrganizationRestTemplateClient organizationRestClient;
    @Autowired
    private OrganizationFeignClient organizationFeignClient;


    public License getLicense(String licenseId, String organizationId) {
        final License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
        if (license == null) {
            final String errorMessage = messages.getMessage("license.search.error.message",
                    new Object[]{licenseId, organizationId}, Locale.getDefault());
            throw new EntityNotFoundException(errorMessage);
        }
        return license.withComment(serviceConfig.getProperty());
    }

    public License getLicense(String licenseId, String organizationId, String clientType) {
        final License license = getLicense(licenseId, organizationId);
        Organization organization = retrieveOrganization(organizationId, clientType);
        if (organization != null) {
            license.setOrganizationName(organization.getName());
            license.setContactName(organization.getContactName());
            license.setContactEmail(organization.getContactEmail());
            license.setContactPhone(organization.getContactPhone());
        }
        return license;
    }

    private Organization retrieveOrganization(String organizationId, String clientType) {
        Organization organization;

        switch (clientType) {
            case "feign" -> {
                log.info("I am using the feign client");
                organization = organizationFeignClient.getOrganization(organizationId);
            }
            case "rest" -> {
                log.info("I am using the rest client");
                organization = organizationRestClient.getOrganization(organizationId);
            }
            case "discovery" -> {
                log.info("I am using the discovery client");
                organization = organizationDiscoveryClient.getOrganization(organizationId);
            }
            default -> organization = organizationRestClient.getOrganization(organizationId);
        }

        return organization;
    }

    public License createLicense(License license) {
        license.setLicenseId(UUID.randomUUID().toString());
        final License saved = licenseRepository.save(license);
        return saved.withComment(serviceConfig.getProperty());
    }

    public License updateLicense(License license) {
        final License saved = licenseRepository.save(license);
        return saved.withComment(serviceConfig.getProperty());
    }

    public String deleteLicense(String licenseId) {
        final License license = licenseRepository.findById(licenseId).orElse(null);
        if (license == null)
            return messages.getMessage("license.search.error.message",
                    new Object[]{licenseId, null}, Locale.getDefault());
        licenseRepository.delete(license);
        return messages.getMessage("license.delete.message",
                new Object[] {licenseId}, Locale.getDefault());
    }


}
