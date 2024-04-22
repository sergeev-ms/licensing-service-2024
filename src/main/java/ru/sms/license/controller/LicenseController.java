package ru.sms.license.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sms.license.model.License;
import ru.sms.license.service.LicenseService;

import java.util.Locale;

@RestController
@RequestMapping(value = "v1/organization/{organizationId}/license")
public class LicenseController {

    @Autowired
    private LicenseService licenseService;

    @GetMapping(value = "/{licenseId}")
    public ResponseEntity<License> getLicense(
            @PathVariable String organizationId,
            @PathVariable String licenseId) {
        final License license = licenseService.getLicense(licenseId, organizationId);
        return ResponseEntity.ok(license);
    }
    @PostMapping
    public ResponseEntity<String> createLicense(
            @PathVariable String organizationId,
            @RequestBody License license,
            @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        final String serviceLicense = licenseService.createLicense(license, organizationId, locale);
        return ResponseEntity.ok(serviceLicense);
    }

    @PutMapping
    public ResponseEntity<String> updateLicense(
            @PathVariable String organizationId,
            @RequestBody License license,
            @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        return ResponseEntity.ok(licenseService.updateLicense(license, organizationId, locale));
    }

    @DeleteMapping(value = "/{licenseId}")
    public ResponseEntity<String> deleteLicense(
            @PathVariable String organizationId,
            @PathVariable String licenseId,
            @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        return ResponseEntity.ok(licenseService.deleteLicense(licenseId, organizationId, locale));
    }
}
