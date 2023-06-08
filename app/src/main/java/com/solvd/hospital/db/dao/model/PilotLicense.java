package com.solvd.hospital.db.dao.model;

public class PilotLicense {
    private Integer id;
    private String licenseCode;

    public PilotLicense(Integer id, String licenseCode) {
        this.id = id;
        this.licenseCode = licenseCode;
    }

    public PilotLicense() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLicenseCode() {
        return licenseCode;
    }

    public void setLicenseCode(String licenseCode) {
        this.licenseCode = licenseCode;
    }
}
