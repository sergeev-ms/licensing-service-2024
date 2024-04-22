package ru.sms.license.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.sms.license.model.License;

import java.util.Locale;
import java.util.Random;

@Service
public class LicenseService {


    @Autowired
    private MessageSource messages;

    public License getLicense(String licenseId, String organizationId) {
        final License license = new License();
        license.setId(new Random().nextInt(1000));
        license.setLicenseId(licenseId);
        license.setOrganizationId(organizationId);
        license.setDescription("Software");
        license.setProductName("Ostock");
        license.setLicenseType("full");
        return license;
    }

    public String createLicense(License license, String organizationId, Locale locale) {
        String responseMessage = null;
        if (license != null) {
            license.setOrganizationId(organizationId);
            responseMessage = messages.getMessage("license.create.message", new Object[] {license}, locale);
        }
        return responseMessage;
    }

    public String updateLicense(License license, String organizationId, Locale locale) {
        String responseMessage = null;
        if (license != null) {
            license.setOrganizationId(organizationId);
            responseMessage =messages.getMessage("license.update.message", new Object[] {license}, locale);
        }
        return responseMessage;
    }

    public String deleteLicense(String licenseId, String organizationId, Locale locale) {
        String responseMessage = messages.getMessage("license.delete.message", new Object[] {licenseId, organizationId}, locale);
        return responseMessage;
    }


}
