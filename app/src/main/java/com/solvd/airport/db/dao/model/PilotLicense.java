package com.solvd.airport.db.dao.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pilotLicense")
@XmlAccessorType(XmlAccessType.FIELD)
public class PilotLicense {
    private Long id;
    private String licenseCode;

    public PilotLicense(Long id, String licenseCode) {
        this.id = id;
        this.licenseCode = licenseCode;
    }

    public PilotLicense() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicenseCode() {
        return licenseCode;
    }

    public void setLicenseCode(String licenseCode) {
        this.licenseCode = licenseCode;
    }
}
