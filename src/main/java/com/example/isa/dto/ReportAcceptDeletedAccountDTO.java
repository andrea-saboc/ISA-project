package com.example.isa.dto;

public class ReportAcceptDeletedAccountDTO {
    public String reportText;
    public Long id;

    public ReportAcceptDeletedAccountDTO() {
        super();
    }

    public String getReportText() {
        return reportText;
    }

    public void setReportText(String reportText) {
        this.reportText = reportText;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
