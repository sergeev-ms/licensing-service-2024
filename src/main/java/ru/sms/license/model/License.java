package ru.sms.license.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

@Getter @Setter @ToString
@Entity
@Table(name = "licences")
public class License extends RepresentationModel<License> {

    @Id
    @Column(name = "license_id", nullable = false)
    private String licenseId;

    @Column(name = "organization_id")
    private String organizationId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column
    private String description;

    @Column(name = "license_type", nullable = false)
    private String licenseType;

    @Column
    private String comment;

    @Transient
    private String organizationName;
    @Transient
    private String contactName;
    @Transient
    private String contactPhone;
    @Transient
    private String contactEmail;


    public License withComment(String comment) {
        this.setComment(comment);
        return this;
    }
}
