package ru.sms.license.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sms.license.model.License;

import java.util.List;

@Repository
public interface LicenseRepository extends CrudRepository<License, String> {
    public List<License> findByOrganizationId(String organizationId);
    public License findByOrganizationNameAndLicenseId(String organizationId, String licenseId);
}
