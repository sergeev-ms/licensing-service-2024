package ru.sms.license.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.sms.license.config.ServiceConfig;
import ru.sms.license.model.License;
import ru.sms.license.repository.LicenseRepository;

import java.util.Locale;
import java.util.UUID;

@Service
public class LicenseService {
    @Autowired
    private MessageSource messages;
    @Autowired
    private ServiceConfig serviceConfig;
    @Autowired
    private LicenseRepository licenseRepository;


    public License getLicense(String licenseId, String organizationName) {
        final License license = licenseRepository.findByOrganizationNameAndLicenseId(organizationName, licenseId);
        if (license == null) {
            final String errorMessage = messages.getMessage("license.search.error.message",
                    new Object[]{licenseId, organizationName}, Locale.getDefault());
            throw new IllegalArgumentException(errorMessage);
        }
        return license.withComment(serviceConfig.getProperty());
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
                new Object[] {licenseId, license.getOrganization().getId()}, Locale.getDefault());
    }


}
