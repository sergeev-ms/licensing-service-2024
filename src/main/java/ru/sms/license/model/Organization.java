package ru.sms.license.model;

import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter @Setter @ToString
@Entity
@Table(name = "organization")
public class Organization extends RepresentationModel<Organization> {

    @Id
    @Column
    String id;

    @Column(nullable = false, unique = true)
    String name;

    @Column(name = "contact_name")
    String contactName;

    @Column(name = "contact_email")
    String contactEmail;

    @Column(name = "contact_phone")
    String contactPhone;

    @ToString.Exclude
    @OneToMany(mappedBy = "organization", orphanRemoval = true)
    transient private Set<License> licenses = new LinkedHashSet<>();

}