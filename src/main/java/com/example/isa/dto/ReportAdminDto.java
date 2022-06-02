package com.example.isa.dto;

public class ReportAdminDto {

    public String reportText;
    public String email;
    public boolean approved;

    public ReportAdminDto() {
        super();
    }



    public String getReportText() {
        return reportText;
    }

    public void setReportText(String reportText) {
        this.reportText = reportText;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
