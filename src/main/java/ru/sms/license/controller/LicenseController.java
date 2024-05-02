package ru.sms.license.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sms.license.model.License;
import ru.sms.license.service.LicenseService;

import java.util.Locale;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "v1/organization/{organizationName}/license")
public class LicenseController {

    @Autowired
    private LicenseService licenseService;

    @GetMapping(value = "/{licenseId}")
    public ResponseEntity<License> getLicense(
            @PathVariable String organizationName,
            @PathVariable String licenseId) {
        final License license = licenseService.getLicense(licenseId, organizationName);

        final LicenseController controller = methodOn(LicenseController.class);
        license.add(linkTo(controller.getLicense(organizationName, licenseId)).withSelfRel(),
                linkTo(controller.createLicense(license, null)).withRel("createLicense"),
                linkTo(controller.updateLicense(license, null)).withRel("updateLicense"),
                linkTo(controller.deleteLicense(organizationName, null)).withRel("deleteLicense")
        );

        return ResponseEntity.ok(license);
    }

    @PostMapping
    public ResponseEntity<License> createLicense(
            @RequestBody License license,
            @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        final License serviceLicense = licenseService.createLicense(license);
        return ResponseEntity.ok(serviceLicense);
    }

    @PutMapping
    public ResponseEntity<License> updateLicense(
            @RequestBody License license,
            @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        return ResponseEntity.ok(licenseService.updateLicense(license));
    }

    @DeleteMapping(value = "/{licenseId}")
    public ResponseEntity<String> deleteLicense(
            @PathVariable String licenseId,
            @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        return ResponseEntity.ok(licenseService.deleteLicense(licenseId));
    }
}
