CREATE TABLE licences
(
    license_id      VARCHAR(255) NOT NULL,
    description     VARCHAR(255),
    organization_id VARCHAR(255),
    product_name    VARCHAR(255) NOT NULL,
    license_type    VARCHAR(255) NOT NULL,
    comment         VARCHAR(255),
    CONSTRAINT pk_licences PRIMARY KEY (license_id)
);

CREATE TABLE organization
(
    id            VARCHAR(255) NOT NULL,
    name          VARCHAR(255) NOT NULL,
    contact_name  VARCHAR(255),
    contact_email VARCHAR(255),
    contact_phone VARCHAR(255),
    CONSTRAINT pk_organization PRIMARY KEY (id)
);

ALTER TABLE organization
    ADD CONSTRAINT uc_organization_name UNIQUE (name);

ALTER TABLE licences
    ADD CONSTRAINT FK_LICENCES_ON_ORGANIZATION FOREIGN KEY (organization_id) REFERENCES organization (id);