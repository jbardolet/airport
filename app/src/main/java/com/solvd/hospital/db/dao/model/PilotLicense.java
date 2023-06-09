package com.solvd.hospital.db.dao.model;

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
